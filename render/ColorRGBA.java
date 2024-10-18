package jforms.render;

public class ColorRGBA {

    private static final byte byteFactorByte = (byte) 0xFF;
    private static final float byteFactorFloat = (float) byteFactorByte;
    private static final byte offsetRed = 0;
    private static final byte offsetGreen = 8;
    private static final byte offsetBlue = 16;
    private static final byte offsetAlpha = 24;

    protected int value;

    public ColorRGBA() {
        this.value = 0;
    }

    public ColorRGBA(int value) {
        this.value = value;
    }

    public ColorRGBA(java.awt.Color color) {
        this.value = color.getRGB();
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
        return (byte) ((value >> offsetRed) & byteFactorByte);
    }

    public ColorRGBA setRedByte(byte value) {
        this.value = (this.value & 0x00FFFFFF) | ((value & byteFactorByte) << offsetRed);

        return this;
    }

    public byte getGreenByte() {
        return (byte) ((value >> offsetGreen) & byteFactorByte);
    }

    public ColorRGBA setGreenByte(byte value) {
        this.value = (this.value & 0xFF00FFFF) | ((value & byteFactorByte) << offsetGreen);

        return this;
    }

    public byte getBlueByte() {
        return (byte) ((value >> offsetBlue) & byteFactorByte);
    }

    public ColorRGBA setBlueByte(byte value) {
        this.value = (this.value & 0xFFFF00FF) | ((value & byteFactorByte) << offsetBlue);

        return this;
    }

    public byte getAlphaByte() {
        return (byte) ((value >> offsetAlpha) & byteFactorByte);
    }

    public ColorRGBA setAlphaByte(byte value) {
        this.value = (this.value & 0xFFFFFF00) | ((value & byteFactorByte) << offsetAlpha);

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
}
