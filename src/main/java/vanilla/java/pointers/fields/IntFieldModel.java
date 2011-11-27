package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class IntFieldModel implements FieldModel<Integer> {
    private final String name;
    private int offset;

    public IntFieldModel(String name) {
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
    public Integer get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getInt(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Integer arg) {
        byteBuffer.putInt(baseOffset + offset, arg);
    }
}
