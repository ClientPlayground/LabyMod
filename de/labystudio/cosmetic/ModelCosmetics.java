package de.labystudio.cosmetic;

import de.labystudio.labymod.ConfigManager;
import de.labystudio.labymod.LabyMod;
import de.labystudio.modapi.ModAPI;
import de.labystudio.modapi.events.RenderCosmeticEvent;
import de.labystudio.utils.LeftHand;
import de.labystudio.utils.ModGui;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelCosmetics extends ModelBiped
{
    HashMap<UUID, Long> wingState;
    private ModelRenderer bipedDeadmau5Head;
    private ModelRenderer bipedRabbitHead;
    ModelRenderer wolfTail;
    private ModelRenderer wing;
    private ModelRenderer wingTip;
    ModelRenderer ocelotTail;
    ModelRenderer ocelotTail2;
    private ModelRenderer[] witherBody;
    private ModelRenderer[] witherHeads;
    private ModelRenderer[] blazeSticks;
    private ModelRenderer witchHat;
    private ModelRenderer xmasHat;
    private ModelRenderer horn;
    private ModelRenderer halo;
    private ModelRenderer crown;
    private ModelRenderer crownDiamond;
    private ModelRenderer cap;
    private ModelRenderer pot;
    private static final HashMap<String, ResourceLocation> flags = new HashMap();
    private static final HashMap<String, ResourceLocation> caps = new HashMap();
    private static final ResourceLocation enderDragonTextures = new ResourceLocation("textures/entity/enderdragon/dragon.png");
    private static final ResourceLocation blazeTexture = new ResourceLocation("textures/entity/blaze.png");
    private static final ResourceLocation witherTexture = new ResourceLocation("textures/entity/wither/wither.png");
    private static final ResourceLocation witchTexture = new ResourceLocation("flags/default.png");
    private static final ResourceLocation haloTexture = new ResourceLocation("halo.png");
    private static final ResourceLocation xmasTexture = new ResourceLocation("xmas.png");
    private static final ResourceLocation crownTexture = new ResourceLocation("crown.png");
    private static final ResourceLocation potTexture = new ResourceLocation("textures/blocks/flower_pot.png");
    private static final ResourceLocation dirtTexture = new ResourceLocation("textures/blocks/dirt.png");
    private static final ResourceLocation capTexture = new ResourceLocation("caps/DEFAULT.png");
    private static final ResourceLocation wolfTexture = new ResourceLocation("wolf.png");

    public ModelCosmetics(float p_i46304_1_, boolean p_i46304_2_)
    {
        super(p_i46304_1_, 0.0F, 64, 64);
        this.wingState = new HashMap();
        this.blazeSticks = new ModelRenderer[12];
        this.bipedDeadmau5Head = new ModelRenderer(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, p_i46304_1_);
        this.bipedDeadmau5Head.isHidden = true;
        this.bipedRabbitHead = new ModelRenderer(this, 24, 0);
        this.bipedRabbitHead.addBox(-3.0F, -6.0F, -1.0F, 2, 6, 1, p_i46304_1_);
        this.bipedRabbitHead.isHidden = true;
        this.horn = new ModelRenderer(this, 24, 0);
        this.horn.addBox(-3.0F, -6.0F, -1.0F, 2, 4, 1, p_i46304_1_);
        this.horn.isHidden = true;
        this.wolfTail = new ModelRenderer(this, 56, 30);
        this.wolfTail.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
        this.wolfTail.setRotationPoint(-0.2F, 10.0F, 3.0F);
        this.wolfTail.isHidden = true;
        this.setTextureOffset("body.scale", 220, 53);
        this.setTextureOffset("body.body", 0, 0);
        this.setTextureOffset("wingtip.bone", 112, 136);
        this.setTextureOffset("wing.skin", -56, 88);
        this.setTextureOffset("wing.bone", 112, 88);
        this.setTextureOffset("wingtip.skin", -56, 144);
        int i = this.textureWidth;
        int j = this.textureHeight;
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.wing = new ModelRenderer(this, "wing");
        this.wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
        this.wing.addBox("bone", -56.0F, -4.0F, -4.0F, 56, 8, 8);
        this.wing.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 1, 56);
        this.wing.isHidden = true;
        this.wingTip = new ModelRenderer(this, "wingtip");
        this.wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
        this.wingTip.isHidden = true;
        this.wingTip.addBox("bone", -56.0F, -2.0F, -2.0F, 56, 4, 4);
        this.wingTip.addBox("skin", -56.0F, 0.0F, 2.0F, 56, 1, 56);
        this.wing.addChild(this.wingTip);
        this.textureWidth = i;
        this.textureHeight = j;
        this.ocelotTail = new ModelRenderer(this, 58, 13);
        this.ocelotTail.addBox(-0.5F, 0.0F, 0.0F, 1, 8, 1);
        this.ocelotTail.rotateAngleX = 0.9F;
        this.ocelotTail.setRotationPoint(0.0F, 15.0F, 8.0F);
        this.ocelotTail2 = new ModelRenderer(this, 58, 15);
        this.ocelotTail2.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 1);
        this.ocelotTail2.setRotationPoint(0.0F, 20.0F, 14.0F);
        this.ocelotTail.isHidden = true;
        this.ocelotTail2.isHidden = true;

        for (int k = 0; k < this.blazeSticks.length; ++k)
        {
            this.blazeSticks[k] = new ModelRenderer(this, 0, 16);
            this.blazeSticks[k].addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
            this.blazeSticks[k].isHidden = true;
        }

        this.textureWidth = 64;
        this.textureHeight = 64;
        this.witherBody = new ModelRenderer[2];
        this.witherBody[0] = new ModelRenderer(this, 0, 16);
        this.witherBody[0].addBox(-10.0F, -1.9F, -0.5F, 20, 3, 3, p_i46304_1_);
        this.witherBody[0].isHidden = true;
        this.witherBody[1] = (new ModelRenderer(this)).setTextureSize(this.textureWidth, this.textureHeight);
        this.witherBody[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
        this.witherBody[1].isHidden = true;
        this.witherHeads = new ModelRenderer[2];
        this.witherHeads[0] = new ModelRenderer(this, 3, 3);
        this.witherHeads[0].addBox(-4.0F, -11.0F, -4.0F, 6, 6, 6, p_i46304_1_);
        this.witherHeads[0].rotationPointX = -8.0F;
        this.witherHeads[0].rotationPointY = 4.0F;
        this.witherHeads[0].isHidden = true;
        this.witherHeads[1] = new ModelRenderer(this, 3, 3);
        this.witherHeads[1].addBox(-4.0F, -11.0F, -4.0F, 6, 6, 6, p_i46304_1_);
        this.witherHeads[1].rotationPointX = 10.0F;
        this.witherHeads[1].rotationPointY = 4.0F;
        this.witherHeads[1].isHidden = true;
        this.textureWidth = i;
        this.textureHeight = j;
        this.halo = new ModelRenderer(this, 0, 0);
        this.halo.addBox(-3.0F, -6.0F, -1.0F, 6, 1, 1, p_i46304_1_);
        this.halo.isHidden = true;
        this.witchHat = (new ModelRenderer(this)).setTextureSize(40, 34);
        this.witchHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.witchHat.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        ModelRenderer modelrenderer9 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer9.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer9.setTextureOffset(0, 12).addBox(0.0F, 0.0F, 0.0F, 7, 4, 7);
        modelrenderer9.rotateAngleX = -0.05235988F;
        modelrenderer9.rotateAngleZ = 0.02617994F;
        this.witchHat.addChild(modelrenderer9);
        ModelRenderer modelrenderer = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer.setRotationPoint(1.75F, -4.0F, 2.0F);
        modelrenderer.setTextureOffset(0, 23).addBox(0.0F, 0.0F, 0.0F, 4, 4, 4);
        modelrenderer.rotateAngleX = -0.10471976F;
        modelrenderer.rotateAngleZ = 0.05235988F;
        modelrenderer9.addChild(modelrenderer);
        ModelRenderer modelrenderer1 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer1.setRotationPoint(1.75F, -2.0F, 2.0F);
        modelrenderer1.setTextureOffset(0, 31).addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.25F);
        modelrenderer1.rotateAngleX = -0.20943952F;
        modelrenderer1.rotateAngleZ = 0.10471976F;
        modelrenderer.addChild(modelrenderer1);
        this.witchHat.isHidden = true;
        this.xmasHat = (new ModelRenderer(this)).setTextureSize(40, 34);
        this.xmasHat.setRotationPoint(-5.0F, -10.03125F, -5.0F);
        this.xmasHat.setTextureOffset(0, 0).addBox(0.0F, 0.0F, 0.0F, 10, 2, 10);
        ModelRenderer modelrenderer2 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer2.setRotationPoint(4.0F, -2.7F, 4.0F);
        modelrenderer2.setTextureOffset(0, 12).addBox(-3.0F, 0.0F, -3.0F, 8, 3, 8);
        modelrenderer2.rotateAngleZ = 0.1F;
        this.xmasHat.addChild(modelrenderer2);
        ModelRenderer modelrenderer3 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer3.setRotationPoint(1.0F, -1.7F, 1.0F);
        modelrenderer3.setTextureOffset(0, 12).addBox(-3.0F, 0.0F, -3.0F, 6, 2, 6);
        modelrenderer3.rotateAngleZ = 0.1F;
        modelrenderer2.addChild(modelrenderer3);
        ModelRenderer modelrenderer4 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer4.setRotationPoint(1.0F, -2.0F, 0.0F);
        modelrenderer4.setTextureOffset(0, 12).addBox(-1.0F, 0.0F, -2.0F, 4, 4, 4);
        modelrenderer4.rotateAngleZ = 0.6F;
        modelrenderer3.addChild(modelrenderer4);
        ModelRenderer modelrenderer5 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer5.setRotationPoint(2.0F, -3.0F, 0.0F);
        modelrenderer5.setTextureOffset(0, 12).addBox(-2.0F, 1.4F, -1.5F, 3, 2, 3);
        modelrenderer5.rotateAngleZ = 0.2F;
        modelrenderer4.addChild(modelrenderer5);
        ModelRenderer modelrenderer6 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer6.setRotationPoint(0.0F, 0.0F, 0.0F);
        modelrenderer6.setTextureOffset(0, 12).addBox(-0.5F, 0.5F, -1.0F, 4, 2, 2);
        modelrenderer6.rotateAngleZ = -0.4F;
        modelrenderer5.addChild(modelrenderer6);
        ModelRenderer modelrenderer7 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer7.setRotationPoint(0.0F, 0.0F, 0.0F);
        modelrenderer7.setTextureOffset(0, 12).addBox(3.5F, -0.5F, -0.5F, 3, 1, 1);
        modelrenderer7.rotateAngleZ = 0.8F;
        modelrenderer6.addChild(modelrenderer7);
        ModelRenderer modelrenderer8 = (new ModelRenderer(this)).setTextureSize(40, 34);
        modelrenderer8.setRotationPoint(0.0F, 0.0F, 0.0F);
        modelrenderer8.setTextureOffset(0, 0).addBox(5.0F, -1.2F, -1.0F, 2, 2, 2);
        modelrenderer8.rotateAngleZ = 0.05F;
        modelrenderer7.addChild(modelrenderer8);
        this.xmasHat.isHidden = true;
        float f = 0.02F;
        this.crown = (new ModelRenderer(this, 0, 0)).setTextureSize(22, 7);
        this.crown.setTextureOffset(4, 0).addBox(-4.0F, 0.0F, -5.0F, 8, 2, 1, f);
        this.crown.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, -5.0F, 1, 4, 1, f);
        this.crown.setTextureOffset(0, 5).addBox(-4.0F, -1.0F, -5.0F, 1, 1, 1, f);
        this.crown.setTextureOffset(0, 5).addBox(3.0F, -1.0F, -5.0F, 1, 1, 1, f);
        this.crown.setTextureOffset(4, 5).addBox(-1.5F, -1.0F, -5.0F, 3, 1, 1, f);
        this.crown.setTextureOffset(0, 5).addBox(-0.5F, -2.0F, -5.0F, 1, 1, 1, f);
        this.crown.isHidden = true;
        this.crownDiamond = (new ModelRenderer(this, 12, 5)).setTextureSize(22, 7);
        this.crownDiamond.addBox(-0.5F, -0.0F, -6.0F, 1, 1, 1, f);
        this.crownDiamond.rotateAngleZ = 0.8F;
        this.crownDiamond.rotationPointZ = 0.5F;
        this.crownDiamond.rotationPointX = 0.4F;
        this.crownDiamond.isHidden = true;
        float f1 = 0.02F;
        this.cap = (new ModelRenderer(this, 0, 0)).setTextureSize(38, 38);
        this.cap.setTextureOffset(0, 0).addBox(-4.0F, -6.0F, -7.0F, 8, 1, 11, f1);
        this.cap.setTextureOffset(0, 12).addBox(-4.0F, -8.0F, -4.0F, 8, 2, 8, f1);
        this.cap.setTextureOffset(0, 22).addBox(-4.0F, -9.0F, -3.0F, 8, 1, 6, f1);
        this.cap.setTextureOffset(0, 29).addBox(-3.0F, -9.0F, -4.0F, 6, 1, 1, f1);
        this.cap.setTextureOffset(14, 29).addBox(-3.0F, -9.0F, 3.0F, 6, 1, 1, f1);
        this.cap.setTextureOffset(0, 31).addBox(-3.0F, -6.0F, -8.0F, 6, 1, 1, f1);
        this.cap.isHidden = true;
        float f2 = 0.01F;
        this.pot = (new ModelRenderer(this, 4, 4)).setTextureSize(16, 16);
        this.pot.setTextureOffset(5, 4).addBox(-3.0F, -19.0F, -3.0F, 1, 6, 6, f2);
        this.pot.setTextureOffset(0, 0).addBox(-3.0F, -19.0F, -3.0F, 6, 1, 6, f2 - 0.01F);
        this.pot.isHidden = true;
    }

    public ModelCosmetics(float p_i46304_1_, float f, int i, int j)
    {
        this(p_i46304_1_, false);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale)
    {
        if (LeftHand.use(entityIn))
        {
            GlStateManager.scale(-1.0F, 1.0F, 1.0F);
        }

        super.render(entityIn, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale);
        GlStateManager.pushMatrix();

        if (entityIn.isSneaking())
        {
            GlStateManager.translate(0.0F, 0.2F, 0.0F);
        }

        if (LeftHand.use(entityIn))
        {
            GlStateManager.scale(-1.0F, 1.0F, 1.0F);
        }

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        if (ConfigManager.settings.cosmetics)
        {
            CosmeticUser cosmeticuser = LabyMod.getInstance().getCosmeticManager().getCosmeticUser(entityIn);

            if (ModAPI.enabled() && ModAPI.extendedAPI)
            {
                ModAPI.callEvent(new RenderCosmeticEvent(entityIn, cosmeticuser, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale));
            }

            if (cosmeticuser != null && cosmeticuser.getCosmeticsData() != null && !cosmeticuser.getCosmeticsData().isEmpty() && entityIn instanceof AbstractClientPlayer)
            {
                AbstractClientPlayer abstractclientplayer = (AbstractClientPlayer)entityIn;

                if (abstractclientplayer != null && abstractclientplayer.getLocationSkin() != null && !abstractclientplayer.isSpectator() && !abstractclientplayer.isPotionActive(Potion.invisibility))
                {
                    float f = LabyMod.getInstance().getPartialTicks();

                    if (Minecraft.getMinecraft().currentScreen != null && abstractclientplayer == Minecraft.getMinecraft().thePlayer)
                    {
                        f = 1.0F;
                    }

                    float f1 = getFirstRotationX(abstractclientplayer, f);
                    float f2 = getSecondRotationX(abstractclientplayer, f);
                    ResourceLocation resourcelocation = abstractclientplayer.getLocationSkin();

                    for (Cosmetic cosmetic : cosmeticuser.getCosmeticsData())
                    {
                        if (cosmetic != null)
                        {
                            Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
                            GlStateManager.color(1.0F, 1.0F, 1.0F);
                            GL11.glColor3d(1.0D, 1.0D, 1.0D);
                            GlStateManager.resetColor();

                            if (cosmetic.getType() == EnumCosmetic.CAP)
                            {
                                GlStateManager.pushMatrix();
                                GlStateManager.color(1.0F, 1.0F, 1.0F);
                                GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                ResourceLocation resourcelocation1 = capTexture;

                                if (cosmetic.d != null && !cosmetic.d.isEmpty())
                                {
                                    if (!caps.containsKey(cosmetic.d))
                                    {
                                        caps.put(cosmetic.d, new ResourceLocation("caps/" + cosmetic.d + ".png"));
                                    }

                                    ResourceLocation resourcelocation2 = (ResourceLocation)caps.get(cosmetic.d);

                                    if (resourcelocation2 != null)
                                    {
                                        resourcelocation1 = (ResourceLocation)caps.get(cosmetic.d);
                                    }
                                }

                                Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation1);
                                float f25 = 1.226F;
                                GlStateManager.scale(f25, f25, f25);

                                if (cosmetic.a == 1.0D)
                                {
                                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                                }

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0D, 0.06D, 0.0D);
                                }

                                this.cap.isHidden = false;
                                this.cap.render(0.0571F);
                                this.cap.isHidden = true;

                                if (cosmetic.d != null && cosmetic.d.equals("LABYMOD"))
                                {
                                    float f3 = 0.3F;
                                    GlStateManager.translate(0.0D, -0.425D, -0.24D);
                                    GlStateManager.scale(f3, f3, f3);
                                    Minecraft.getMinecraft().getTextureManager().bindTexture(wolfTexture);
                                    LabyMod.getInstance().draw.drawTexturedModalRectFixed(-0.2D, -0.2D, 256.0D, 256.0D, 0.4D, 0.4D);
                                }

                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.FLOWER && cosmetic.itemStack != null)
                            {
                                Minecraft.getMinecraft().getTextureManager().bindTexture(potTexture);
                                GlStateManager.pushMatrix();
                                GlStateManager.color(1.0F, 1.0F, 1.0F);
                                GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                GlStateManager.scale(0.7D, 0.7D, 0.7D);
                                this.pot.isHidden = false;

                                for (int i = 0; i < 4; ++i)
                                {
                                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                                    this.pot.render(0.0571F);
                                }

                                this.pot.isHidden = true;
                                GlStateManager.scale(0.8D, 0.8D, 0.8D);
                                GlStateManager.translate(0.0D, -1.7D, 0.0D);
                                GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
                                GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
                                ItemStack itemstack = cosmetic.itemStack;

                                for (int j1 = 0; j1 < 2; ++j1)
                                {
                                    GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                                    Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack, ItemCameraTransforms.TransformType.FIXED);
                                }

                                GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                                GlStateManager.translate(0.0D, 0.0D, 0.5D);
                                Minecraft.getMinecraft().getTextureManager().bindTexture(dirtTexture);
                                LabyMod.getInstance().draw.drawTexturedModalRectFixed(-0.2D, -0.2D, 256.0D, 256.0D, 0.4D, 0.4D);
                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.CROWN)
                            {
                                for (int j = 0; j < 4; ++j)
                                {
                                    GlStateManager.pushMatrix();
                                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                                    GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                    GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                    float f26 = 1.085F;
                                    GlStateManager.scale(f26, f26, f26);

                                    if (entityIn.isSneaking())
                                    {
                                        float f31 = entityIn.rotationPitch * -7.0E-4F;
                                        GlStateManager.translate(0.0D, (double)(0.06F - Math.abs(f31)) + 0.02D, (double)f31);
                                    }

                                    GlStateManager.rotate((float)(90 * j), 0.0F, 1.0F, 0.0F);
                                    GlStateManager.translate(0.0D, -0.4753D, 0.0D);
                                    Minecraft.getMinecraft().getTextureManager().bindTexture(crownTexture);
                                    this.crown.isHidden = false;
                                    this.crown.render(0.0571F);
                                    this.crown.isHidden = true;
                                    this.crownDiamond.isHidden = false;
                                    this.crownDiamond.rotateAngleZ = 0.8F;
                                    this.crownDiamond.rotationPointZ = 0.6F;
                                    this.crownDiamond.rotationPointX = 0.4F;
                                    GlStateManager.translate(-0.22F, 0.0F, 0.0F);
                                    GlStateManager.color(1.0F, 1.0F, 1.0F);

                                    if (cosmetic.getData() != null)
                                    {
                                        GL11.glColor4f((float)cosmetic.a * 0.01F, (float)cosmetic.b * 0.01F, (float)cosmetic.c * 0.01F, 0.5F);
                                    }

                                    for (int i2 = 0; i2 < 3; ++i2)
                                    {
                                        this.crownDiamond.render(0.0561F);
                                        GlStateManager.translate(0.218F, 0.0F, 0.0F);
                                    }

                                    GL11.glColor3d(1.0D, 1.0D, 1.0D);
                                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                                    this.crownDiamond.isHidden = true;
                                    GlStateManager.popMatrix();
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.XMAS)
                            {
                                Minecraft.getMinecraft().getTextureManager().bindTexture(xmasTexture);
                                GlStateManager.pushMatrix();

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0D, 0.06D, 0.0D);
                                }

                                GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                GlStateManager.scale(0.95F, 0.95F, 0.95F);
                                boolean flag1 = cosmetic.getData() != null && cosmetic.getData().equalsIgnoreCase("emotions");

                                if (flag1)
                                {
                                    AbstractClientPlayer abstractclientplayer1 = (AbstractClientPlayer)entityIn;
                                    double d4 = abstractclientplayer1.prevChasingPosX + (abstractclientplayer1.chasingPosX - abstractclientplayer1.prevChasingPosX) * (double)f - (abstractclientplayer1.prevPosX + (abstractclientplayer1.posX - abstractclientplayer1.prevPosX) * (double)f);
                                    double d0 = abstractclientplayer1.prevChasingPosY + (abstractclientplayer1.chasingPosY - abstractclientplayer1.prevChasingPosY) * (double)f - (abstractclientplayer1.prevPosY + (abstractclientplayer1.posY - abstractclientplayer1.prevPosY) * (double)f);
                                    double d1 = abstractclientplayer1.prevChasingPosZ + (abstractclientplayer1.chasingPosZ - abstractclientplayer1.prevChasingPosZ) * (double)f - (abstractclientplayer1.prevPosZ + (abstractclientplayer1.posZ - abstractclientplayer1.prevPosZ) * (double)f);
                                    float f6 = abstractclientplayer1.prevRenderYawOffset + (abstractclientplayer1.renderYawOffset - abstractclientplayer1.prevRenderYawOffset) * f;
                                    double d2 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
                                    double d3 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
                                    float f8 = (float)d0 * 10.0F;
                                    f8 = MathHelper.clamp_float(f8, -40.0F, 12.0F);
                                    float f9 = (float)(d4 * d2 + d1 * d3) * 100.0F;
                                    float f10 = (float)(d4 * d3 - d1 * d2) * 100.0F;
                                    float f11 = (90.0F - Math.abs(abstractclientplayer.rotationPitch)) / 100.0F;
                                    float f12 = f9 > 120.0F ? 120.0F : f9;
                                    float f13 = (float)(d0 / 3.0D > 0.7D ? -0.7D : -d0 / 3.0D);
                                    float f14 = (float)Math.cos((double)(p_78088_4_ / 10.0F)) / 40.0F;
                                    float f15 = abstractclientplayer.rotationPitch;
                                    float f16 = abstractclientplayer1.distanceWalkedModified - abstractclientplayer1.prevDistanceWalkedModified;
                                    float f17 = -(abstractclientplayer1.distanceWalkedModified + f16 * f);
                                    float f18 = abstractclientplayer1.prevCameraYaw + (abstractclientplayer1.cameraYaw - abstractclientplayer1.prevCameraYaw) * f;
                                    f12 = f12 + Math.abs(MathHelper.cos(f17 * (float)Math.PI - 0.2F) * f18) * 70.0F;
                                    float f19 = 0.0F;

                                    if (abstractclientplayer1 == Minecraft.getMinecraft().thePlayer)
                                    {
                                        float f20 = 3.0F / (float)(ModGui.getRealFPS() + 1);

                                        if (CosmeticTick.LOCAL_XMAS_FPS_VALUE < CosmeticTick.LOCAL_XMAS_TICK_VALUE)
                                        {
                                            CosmeticTick.LOCAL_XMAS_FPS_VALUE += f20;
                                        }

                                        if (CosmeticTick.LOCAL_XMAS_FPS_VALUE > CosmeticTick.LOCAL_XMAS_TICK_VALUE)
                                        {
                                            CosmeticTick.LOCAL_XMAS_FPS_VALUE -= f20;
                                        }

                                        float f21 = -CosmeticTick.LOCAL_XMAS_FPS_VALUE;
                                        f19 = f21 / -1.5F;
                                        f13 += f21;
                                    }

                                    ModelRenderer modelrenderer7 = (ModelRenderer)this.xmasHat.childModels.get(0);
                                    ModelRenderer modelrenderer8 = (ModelRenderer)modelrenderer7.childModels.get(0);
                                    modelrenderer8.rotateAngleY = f15 / 300.0F - f12 / 200.0F;
                                    modelrenderer8.rotateAngleZ = 0.1F + f13 / 2.0F;
                                    ModelRenderer modelrenderer1 = (ModelRenderer)modelrenderer8.childModels.get(0);
                                    modelrenderer1.rotateAngleY = f15 / 200.0F;
                                    modelrenderer8.rotateAngleZ = 0.1F + f13 / 4.0F;
                                    ModelRenderer modelrenderer2 = (ModelRenderer)modelrenderer1.childModels.get(0);
                                    modelrenderer2.rotateAngleY = f15 / 100.0F - f12 / 100.0F;
                                    ModelRenderer modelrenderer3 = (ModelRenderer)modelrenderer2.childModels.get(0);
                                    modelrenderer3.rotateAngleZ = f13;
                                    modelrenderer3.rotateAngleY = f14;
                                    modelrenderer3.rotationPointY = f19;
                                    ModelRenderer modelrenderer4 = (ModelRenderer)modelrenderer3.childModels.get(0);
                                    modelrenderer4.rotateAngleZ = f11 - 0.3F;
                                    modelrenderer4.rotationPointY = 3.0F - f11 * 4.0F;
                                    ModelRenderer modelrenderer5 = (ModelRenderer)modelrenderer4.childModels.get(0);
                                    modelrenderer5.rotateAngleZ = f14 / -2.0F;
                                    modelrenderer5.rotateAngleY = f14 / 4.0F;
                                }

                                this.xmasHat.isHidden = false;
                                this.xmasHat.render(scale);
                                this.xmasHat.isHidden = true;
                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.HALLOWEEN)
                            {
                                GlStateManager.pushMatrix();
                                GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                GlStateManager.scale(0.8D, 0.8D, 0.8D);
                                ItemStack itemstack1 = null;
                                int k1 = (int)cosmetic.a;
                                int j2 = 0;

                                switch (k1)
                                {
                                    case 0:
                                        j2 = 258;
                                        GlStateManager.rotate(-120.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.rotate(40.0F, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.translate(0.4D, -0.4D, 0.3D);
                                        break;

                                    case 1:
                                        j2 = 257;
                                        GlStateManager.rotate(-30.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.translate(0.2D, -0.8D, -0.22D);
                                        break;

                                    case 2:
                                        j2 = 258;
                                        GlStateManager.rotate(70.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.translate(-0.3D, -0.7D, -0.02D);
                                        break;

                                    case 3:
                                        j2 = 292;
                                        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.rotate(70.0F, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.translate(0.2D, -0.6D, 0.4D);
                                        break;

                                    case 4:
                                        j2 = 262;
                                        GlStateManager.rotate(-180.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.rotate(-100.0F, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.rotate(30.0F, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.translate(0.1D, 0.3D, -0.5D);
                                        break;

                                    case 5:
                                        j2 = 262;
                                        GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.translate(0.1D, -0.1D, 0.0D);
                                        GlStateManager.translate(-0.19D, -0.19D, 0.0D);
                                        itemstack1 = new ItemStack(Item.getItemById(j2));

                                        if (itemstack1 != null && itemstack1.getItem() != null)
                                        {
                                            Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack1, ItemCameraTransforms.TransformType.FIXED);
                                        }

                                        GlStateManager.translate(0.38D, 0.38D, 0.0D);
                                }

                                if (j2 != 0)
                                {
                                    if (itemstack1 == null)
                                    {
                                        itemstack1 = new ItemStack(Item.getItemById(j2));
                                    }

                                    if (itemstack1 != null && itemstack1.getItem() != null)
                                    {
                                        Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack1, ItemCameraTransforms.TransformType.FIXED);
                                    }
                                }

                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.HALO)
                            {
                                for (int k = 0; k < 4; ++k)
                                {
                                    GlStateManager.pushMatrix();
                                    GlStateManager.color(3.9F, 3.0F, 0.0F);
                                    GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                    GlStateManager.rotate((float)(90 * k), 0.0F, 1.0F, 0.0F);
                                    float f27 = 0.2F;

                                    switch (k)
                                    {
                                        case 0:
                                            GlStateManager.translate(0.0F, 0.0F, 0.01F - f27);
                                            break;

                                        case 1:
                                            GlStateManager.translate(-0.19F + f27, 0.0F, -0.19F);
                                            break;

                                        case 2:
                                            GlStateManager.translate(0.0F, 0.0F, -0.38F + f27);
                                            break;

                                        case 3:
                                            GlStateManager.translate(0.19F - f27, 0.0F, -0.19F);
                                    }

                                    Minecraft.getMinecraft().getTextureManager().bindTexture(haloTexture);
                                    float f32 = p_78088_4_ / 10.0F;
                                    f32 = MathHelper.cos(f32);
                                    f32 = f32 * 0.03F;
                                    GlStateManager.translate(0.0F, -0.4F - p_78088_3_ * 0.1F - f32, 0.0F);
                                    this.halo.isHidden = false;
                                    this.halo.render(scale);
                                    this.halo.isHidden = true;
                                    GlStateManager.popMatrix();
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.TOOL)
                            {
                                GlStateManager.pushMatrix();

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.rotate(30.0F, 1.0F, 0.0F, 0.0F);
                                }

                                GlStateManager.scale(0.8D, 0.8D, 0.8D);
                                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                                GlStateManager.translate(0.0D, 0.3D, -0.22D);
                                ItemStack itemstack2;

                                if (cosmetic.itemStack == null)
                                {
                                    itemstack2 = ((AbstractClientPlayer)entityIn).getHeldItem();
                                }
                                else
                                {
                                    itemstack2 = cosmetic.itemStack;
                                }

                                if (itemstack2 != null && itemstack2.getItem() != null)
                                {
                                    Minecraft.getMinecraft().getItemRenderer().renderItem((EntityLivingBase)entityIn, itemstack2, ItemCameraTransforms.TransformType.FIXED);
                                }

                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.HAT)
                            {
                                ResourceLocation resourcelocation3 = witchTexture;

                                if (cosmetic.d != null && !cosmetic.d.isEmpty())
                                {
                                    if (!flags.containsKey(cosmetic.d))
                                    {
                                        flags.put(cosmetic.d, new ResourceLocation("flags/" + cosmetic.d + ".png"));
                                    }

                                    ResourceLocation resourcelocation4 = (ResourceLocation)flags.get(cosmetic.d);

                                    if (resourcelocation4 != null)
                                    {
                                        resourcelocation3 = (ResourceLocation)flags.get(cosmetic.d);
                                    }
                                }

                                Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation3);
                                GlStateManager.pushMatrix();

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0D, 0.1D, 0.0D);
                                }

                                GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                this.witchHat.isHidden = false;
                                this.witchHat.render(scale);
                                this.witchHat.isHidden = true;
                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.WITHER)
                            {
                                for (ModelRenderer modelrenderer : this.witherHeads)
                                {
                                    modelrenderer.isHidden = false;
                                    modelrenderer.render(scale);
                                    modelrenderer.isHidden = true;
                                }

                                Minecraft.getMinecraft().getTextureManager().bindTexture(witherTexture);

                                for (ModelRenderer modelrenderer6 : this.witherBody)
                                {
                                    modelrenderer6.isHidden = false;
                                    modelrenderer6.render(scale);
                                    modelrenderer6.isHidden = true;
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.BLAZE)
                            {
                                float f22 = p_78088_4_;
                                float f28 = p_78088_4_ * (float)Math.PI * -0.01F;

                                for (int k2 = 0; k2 < 4; ++k2)
                                {
                                    this.blazeSticks[k2].rotationPointY = -2.0F + MathHelper.cos(((float)((double)k2 * 1.5D) + f22) * 0.2F);
                                    this.blazeSticks[k2].rotationPointX = MathHelper.cos(f28) * 10.0F;
                                    this.blazeSticks[k2].rotationPointZ = MathHelper.sin(f28) * 10.0F;
                                    ++f28;
                                }

                                f28 = ((float)Math.PI / 4F) + f22 * (float)Math.PI * 0.01F;

                                for (int l2 = 4; l2 < 8; ++l2)
                                {
                                    this.blazeSticks[l2].rotationPointY = 2.0F + MathHelper.cos(((float)(l2 * 2) + f22) * 0.2F);
                                    this.blazeSticks[l2].rotationPointX = MathHelper.cos(f28) * 9.0F;
                                    this.blazeSticks[l2].rotationPointZ = MathHelper.sin(f28) * 9.0F;
                                    ++f28;
                                }

                                f28 = 0.47123894F + f22 * (float)Math.PI * -0.01F;

                                for (int i3 = 8; i3 < 12; ++i3)
                                {
                                    this.blazeSticks[i3].rotationPointY = 11.0F + MathHelper.cos(((float)i3 * 1.5F + f22) * 0.5F);
                                    this.blazeSticks[i3].rotationPointX = MathHelper.cos(f28) * 5.0F;
                                    this.blazeSticks[i3].rotationPointZ = MathHelper.sin(f28) * 5.0F;
                                    ++f28;
                                }

                                Minecraft.getMinecraft().getTextureManager().bindTexture(blazeTexture);

                                for (int j3 = 0; j3 < this.blazeSticks.length; ++j3)
                                {
                                    this.blazeSticks[j3].isHidden = false;
                                    this.blazeSticks[j3].render(scale);
                                    this.blazeSticks[j3].isHidden = true;
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.DEADMAU5)
                            {
                                for (int l = 0; l < 2; ++l)
                                {
                                    GlStateManager.pushMatrix();
                                    GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                    GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                    GlStateManager.translate(0.375F * (float)(l * 2 - 1), 0.0F, 0.0F);
                                    GlStateManager.translate(0.0F, -0.375F, 0.0F);
                                    GlStateManager.rotate(-f2, 1.0F, 0.0F, 0.0F);
                                    GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
                                    float f29 = 1.3333334F;
                                    GlStateManager.scale(f29, f29, f29);
                                    this.bipedDeadmau5Head.isHidden = false;
                                    this.renderDeadmau5Head(0.0625F);
                                    this.bipedDeadmau5Head.isHidden = true;
                                    GlStateManager.popMatrix();
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.RABBIT)
                            {
                                boolean flag2 = cosmetic.getData() != null && cosmetic.getData().equalsIgnoreCase("emotions");
                                float f30 = f1;

                                for (int k3 = 0; k3 < 2; ++k3)
                                {
                                    GlStateManager.pushMatrix();

                                    if (k3 == 0)
                                    {
                                        if (flag2)
                                        {
                                            GlStateManager.rotate((float)(Math.cos((double)(p_78088_4_ / 40.0F)) * 1.5D), 0.0F, 0.0F, 1.0F);
                                        }

                                        GlStateManager.rotate(f30, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.translate((double)(0.375F * (float)((double)k3 * 0.78D)) + 0.02D, 0.0D, 0.0D);
                                        GlStateManager.translate(0.0F, -0.375F, 0.0F);
                                        GlStateManager.rotate(-f2, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.rotate(-f30, 0.0F, 1.0F, 0.0F);
                                        this.bipedRabbitHead.rotateAngleX = this.bipedHead.rotateAngleX;
                                        this.bipedRabbitHead.rotateAngleY = this.bipedHead.rotateAngleY;
                                        this.bipedRabbitHead.rotateAngleZ = this.bipedHead.rotateAngleZ;
                                        this.bipedRabbitHead.rotationPointX = this.bipedHead.rotationPointX;
                                        this.bipedRabbitHead.rotationPointY = this.bipedHead.rotationPointY;
                                        this.bipedRabbitHead.rotationPointZ = this.bipedHead.rotationPointZ;
                                    }
                                    else
                                    {
                                        if (flag2)
                                        {
                                            GlStateManager.rotate((float)(Math.cos((double)(p_78088_4_ / 50.0F)) * 2.5D), 0.0F, 0.0F, 1.0F);
                                        }

                                        f30 *= -1.0F;
                                        GlStateManager.scale(-1.0F, 1.0F, 1.0F);
                                        GlStateManager.rotate(f30, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.translate((double)(0.375F * (float)((double)k3 * 0.78D)) + -0.276D, 0.0D, 0.0D);
                                        GlStateManager.translate(0.0F, -0.375F, 0.0F);
                                        GlStateManager.rotate(-f2, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.rotate(-f30, 0.0F, 1.0F, 0.0F);
                                        this.bipedRabbitHead.rotateAngleX = this.bipedHead.rotateAngleX;
                                        this.bipedRabbitHead.rotateAngleY = -this.bipedHead.rotateAngleY;
                                        this.bipedRabbitHead.rotateAngleZ = this.bipedHead.rotateAngleZ;
                                        this.bipedRabbitHead.rotationPointX = this.bipedHead.rotationPointX;
                                        this.bipedRabbitHead.rotationPointY = this.bipedHead.rotationPointY;
                                        this.bipedRabbitHead.rotationPointZ = this.bipedHead.rotationPointZ;
                                    }

                                    float f34 = 1.3333334F;
                                    GlStateManager.scale(f34, f34, f34);
                                    this.bipedRabbitHead.rotationPointX = 0.0F;
                                    this.bipedRabbitHead.rotationPointY = 0.0F;

                                    if (cosmeticuser.getEnumList().contains(EnumCosmetic.XMAS) || cosmeticuser.getEnumList().contains(EnumCosmetic.HAT))
                                    {
                                        GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
                                        GlStateManager.translate(0.0F, -0.1F, 0.0F);
                                    }

                                    if (flag2 && entityIn.isSneaking())
                                    {
                                        GlStateManager.rotate(entityIn.rotationPitch / 5.0F, 0.0F, 1.0F, 0.0F);
                                    }

                                    this.bipedRabbitHead.isHidden = false;
                                    this.bipedRabbitHead.render(0.0625F);
                                    this.bipedRabbitHead.isHidden = true;
                                    GlStateManager.popMatrix();
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.PIXELBIESTER)
                            {
                                for (int i1 = 0; i1 < 2; ++i1)
                                {
                                    for (int l1 = 0; l1 < 3; ++l1)
                                    {
                                        GlStateManager.pushMatrix();
                                        GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
                                        GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
                                        GlStateManager.translate(0.0D, 0.0D, i1 == 0 ? -0.04D : 0.04D);
                                        GlStateManager.rotate(i1 == 0 ? 180.0F : 0.0F, 0.0F, 1.0F, 0.0F);

                                        switch (l1)
                                        {
                                            case 0:
                                                GlStateManager.translate(0.03D, -0.3D, 0.0D);
                                                GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
                                                break;

                                            case 1:
                                                GlStateManager.translate(-0.28D, -0.45D, -0.02D);
                                                GlStateManager.scale(0.8D, 0.8D, 0.8D);
                                                GlStateManager.rotate(35.0F, 0.0F, 0.0F, 1.0F);
                                                break;

                                            case 2:
                                                GlStateManager.translate(-0.1D, -0.88D, -0.04D);
                                                GlStateManager.scale(0.4D, 0.4D, 0.4D);
                                                GlStateManager.rotate(-55.0F, 0.0F, 0.0F, 1.0F);
                                        }

                                        float f33 = 1.3333334F;
                                        GlStateManager.scale(f33, f33, f33);
                                        this.horn.rotationPointX = 0.0F;
                                        this.horn.rotationPointY = 0.0F;
                                        this.horn.isHidden = false;
                                        this.horn.render(0.0625F);
                                        this.horn.isHidden = true;
                                        GlStateManager.popMatrix();
                                    }
                                }
                            }

                            if (cosmetic.getType() == EnumCosmetic.OCELOTTAIL)
                            {
                                this.ocelotTail.rotationPointY = 15.0F;
                                this.ocelotTail.rotationPointZ = 8.0F;
                                this.ocelotTail2.rotationPointY = 20.0F;
                                this.ocelotTail2.rotationPointZ = 14.0F;
                                this.ocelotTail.rotateAngleX = 0.9F;
                                GlStateManager.pushMatrix();

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0F, -0.35F, -0.33F);
                                    ++this.ocelotTail.rotationPointY;
                                    this.ocelotTail2.rotationPointY += -4.0F;
                                    this.ocelotTail2.rotationPointZ += 2.0F;
                                    this.ocelotTail.rotateAngleX = ((float)Math.PI / 2F);
                                    this.ocelotTail2.rotateAngleX = ((float)Math.PI / 2F);
                                }
                                else if (entityIn.isSprinting())
                                {
                                    GlStateManager.translate(0.0F, -0.2F, -0.61F);
                                    this.ocelotTail2.rotationPointY = this.ocelotTail.rotationPointY;
                                    this.ocelotTail2.rotationPointZ += 2.0F;
                                    this.ocelotTail.rotateAngleX = ((float)Math.PI / 2F);
                                    this.ocelotTail2.rotateAngleX = ((float)Math.PI / 2F);
                                    this.ocelotTail2.rotateAngleX = 1.7278761F + ((float)Math.PI / 10F) * MathHelper.cos(p_78088_2_) * p_78088_3_;
                                }

                                if (!entityIn.isSprinting() && !entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0F, -0.35F, -0.61F);
                                    this.ocelotTail2.rotateAngleX = 1.7278761F + ((float)Math.PI / 4F) * MathHelper.cos(p_78088_2_) * p_78088_3_;
                                }
                                else
                                {
                                    this.ocelotTail2.rotateAngleX = 1.7278761F + 0.47123894F * MathHelper.cos(p_78088_2_) * p_78088_3_;
                                }

                                this.ocelotTail.isHidden = false;
                                this.ocelotTail2.isHidden = false;
                                this.ocelotTail.render(scale);
                                this.ocelotTail2.render(scale);
                                this.ocelotTail.isHidden = true;
                                this.ocelotTail2.isHidden = true;
                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.WOLFTAIL)
                            {
                                GlStateManager.pushMatrix();

                                if (entityIn.isSneaking())
                                {
                                    GlStateManager.translate(0.0F, 0.2F, -0.25F);
                                    GlStateManager.rotate(45.0F, 45.0F, 0.0F, 0.0F);
                                }
                                else
                                {
                                    GlStateManager.translate(0.0F, 0.1F, -0.25F);
                                    GlStateManager.rotate(15.0F, 15.0F, 0.0F, 0.0F);
                                }

                                if (cosmetic.getData() != null && cosmetic.getData().equalsIgnoreCase("emotions"))
                                {
                                    float f23 = abstractclientplayer.getHealth();

                                    if (f23 > 20.0F)
                                    {
                                        f23 = 20.0F;
                                    }

                                    if (f23 < 0.0F)
                                    {
                                        f23 = 0.0F;
                                    }

                                    GlStateManager.translate(0.0F, f23 / 80.0F, f23 / 50.0F * -1.0F);
                                    GlStateManager.rotate(f23 * 2.0F, f23 * 2.0F, 0.0F, 0.0F);
                                }

                                this.wolfTail.isHidden = false;
                                this.wolfTail.renderWithRotation(scale);
                                this.wolfTail.isHidden = true;
                                GlStateManager.popMatrix();
                            }

                            if (cosmetic.getType() == EnumCosmetic.WINGS)
                            {
                                GlStateManager.pushMatrix();
                                float f24 = 100.0F;
                                boolean flag3 = this.wingState.containsKey(entityIn.getUniqueID());
                                boolean flag4 = entityIn.onGround;

                                if (!flag4 || flag3)
                                {
                                    f24 = 10.0F;
                                }

                                if (!flag3 && !flag4)
                                {
                                    this.wingState.put(entityIn.getUniqueID(), Long.valueOf(0L));
                                }

                                float f35 = p_78088_3_ + p_78088_4_ / f24;
                                float f36 = p_78088_3_ + p_78088_4_ / 100.0F;
                                float f4 = f35 * (float)Math.PI * 2.0F;
                                float f37 = 0.125F - (float)Math.cos((double)f4) * 0.2F;
                                float f5 = f36 * (float)Math.PI * 2.0F;
                                float f38 = 0.125F - (float)Math.cos((double)f5) * 0.2F;

                                if (this.wingState.containsKey(entityIn.getUniqueID()) && (int)(f37 * 100.0F) == (int)(f38 * 100.0F) && flag4)
                                {
                                    this.wingState.remove(entityIn.getUniqueID());
                                    f24 = 100.0F;
                                }

                                Minecraft.getMinecraft().getTextureManager().bindTexture(enderDragonTextures);
                                GlStateManager.scale(0.0012D * (double)(ConfigManager.settings.wingsScale + 75), 0.0012D * (double)(ConfigManager.settings.wingsScale + 75), 0.0012D * (double)(ConfigManager.settings.wingsScale + 75));
                                GlStateManager.translate(0.0D, -0.3D, 1.1D);
                                GlStateManager.rotate(50.0F, -50.0F, 0.0F, 0.0F);
                                boolean flag5 = false;
                                boolean flag = false;

                                if (cosmetic.getData() != null)
                                {
                                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                                    GL11.glColor3d(cosmetic.a * 0.01D, cosmetic.b * 0.01D, cosmetic.c * 0.01D);

                                    if (cosmetic.a == -1.0D && cosmetic.b == -1.0D && cosmetic.c == -1.0D)
                                    {
                                        int l3 = 600;
                                        flag = Minecraft.getSystemTime() % (long)l3 / (long)(l3 / 2) == 0L;
                                        flag5 = true;

                                        if (flag)
                                        {
                                            GL11.glColor3d(18.0D, 0.0D, 0.0D);
                                        }
                                        else
                                        {
                                            GL11.glColor3d(0.0D, 0.0D, 18.0D);
                                        }
                                    }

                                    if (cosmetic.a == -2.0D && cosmetic.b == -2.0D && cosmetic.c == -2.0D)
                                    {
                                        GL11.glColor3d(18.0D, 18.0D, 0.0D);
                                        flag5 = true;
                                    }
                                }

                                GlStateManager.disableLight(0);
                                GlStateManager.disableLight(1);

                                for (int i4 = 0; i4 < 2; ++i4)
                                {
                                    GlStateManager.enableCull();
                                    float f7 = f35 * (float)Math.PI * 2.0F;
                                    this.wing.rotateAngleX = 0.125F - (float)Math.cos((double)f7) * 0.2F;
                                    this.wing.rotateAngleY = 0.25F;
                                    this.wing.rotateAngleZ = (float)(Math.sin((double)f7) + 1.225D) * 0.3F;
                                    this.wingTip.rotateAngleZ = -((float)(Math.sin((double)(f7 + 2.0F)) + 0.5D)) * 0.75F;
                                    this.wing.isHidden = false;
                                    this.wingTip.isHidden = false;
                                    this.wing.render(scale);
                                    this.wing.isHidden = true;
                                    this.wingTip.isHidden = true;
                                    GlStateManager.scale(-1.0F, 1.0F, 1.0F);

                                    if (i4 == 0)
                                    {
                                        GlStateManager.cullFace(1028);

                                        if (flag5)
                                        {
                                            if (flag)
                                            {
                                                GL11.glColor3d(0.0D, 0.0D, 18.0D);
                                            }
                                            else
                                            {
                                                GL11.glColor3d(18.0D, 0.0D, 0.0D);
                                            }
                                        }
                                    }
                                }

                                GlStateManager.enableLight(0);
                                GlStateManager.enableLight(1);
                                GlStateManager.cullFace(1029);
                                GlStateManager.disableCull();
                                GlStateManager.enableDepth();
                                GlStateManager.popMatrix();
                            }
                        }
                    }

                    Minecraft.getMinecraft().getTextureManager().bindTexture(resourcelocation);
                    GL11.glColor3d(1.0D, 1.0D, 1.0D);
                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                    GlStateManager.resetColor();
                }
            }
        }

        if (LeftHand.use(entityIn))
        {
            GlStateManager.scale(-1.0F, 1.0F, 1.0F);
        }

        GlStateManager.popMatrix();
    }

    public static float getFirstRotationX(AbstractClientPlayer clientPlayer, float partialTicks)
    {
        float f = interpolateRotation(clientPlayer.prevRenderYawOffset, clientPlayer.renderYawOffset, partialTicks);
        float f1 = interpolateRotation(clientPlayer.prevRotationYawHead, clientPlayer.rotationYawHead, partialTicks);
        float f2 = f1 - f;

        if (clientPlayer.isRiding() && clientPlayer.ridingEntity instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)clientPlayer.ridingEntity;
            f = interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
            f2 = f1 - f;
            float f3 = MathHelper.wrapAngleTo180_float(f2);

            if (f3 < -85.0F)
            {
                f3 = -85.0F;
            }

            if (f3 >= 85.0F)
            {
                f3 = 85.0F;
            }

            f = f1 - f3;

            if (f3 * f3 > 2500.0F)
            {
                float f4 = f + f3 * 0.2F;
            }
        }

        return f2;
    }

    public static float getSecondRotationX(AbstractClientPlayer clientPlayer, float partialTicks)
    {
        return clientPlayer.prevRotationPitch + (clientPlayer.rotationPitch - clientPlayer.prevRotationPitch) * partialTicks;
    }

    public void renderDeadmau5Head(float p_178727_1_)
    {
        copyModelAngles(this.bipedHead, this.bipedDeadmau5Head);
        this.bipedDeadmau5Head.rotationPointX = 0.0F;
        this.bipedDeadmau5Head.rotationPointY = 0.0F;
        this.bipedDeadmau5Head.render(p_178727_1_);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity entityIn)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, entityIn);
        this.wolfTail.rotateAngleY = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.wolfTail.rotateAngleX = p_78087_2_;
    }

    public void setInvisible(boolean invisible)
    {
        super.setInvisible(invisible);
        this.bipedDeadmau5Head.showModel = invisible;
        this.wolfTail.showModel = invisible;
        this.wing.showModel = invisible;
        this.wingTip.showModel = invisible;
        this.ocelotTail.showModel = invisible;
        this.ocelotTail2.showModel = invisible;
    }

    public static float interpolateRotation(float par1, float par2, float par3)
    {
        float f;

        for (f = par2 - par1; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return par1 + par3 * f;
    }
}
