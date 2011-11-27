package vanilla.java.pointers;

import org.junit.Test;

import java.nio.ByteBuffer;

import static junit.framework.Assert.assertEquals;

public class ByteBufferPointerTest {
    @Test
    public void testPtr() {
        ByteBuffer bb = ByteBuffer.allocate(64 * 1024 * 1024);
        final Pointer<Primitives> ptr = Pointers.of(bb, Primitives.class);
        int count = 0;
        long start = System.nanoTime();
        while (ptr.hasNext()) {
            final Primitives prims = ptr.next();
            prims.setBoolean(null);
            prims.setByte((byte) 1);
            prims.setChar('2');
            prims.setShort((short) 3);
            prims.setInt(4);
            prims.setFloat(5.5f);
            prims.setLong(6);
            prims.setDouble(7.7);
            prims.setEnum(ABC.B);

            assertEquals(null, prims.getBoolean());
            assertEquals((byte) 1, prims.getByte());
            assertEquals('2', prims.getChar());
            assertEquals((short) 3, prims.getShort());
            assertEquals(4, prims.getInt());
            assertEquals(5.5f, prims.getFloat());
            assertEquals(6, prims.getLong());
            assertEquals(7.7, prims.getDouble());
            assertEquals(ABC.B, prims.getEnum());

            count++;
        }
        long time = System.nanoTime() - start;
        System.out.printf("Avg time %,d ns%n", time / count);
    }
}

interface Primitives {
    public void setBoolean(Boolean b);

    public Boolean getBoolean();

    public void setByte(byte a);

    public byte getByte();

    public void setChar(char ch);

    public char getChar();

    public void setShort(short s);

    public short getShort();

    public void setInt(int a);

    public int getInt();

    public void setFloat(float f);

    public float getFloat();

    public void setLong(long l);

    public long getLong();

    public void setDouble(double a);

    public double getDouble();

    public void setEnum(ABC abc);

    public ABC getEnum();
}

enum ABC {
    A, B, C;
}
