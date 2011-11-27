package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class FloatFieldModel implements FieldModel<Float> {
    private final String name;
    private int offset;

    public FloatFieldModel(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void offset(int offset) {
        this.offset = offset;
    }

    @Override
    public int fieldSize() {
        return 4;
    }

    @Override
    public Float get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getFloat(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Float arg) {
        byteBuffer.putFloat(baseOffset + offset, arg);
    }
}
