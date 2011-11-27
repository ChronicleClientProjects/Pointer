package vanilla.java.pointers;


import java.nio.ByteBuffer;

public interface FieldModel<T> {
    String name();

    void offset(int offset);

    int fieldSize();

    T get(ByteBuffer byteBuffer, int baseOffset);

    void set(ByteBuffer byteBuffer, int baseOffset, T arg);
}
