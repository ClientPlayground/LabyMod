package net.minecraft.tileentity;

import com.google.gson.JsonParseException;
import de.labystudio.gommehd.GommeHDSign;
import java.util.ArrayList;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class TileEntitySign extends TileEntity
{
    public final IChatComponent[] signText = new IChatComponent[] {new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText(""), new ChatComponentText("")};

    /**
     * The index of the line currently being edited. Only used on client side, but defined on both. Note this is only
     * really used when the > < are going to be visible.
     */
    public int lineBeingEdited = -1;
    private boolean isEditable = true;
    private EntityPlayer player;
    private final CommandResultStats stats = new CommandResultStats();
    public int updateSign = 0;
    ArrayList<String> text = new ArrayList();
    boolean available = false;
    boolean search = false;
    boolean size = false;
    boolean full = false;
    boolean empty = false;

    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);

        for (int i = 0; i < 4; ++i)
        {
            String s = IChatComponent.Serializer.componentToJson(this.signText[i]);
            compound.setString("Text" + (i + 1), s);
        }

        this.stats.writeStatsToNBT(compound);
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        this.isEditable = false;
        super.readFromNBT(compound);
        ICommandSender icommandsender = new ICommandSender()
        {
            public String getName()
            {
                return "Sign";
            }
            public IChatComponent getDisplayName()
            {
                return new ChatComponentText(this.getName());
            }
            public void addChatMessage(IChatComponent component)
            {
            }
            public boolean canCommandSenderUseCommand(int permLevel, String commandName)
            {
                return true;
            }
            public BlockPos getPosition()
            {
                return TileEntitySign.this.pos;
            }
            public Vec3 getPositionVector()
            {
                return new Vec3((double)TileEntitySign.this.pos.getX() + 0.5D, (double)TileEntitySign.this.pos.getY() + 0.5D, (double)TileEntitySign.this.pos.getZ() + 0.5D);
            }
            public World getEntityWorld()
            {
                return TileEntitySign.this.worldObj;
            }
            public Entity getCommandSenderEntity()
            {
                return null;
            }
            public boolean sendCommandFeedback()
            {
                return false;
            }
            public void setCommandStat(CommandResultStats.Type type, int amount)
            {
            }
        };

        for (int i = 0; i < 4; ++i)
        {
            String s = compound.getString("Text" + (i + 1));

            try
            {
                IChatComponent ichatcomponent = IChatComponent.Serializer.jsonToComponent(s);

                try
                {
                    this.signText[i] = ChatComponentProcessor.processComponent(icommandsender, ichatcomponent, (Entity)null);
                }
                catch (CommandException var7)
                {
                    this.signText[i] = ichatcomponent;
                }
            }
            catch (JsonParseException var8)
            {
                this.signText[i] = new ChatComponentText(s);
            }
        }

        this.stats.readStatsFromNBT(compound);
    }

    /**
     * Allows for a specialized description packet to be created. This is often used to sync tile entity data from the
     * server to the client easily. For example this is used by signs to synchronise the text to be displayed.
     */
    public Packet getDescriptionPacket()
    {
        IChatComponent[] aichatcomponent = new IChatComponent[4];
        System.arraycopy(this.signText, 0, aichatcomponent, 0, 4);
        return new S33PacketUpdateSign(this.worldObj, this.pos, aichatcomponent);
    }

    public boolean func_183000_F()
    {
        return true;
    }

    public boolean getIsEditable()
    {
        return this.isEditable;
    }

    /**
     * Sets the sign's isEditable flag to the specified parameter.
     */
    public void setEditable(boolean isEditableIn)
    {
        this.isEditable = isEditableIn;

        if (!isEditableIn)
        {
            this.player = null;
        }
    }

    public void setPlayer(EntityPlayer playerIn)
    {
        this.player = playerIn;
    }

    public EntityPlayer getPlayer()
    {
        return this.player;
    }

    public boolean executeCommand(final EntityPlayer playerIn)
    {
        ICommandSender icommandsender = new ICommandSender()
        {
            public String getName()
            {
                return playerIn.getName();
            }
            public IChatComponent getDisplayName()
            {
                return playerIn.getDisplayName();
            }
            public void addChatMessage(IChatComponent component)
            {
            }
            public boolean canCommandSenderUseCommand(int permLevel, String commandName)
            {
                return permLevel <= 2;
            }
            public BlockPos getPosition()
            {
                return TileEntitySign.this.pos;
            }
            public Vec3 getPositionVector()
            {
                return new Vec3((double)TileEntitySign.this.pos.getX() + 0.5D, (double)TileEntitySign.this.pos.getY() + 0.5D, (double)TileEntitySign.this.pos.getZ() + 0.5D);
            }
            public World getEntityWorld()
            {
                return playerIn.getEntityWorld();
            }
            public Entity getCommandSenderEntity()
            {
                return playerIn;
            }
            public boolean sendCommandFeedback()
            {
                return false;
            }
            public void setCommandStat(CommandResultStats.Type type, int amount)
            {
                TileEntitySign.this.stats.func_179672_a(this, type, amount);
            }
        };

        for (int i = 0; i < this.signText.length; ++i)
        {
            ChatStyle chatstyle = this.signText[i] == null ? null : this.signText[i].getChatStyle();

            if (chatstyle != null && chatstyle.getChatClickEvent() != null)
            {
                ClickEvent clickevent = chatstyle.getChatClickEvent();

                if (clickevent.getAction() == ClickEvent.Action.RUN_COMMAND)
                {
                    MinecraftServer.getServer().getCommandManager().executeCommand(icommandsender, clickevent.getValue());
                }
            }
        }

        return true;
    }

    public CommandResultStats getStats()
    {
        return this.stats;
    }

    public void setText(ArrayList<String> p_setText_1_)
    {
        this.text = GommeHDSign.getText(this);
    }

    public ArrayList<String> getText()
    {
        return this.text;
    }

    public void setAvailable(ArrayList<String> p_setAvailable_1_)
    {
        this.available = GommeHDSign.isAvailable(p_setAvailable_1_);
    }

    public boolean getAvailable()
    {
        return this.available;
    }

    public void setSearch(ArrayList<String> p_setSearch_1_)
    {
        this.search = GommeHDSign.search(p_setSearch_1_);
    }

    public boolean getSearch()
    {
        return this.search;
    }

    public void setSize(ArrayList<String> p_setSize_1_)
    {
        this.size = GommeHDSign.size(p_setSize_1_);
    }

    public void setEmpty(ArrayList<String> p_setEmpty_1_)
    {
        this.empty = GommeHDSign.isEmpty(p_setEmpty_1_);
    }

    public boolean isEmpty()
    {
        return this.empty;
    }

    public boolean getSize()
    {
        return this.size;
    }

    public void setFull(ArrayList<String> p_setFull_1_)
    {
        this.full = GommeHDSign.isFull(p_setFull_1_);
    }

    public boolean isFull()
    {
        return this.full;
    }
}
