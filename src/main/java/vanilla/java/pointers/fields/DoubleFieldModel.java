package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class DoubleFieldModel implements FieldModel<Double> {
    private final String name;
    private int offset;

    public DoubleFieldModel(String name) {
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
        return 8;
    }

    @Override
    public Double get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getDouble(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Double arg) {
        byteBuffer.putDouble(baseOffset + offset, arg);
    }
}
