package net.minecraft.client.renderer;

import java.nio.FloatBuffer;
import org.lwjgl.opengl.GL11;

public class GlStateManager
{
    private static GlStateManager.AlphaState alphaState = new GlStateManager.AlphaState();
    private static GlStateManager.BooleanState lightingState = new GlStateManager.BooleanState(2896);
    private static GlStateManager.BooleanState[] lightState = new GlStateManager.BooleanState[8];
    private static GlStateManager.ColorMaterialState colorMaterialState = new GlStateManager.ColorMaterialState();
    private static GlStateManager.BlendState blendState = new GlStateManager.BlendState();
    private static GlStateManager.DepthState depthState = new GlStateManager.DepthState();
    private static GlStateManager.FogState fogState = new GlStateManager.FogState();
    private static GlStateManager.CullState cullState = new GlStateManager.CullState();
    private static GlStateManager.PolygonOffsetState polygonOffsetState = new GlStateManager.PolygonOffsetState();
    private static GlStateManager.ColorLogicState colorLogicState = new GlStateManager.ColorLogicState();
    private static GlStateManager.TexGenState texGenState = new GlStateManager.TexGenState();
    private static GlStateManager.ClearState clearState = new GlStateManager.ClearState();
    private static GlStateManager.StencilState stencilState = new GlStateManager.StencilState();
    private static GlStateManager.BooleanState normalizeState = new GlStateManager.BooleanState(2977);
    private static int activeTextureUnit = 0;
    private static GlStateManager.TextureState[] textureState = new GlStateManager.TextureState[8];
    private static int activeShadeModel = 7425;
    private static GlStateManager.BooleanState rescaleNormalState = new GlStateManager.BooleanState(32826);
    private static GlStateManager.ColorMask colorMaskState = new GlStateManager.ColorMask();
    private static GlStateManager.Color colorState = new GlStateManager.Color();

    public static void pushAttrib()
    {
        GL11.glPushAttrib(8256);
    }

    public static void popAttrib()
    {
        GL11.glPopAttrib();
    }

    public static void disableAlpha()
    {
        alphaState.field_179208_a.setDisabled();
    }

    public static void enableAlpha()
    {
        alphaState.field_179208_a.setEnabled();
    }

    public static void alphaFunc(int func, float ref)
    {
        if (func != alphaState.func || ref != alphaState.ref)
        {
            alphaState.func = func;
            alphaState.ref = ref;
            GL11.glAlphaFunc(func, ref);
        }
    }

    public static void enableLighting()
    {
        lightingState.setEnabled();
    }

    public static void disableLighting()
    {
        lightingState.setDisabled();
    }

    public static void enableLight(int light)
    {
        lightState[light].setEnabled();
    }

    public static void disableLight(int light)
    {
        lightState[light].setDisabled();
    }

    public static void enableColorMaterial()
    {
        colorMaterialState.field_179191_a.setEnabled();
    }

    public static void disableColorMaterial()
    {
        colorMaterialState.field_179191_a.setDisabled();
    }

    public static void colorMaterial(int face, int mode)
    {
        if (face != colorMaterialState.field_179189_b || mode != colorMaterialState.field_179190_c)
        {
            colorMaterialState.field_179189_b = face;
            colorMaterialState.field_179190_c = mode;
            GL11.glColorMaterial(face, mode);
        }
    }

    public static void disableDepth()
    {
        depthState.depthTest.setDisabled();
    }

    public static void enableDepth()
    {
        depthState.depthTest.setEnabled();
    }

    public static void depthFunc(int depthFunc)
    {
        if (depthFunc != depthState.depthFunc)
        {
            depthState.depthFunc = depthFunc;
            GL11.glDepthFunc(depthFunc);
        }
    }

    public static void depthMask(boolean flagIn)
    {
        if (flagIn != depthState.maskEnabled)
        {
            depthState.maskEnabled = flagIn;
            GL11.glDepthMask(flagIn);
        }
    }

    public static void disableBlend()
    {
        blendState.field_179213_a.setDisabled();
    }

    public static void enableBlend()
    {
        blendState.field_179213_a.setEnabled();
    }

