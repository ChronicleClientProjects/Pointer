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
import java.util.Iterator;

public interface Pointer<T> extends Iterator<T> {
    public ByteBuffer buffer();

    public void index(int index);

    public int index();

    public int objectSize();

    public int length();

    T get();

    @Override
    boolean hasNext();

    @Override
    T next();

    /**
     * Not Supported.
     */
    @Override
    void remove();
}
