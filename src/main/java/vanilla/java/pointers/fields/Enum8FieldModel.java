package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class Enum8FieldModel<E extends Enum<E>> implements FieldModel<E> {
    private final String name;
    private final E[] values;
    private int offset;

    public Enum8FieldModel(String name, E[] values) {
        this.name = name;
        this.values = values;
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
    public E get(ByteBuffer byteBuffer, int baseOffset) {
        byte b = byteBuffer.get(baseOffset + offset);
        if (b == 0) return null;
        return values[(b & 0xff) - 1];
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, E arg) {
        byteBuffer.put(baseOffset + offset, (byte) (1 + arg.ordinal()));
    }
}
