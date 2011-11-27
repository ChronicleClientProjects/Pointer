package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class ShortFieldModel implements FieldModel<Short> {
    private final String name;
    private int offset;

    public ShortFieldModel(String name) {
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
        return 2;
    }

    @Override
    public Short get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getShort(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Short arg) {
        byteBuffer.putShort(baseOffset + offset, arg);
    }
}
