/*
 * Copyright (c) 2011.  Peter Lawrey
 *
 * "THE BEER-WARE LICENSE" (Revision 128)
 * As long as you retain this notice you can do whatever you want with this stuff.
 * If we meet some day, and you think this stuff is worth it, you can buy me a beer in return
 * There is no warranty.
 */

package vanilla.java.pointers;

import java.nio.ByteBuffer;

public enum Pointers {
    ;

    public static <T> Pointer<T> of(ByteBuffer buffer, Class<T> tClass) {
        return of(buffer, ClassModel.of(tClass));
    }

    public static <T> Pointer<T> of(ByteBuffer buffer, ClassModel<T> tClass) {
        return new ByteBufferPointer(buffer, (PointerInternal) tClass.createPointer());
    }
}
