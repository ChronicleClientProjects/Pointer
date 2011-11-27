package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class BooleanFieldModel implements FieldModel<Boolean> {
    private final String name;
    private int offset;

    public BooleanFieldModel(String name) {
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
    public Boolean get(ByteBuffer byteBuffer, int baseOffset) {
        byte b = byteBuffer.get(baseOffset + offset);
        if (b == -1) return null;
        return b != 0;
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Boolean arg) {
        byteBuffer.put(baseOffset + offset, (byte) (arg == null ? -1 : arg ? 0 : 1));
    }
}
