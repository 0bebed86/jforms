package jforms.render;

public class ColorRGBA {

    protected static final byte byteFactorByte = (byte) 0xFF;
    protected static final float byteFactorFloat = (float) byteFactorByte;

    protected static final byte bitOffsetRed = 0;
    protected static final int bitMaskRed = 0x00FFFFFF;

    protected static final byte bitOffsetGreen = 8;
    protected static final int bitMaskGreen = 0xFF00FFFF;

    protected static final byte bitOffsetBlue = 16;
    protected static final int bitMaskBlue = 0xFFFF00FF;

    protected static final byte bitOffsetAlpha = 24;
    protected static final int bitMaskAlpha = 0xFFFFFF00;

    protected int value;

    public ColorRGBA() {
        this.value = 0;
    }

    public ColorRGBA(int value) {
        this.value = value;
    }

    public ColorRGBA(byte r, byte g, byte b, byte a) {
        this.setValue(r, g, b, a);
    }

    public ColorRGBA(byte[] bytes) {
        this.setValue(bytes);
    }

    public ColorRGBA(float r, float g, float b, float a) {
        this.setValue(r, g, b, a);
    }

    public ColorRGBA(float[] floats) {
        this.setValue(floats);
    }

    public ColorRGBA(ColorRGBA another) {
        this.value = another.value;
    }

    public ColorRGBA(java.awt.Color color) {
        this.value = color.getRGB();
    }

    public ColorRGBA(String name) {
        this(java.awt.Color.getColor(name));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof ColorRGBA)) {
            return false;
        }

        ColorRGBA another = (ColorRGBA) object;

        return this == another || this.value == another.value;
    }

    public int getValue() {
        return value;
    }

    public float[] getValueFloats(float[] store) {
        if (store == null) {
            store = new float[4];
        }

        store[0] = getRedFloat();
        store[1] = getGreenFloat();
        store[2] = getBlueFloat();
        store[3] = getAlphaFloat();

        return store;
    }

    public float[] getValueFloats() {
        return getValueFloats(null);
    }

    public byte[] getValueBytes(byte[] store) {
        if (store == null) {
            store = new byte[4];
        }

        store[0] = getRedByte();
        store[1] = getGreenByte();
        store[2] = getBlueByte();
        store[3] = getAlphaByte();

        return store;
    }

    public byte[] getValueBytes() {
        return getValueBytes(null);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValue(float r, float g, float b, float a) {
        setRedFloat(r);
        setGreenFloat(g);
        setBlueFloat(b);
        setAlphaFloat(a);
    }

    public void setValue(float[] floats) {
        if (floats.length < 4) {
            return;
        }

        setValue(floats[0], floats[1], floats[2], floats[3]);
    }

    public void setValue(byte r, byte g, byte b, byte a) {
        setRedByte(r);
        setGreenByte(g);
        setBlueByte(b);
        setAlphaByte(a);
    }

    public void setValue(byte[] bytes) {
        if (bytes.length < 4) {
            return;
        }

        setValue(bytes[0], bytes[1], bytes[2], bytes[3]);
    }

    public byte getRedByte() {
        return (byte) ((value >> bitMaskRed) & byteFactorByte);
    }

    public ColorRGBA setRedByte(byte value) {
        this.value = (this.value & bitMaskRed) | ((value & byteFactorByte) << bitMaskRed);

        return this;
    }

    public byte getGreenByte() {
        return (byte) ((value >> bitOffsetGreen) & byteFactorByte);
    }

    public ColorRGBA setGreenByte(byte value) {
        this.value = (this.value & bitMaskGreen) | ((value & byteFactorByte) << bitOffsetGreen);

        return this;
    }

    public byte getBlueByte() {
        return (byte) ((value >> bitOffsetBlue) & byteFactorByte);
    }

    public ColorRGBA setBlueByte(byte value) {
        this.value = (this.value & bitMaskBlue) | ((value & byteFactorByte) << bitOffsetBlue);

        return this;
    }

    public byte getAlphaByte() {
        return (byte) ((value >> bitOffsetAlpha) & byteFactorByte);
    }

    public ColorRGBA setAlphaByte(byte value) {
        this.value = (this.value & bitMaskAlpha) | ((value & byteFactorByte) << bitOffsetAlpha);

        return this;
    }

    public float getRedFloat() {
        return (float) getRedByte() / byteFactorFloat;
    }

    public ColorRGBA setRedFloat(float value) {
        return setRedByte((byte) (value * byteFactorFloat));
    }

    public float getGreenFloat() {
        return (float) getGreenByte() / byteFactorFloat;
    }

    public ColorRGBA setGreenFloat(float value) {
        return setGreenByte((byte) (value * byteFactorFloat));
    }

    public float getBlueFloat() {
        return (float) getBlueByte() / byteFactorFloat;
    }

    public ColorRGBA setBlueFloat(float value) {
        return setBlueByte((byte) (value * byteFactorFloat));
    }

    public float getAlphaFloat() {
        return (float) getAlphaByte() / byteFactorFloat;
    }

    public ColorRGBA setAlphaFloat(float value) {
        return setAlphaByte((byte) (value * byteFactorFloat));
    }

    public static ColorRGBA fromValue(int value) {
        return new ColorRGBA(value);
    }

    public static ColorRGBA fromAnother(ColorRGBA another) {
        return new ColorRGBA(another);
    }

    public static ColorRGBA fromAWT(java.awt.Color color) {
        return new ColorRGBA(color);
    }

    public static ColorRGBA fromName(String name) {
        return new ColorRGBA(name);
    }
}
