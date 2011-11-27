package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class ByteFieldModel implements FieldModel<Byte> {
    private final String name;
    private int offset;

    public ByteFieldModel(String name) {
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
        return 1;
    }

    @Override
    public Byte get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.get(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Byte arg) {
        byteBuffer.put(baseOffset + offset, arg);
    }
}