    public static void blendFunc(int srcFactor, int dstFactor)
    {
        if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor)
        {
            blendState.srcFactor = srcFactor;
            blendState.dstFactor = dstFactor;
            GL11.glBlendFunc(srcFactor, dstFactor);
        }
    }

    public static void tryBlendFuncSeparate(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha)
    {
        if (srcFactor != blendState.srcFactor || dstFactor != blendState.dstFactor || srcFactorAlpha != blendState.srcFactorAlpha || dstFactorAlpha != blendState.dstFactorAlpha)
        {
            blendState.srcFactor = srcFactor;
            blendState.dstFactor = dstFactor;
            blendState.srcFactorAlpha = srcFactorAlpha;
            blendState.dstFactorAlpha = dstFactorAlpha;
            OpenGlHelper.glBlendFunc(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
        }
    }

    public static void enableFog()
    {
        fogState.field_179049_a.setEnabled();
    }

    public static void disableFog()
    {
        fogState.field_179049_a.setDisabled();
    }

    public static void setFog(int param)
    {
        if (param != fogState.field_179047_b)
        {
            fogState.field_179047_b = param;
            GL11.glFogi(GL11.GL_FOG_MODE, param);
        }
    }

    public static void setFogDensity(float param)
    {
        if (param != fogState.field_179048_c)
        {
            fogState.field_179048_c = param;
            GL11.glFogf(GL11.GL_FOG_DENSITY, param);
        }
    }

    public static void setFogStart(float param)
    {
        if (param != fogState.field_179045_d)
        {
            fogState.field_179045_d = param;
            GL11.glFogf(GL11.GL_FOG_START, param);
        }
    }

    public static void setFogEnd(float param)
    {
        if (param != fogState.field_179046_e)
        {
            fogState.field_179046_e = param;
            GL11.glFogf(GL11.GL_FOG_END, param);
        }
    }

    public static void enableCull()
    {
        cullState.field_179054_a.setEnabled();
    }

    public static void disableCull()
    {
        cullState.field_179054_a.setDisabled();
    }

    public static void cullFace(int mode)
    {
        if (mode != cullState.field_179053_b)
        {
            cullState.field_179053_b = mode;
            GL11.glCullFace(mode);
        }
    }

    public static void enablePolygonOffset()
    {
        polygonOffsetState.field_179044_a.setEnabled();
    }

    public static void disablePolygonOffset()
    {
        polygonOffsetState.field_179044_a.setDisabled();
    }

    public static void doPolygonOffset(float factor, float units)
    {
        if (factor != polygonOffsetState.field_179043_c || units != polygonOffsetState.field_179041_d)
        {
            polygonOffsetState.field_179043_c = factor;
            polygonOffsetState.field_179041_d = units;
            GL11.glPolygonOffset(factor, units);
        }
    }

    public static void enableColorLogic()
    {
        colorLogicState.field_179197_a.setEnabled();
    }

    public static void disableColorLogic()
    {
        colorLogicState.field_179197_a.setDisabled();
    }

    public static void colorLogicOp(int opcode)
    {
        if (opcode != colorLogicState.field_179196_b)
        {
            colorLogicState.field_179196_b = opcode;
            GL11.glLogicOp(opcode);
        }
    }

    public static void enableTexGenCoord(GlStateManager.TexGen p_179087_0_)
    {
        texGenCoord(p_179087_0_).field_179067_a.setEnabled();
    }

    public static void disableTexGenCoord(GlStateManager.TexGen p_179100_0_)
    {
        texGenCoord(p_179100_0_).field_179067_a.setDisabled();
    }

    public static void texGen(GlStateManager.TexGen p_179149_0_, int p_179149_1_)
    {
        GlStateManager.TexGenCoord glstatemanager$texgencoord = texGenCoord(p_179149_0_);

        if (p_179149_1_ != glstatemanager$texgencoord.field_179066_c)
        {
            glstatemanager$texgencoord.field_179066_c = p_179149_1_;
            GL11.glTexGeni(glstatemanager$texgencoord.field_179065_b, GL11.GL_TEXTURE_GEN_MODE, p_179149_1_);
        }
    }

    public static void func_179105_a(GlStateManager.TexGen p_179105_0_, int pname, FloatBuffer params)
    {
        GL11.glTexGen(texGenCoord(p_179105_0_).field_179065_b, pname, params);
    }

    private static GlStateManager.TexGenCoord texGenCoord(GlStateManager.TexGen p_179125_0_)
    {
        switch (p_179125_0_)
        {
            case S:
                return texGenState.field_179064_a;

            case T:
                return texGenState.field_179062_b;

            case R:
                return texGenState.field_179063_c;

            case Q:
                return texGenState.field_179061_d;

            default:
                return texGenState.field_179064_a;
        }
    }

    public static void setActiveTexture(int texture)
    {
        if (activeTextureUnit != texture - OpenGlHelper.defaultTexUnit)
        {
            activeTextureUnit = texture - OpenGlHelper.defaultTexUnit;
            OpenGlHelper.setActiveTexture(texture);
        }
    }

    public static void enableTexture2D()
    {
        textureState[activeTextureUnit].texture2DState.setEnabled();
    }

    public static void disableTexture2D()
    {
        textureState[activeTextureUnit].texture2DState.setDisabled();
    }

    public static int generateTexture()
    {
        return GL11.glGenTextures();
    }

    public static void deleteTexture(int texture)
    {
        GL11.glDeleteTextures(texture);

        for (GlStateManager.TextureState glstatemanager$texturestate : textureState)
        {
            if (glstatemanager$texturestate.textureName == texture)
            {
                glstatemanager$texturestate.textureName = -1;
            }
        }
    }

    public static void bindTexture(int texture)
    {
        if (texture != textureState[activeTextureUnit].textureName)
        {
            textureState[activeTextureUnit].textureName = texture;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
        }
    }

    public static void enableNormalize()
    {
        normalizeState.setEnabled();
    }

    public static void disableNormalize()
    {
        normalizeState.setDisabled();
    }

    public static void shadeModel(int mode)
    {
        if (mode != activeShadeModel)
        {
            activeShadeModel = mode;
            GL11.glShadeModel(mode);
        }
    }

    public static void enableRescaleNormal()
    {
        rescaleNormalState.setEnabled();
    }

    public static void disableRescaleNormal()
    {
        rescaleNormalState.setDisabled();
    }

    public static void viewport(int x, int y, int width, int height)
    {
        GL11.glViewport(x, y, width, height);
    }

    public static void colorMask(boolean red, boolean green, boolean blue, boolean alpha)
    {
        if (red != colorMaskState.red || green != colorMaskState.green || blue != colorMaskState.blue || alpha != colorMaskState.alpha)
        {
            colorMaskState.red = red;
            colorMaskState.green = green;
            colorMaskState.blue = blue;
            colorMaskState.alpha = alpha;
            GL11.glColorMask(red, green, blue, alpha);
        }
    }

    public static void clearDepth(double depth)
    {
        if (depth != clearState.field_179205_a)
        {
            clearState.field_179205_a = depth;
            GL11.glClearDepth(depth);
        }
    }

    public static void clearColor(float red, float green, float blue, float alpha)
    {
        if (red != clearState.field_179203_b.red || green != clearState.field_179203_b.green || blue != clearState.field_179203_b.blue || alpha != clearState.field_179203_b.alpha)
        {
            clearState.field_179203_b.red = red;
            clearState.field_179203_b.green = green;
            clearState.field_179203_b.blue = blue;
            clearState.field_179203_b.alpha = alpha;
            GL11.glClearColor(red, green, blue, alpha);
        }
    }

    public static void clear(int mask)
    {
        GL11.glClear(mask);
    }

    public static void matrixMode(int mode)
    {
        GL11.glMatrixMode(mode);
    }

    public static void loadIdentity()
    {
        GL11.glLoadIdentity();
    }

    public static void pushMatrix()
    {
        GL11.glPushMatrix();
    }

    public static void popMatrix()
    {
        GL11.glPopMatrix();
    }

    public static void getFloat(int pname, FloatBuffer params)
    {
        GL11.glGetFloat(pname, params);
    }

    public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar)
    {
        GL11.glOrtho(left, right, bottom, top, zNear, zFar);
    }

    public static void rotate(float angle, float x, float y, float z)
    {
        GL11.glRotatef(angle, x, y, z);
    }

    public static void scale(float x, float y, float z)
    {
        GL11.glScalef(x, y, z);
    }

    public static void scale(double x, double y, double z)
    {
        GL11.glScaled(x, y, z);
    }

    public static void translate(float x, float y, float z)
    {
        GL11.glTranslatef(x, y, z);
    }

    public static void translate(double x, double y, double z)
    {
        GL11.glTranslated(x, y, z);
    }

    public static void multMatrix(FloatBuffer matrix)
    {
        GL11.glMultMatrix(matrix);
    }

    public static void color(float colorRed, float colorGreen, float colorBlue, float colorAlpha)
    {
        if (colorRed != colorState.red || colorGreen != colorState.green || colorBlue != colorState.blue || colorAlpha != colorState.alpha)
        {
            colorState.red = colorRed;
            colorState.green = colorGreen;
            colorState.blue = colorBlue;
            colorState.alpha = colorAlpha;
            GL11.glColor4f(colorRed, colorGreen, colorBlue, colorAlpha);
        }
    }

    public static void color(float colorRed, float colorGreen, float colorBlue)
    {
        color(colorRed, colorGreen, colorBlue, 1.0F);
    }

    public static void resetColor()
    {
        colorState.red = colorState.green = colorState.blue = colorState.alpha = -1.0F;
    }

    public static void callList(int list)
    {
        GL11.glCallList(list);
    }

    static
    {
        for (int i = 0; i < 8; ++i)
        {
            lightState[i] = new GlStateManager.BooleanState(16384 + i);
        }

        for (int j = 0; j < 8; ++j)
        {
            textureState[j] = new GlStateManager.TextureState();
        }
    }

    static class AlphaState
    {
        public GlStateManager.BooleanState field_179208_a;
        public int func;
        public float ref;

        private AlphaState()
        {
            this.field_179208_a = new GlStateManager.BooleanState(3008);
            this.func = 519;
            this.ref = -1.0F;
        }
    }

    static class BlendState
    {
        public GlStateManager.BooleanState field_179213_a;
        public int srcFactor;
        public int dstFactor;
        public int srcFactorAlpha;
        public int dstFactorAlpha;

        private BlendState()
        {
            this.field_179213_a = new GlStateManager.BooleanState(3042);
            this.srcFactor = 1;
            this.dstFactor = 0;
            this.srcFactorAlpha = 1;
            this.dstFactorAlpha = 0;
        }
    }

    static class BooleanState
    {
        private final int capability;
        private boolean currentState = false;

        public BooleanState(int capabilityIn)
        {
            this.capability = capabilityIn;
        }

        public void setDisabled()
        {
            this.setState(false);
        }

        public void setEnabled()
        {
            this.setState(true);
        }

        public void setState(boolean state)
        {
            if (state != this.currentState)
            {
                this.currentState = state;

                if (state)
                {
                    GL11.glEnable(this.capability);
                }
                else
                {
                    GL11.glDisable(this.capability);
                }
            }
        }
    }

    static class ClearState
    {
        public double field_179205_a;
        public GlStateManager.Color field_179203_b;
        public int field_179204_c;

        private ClearState()
        {
            this.field_179205_a = 1.0D;
            this.field_179203_b = new GlStateManager.Color(0.0F, 0.0F, 0.0F, 0.0F);
            this.field_179204_c = 0;
        }
    }

    static class Color
    {
        public float red = 1.0F;
        public float green = 1.0F;
        public float blue = 1.0F;
        public float alpha = 1.0F;

        public Color()
        {
        }

        public Color(float redIn, float greenIn, float blueIn, float alphaIn)
        {
            this.red = redIn;
            this.green = greenIn;
            this.blue = blueIn;
            this.alpha = alphaIn;
        }
    }

    static class ColorLogicState
    {
        public GlStateManager.BooleanState field_179197_a;
        public int field_179196_b;

        private ColorLogicState()
        {
            this.field_179197_a = new GlStateManager.BooleanState(3058);
            this.field_179196_b = 5379;
        }
    }

    static class ColorMask
    {
        public boolean red;
        public boolean green;
        public boolean blue;
        public boolean alpha;

        private ColorMask()
        {
            this.red = true;
            this.green = true;
            this.blue = true;
            this.alpha = true;
        }
    }

    static class ColorMaterialState
    {
        public GlStateManager.BooleanState field_179191_a;
        public int field_179189_b;
        public int field_179190_c;

        private ColorMaterialState()
        {
            this.field_179191_a = new GlStateManager.BooleanState(2903);
            this.field_179189_b = 1032;
            this.field_179190_c = 5634;
        }
    }

    static class CullState
    {
        public GlStateManager.BooleanState field_179054_a;
        public int field_179053_b;

        private CullState()
        {
            this.field_179054_a = new GlStateManager.BooleanState(2884);
            this.field_179053_b = 1029;
        }
    }

    static class DepthState
    {
        public GlStateManager.BooleanState depthTest;
        public boolean maskEnabled;
        public int depthFunc;

        private DepthState()
        {
            this.depthTest = new GlStateManager.BooleanState(2929);
            this.maskEnabled = true;
            this.depthFunc = 513;
        }
    }

    static class FogState
    {
        public GlStateManager.BooleanState field_179049_a;
        public int field_179047_b;
        public float field_179048_c;
        public float field_179045_d;
        public float field_179046_e;

        private FogState()
        {
            this.field_179049_a = new GlStateManager.BooleanState(2912);
            this.field_179047_b = 2048;
            this.field_179048_c = 1.0F;
            this.field_179045_d = 0.0F;
            this.field_179046_e = 1.0F;
        }
    }

    static class PolygonOffsetState
    {
        public GlStateManager.BooleanState field_179044_a;
        public GlStateManager.BooleanState field_179042_b;
        public float field_179043_c;
        public float field_179041_d;

        private PolygonOffsetState()
        {
            this.field_179044_a = new GlStateManager.BooleanState(32823);
            this.field_179042_b = new GlStateManager.BooleanState(10754);
            this.field_179043_c = 0.0F;
            this.field_179041_d = 0.0F;
        }
    }

    static class StencilFunc
    {
        public int field_179081_a;
        public int field_179079_b;
        public int field_179080_c;

        private StencilFunc()
        {
            this.field_179081_a = 519;
            this.field_179079_b = 0;
            this.field_179080_c = -1;
        }
    }

    static class StencilState
    {
        public GlStateManager.StencilFunc field_179078_a;
        public int field_179076_b;
        public int field_179077_c;
        public int field_179074_d;
        public int field_179075_e;

        private StencilState()
        {
            this.field_179078_a = new GlStateManager.StencilFunc();
            this.field_179076_b = -1;
            this.field_179077_c = 7680;
            this.field_179074_d = 7680;
            this.field_179075_e = 7680;
        }
    }

    public static enum TexGen
    {
        S,
        T,
        R,
        Q;
    }

    static class TexGenCoord
    {
        public GlStateManager.BooleanState field_179067_a;
        public int field_179065_b;
        public int field_179066_c = -1;

        public TexGenCoord(int p_i46254_1_, int p_i46254_2_)
        {
            this.field_179065_b = p_i46254_1_;
            this.field_179067_a = new GlStateManager.BooleanState(p_i46254_2_);
        }
    }

    static class TexGenState
    {
        public GlStateManager.TexGenCoord field_179064_a;
        public GlStateManager.TexGenCoord field_179062_b;
        public GlStateManager.TexGenCoord field_179063_c;
        public GlStateManager.TexGenCoord field_179061_d;

        private TexGenState()
        {
            this.field_179064_a = new GlStateManager.TexGenCoord(8192, 3168);
            this.field_179062_b = new GlStateManager.TexGenCoord(8193, 3169);
            this.field_179063_c = new GlStateManager.TexGenCoord(8194, 3170);
            this.field_179061_d = new GlStateManager.TexGenCoord(8195, 3171);
        }
    }

    static class TextureState
    {
        public GlStateManager.BooleanState texture2DState;
        public int textureName;

        private TextureState()
        {
            this.texture2DState = new GlStateManager.BooleanState(3553);
            this.textureName = 0;
        }
    }
}
