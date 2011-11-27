package vanilla.java.pointers.fields;


import vanilla.java.pointers.FieldModel;

import java.nio.ByteBuffer;

public class CharFieldModel implements FieldModel<Character> {
    private final String name;
    private int offset;

    public CharFieldModel(String name) {
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
    public Character get(ByteBuffer byteBuffer, int baseOffset) {
        return byteBuffer.getChar(baseOffset + offset);
    }

    @Override
    public void set(ByteBuffer byteBuffer, int baseOffset, Character arg) {
        byteBuffer.putChar(baseOffset + offset, arg);
    }
}
