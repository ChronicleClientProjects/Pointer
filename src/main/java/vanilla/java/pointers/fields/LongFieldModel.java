package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class LongFieldModel implements FieldModel<Long> {
    private final String name;
    private int offset;

    public LongFieldModel(String name) {
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
    public Long get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getLong(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Long arg) {
        byteBuffer.putLong(baseOffset + offset, arg);
    }
}
