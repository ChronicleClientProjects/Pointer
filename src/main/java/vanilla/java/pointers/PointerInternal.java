package vanilla.java.pointers;

import java.nio.ByteBuffer;

public interface PointerInternal {
    void $byteBuffer$(ByteBuffer buffer);

    void $index$(int index);

    int $index$();

    int $objectSize$();
}
