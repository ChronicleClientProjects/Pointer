package vanilla.java.pointers;

import java.nio.ByteBuffer;

public class ByteBufferPointer<T extends PointerInternal> implements Pointer<T> {
    private final ByteBuffer buffer;
    private final T pointerImpl;

    public ByteBufferPointer(ByteBuffer buffer, T pointerImpl) {
        this.buffer = buffer;
        this.pointerImpl = pointerImpl;
        ((PointerInternal) pointerImpl).$byteBuffer$(buffer);
    }

    @Override
    public ByteBuffer buffer() {
        return buffer;
    }

    @Override
    public void index(int index) {
        pointerImpl.$index$(index);
    }

    @Override
    public int index() {
        return pointerImpl.$index$();
    }

    @Override
    public int objectSize() {
        return pointerImpl.$objectSize$();
    }

    @Override
    public int length() {
        return buffer.remaining() / objectSize();
    }

    @Override
    public T get() {
        return pointerImpl;
    }

    @Override
    public boolean hasNext() {
        return (index() + 2) * objectSize() <= buffer.remaining();
    }

    @Override
    public T next() {

        pointerImpl.$index$(pointerImpl.$index$() + 1);
        return pointerImpl;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
