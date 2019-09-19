package txx.java.nio_demo;

import java.nio.ByteBuffer;

/**
 * 类型化的put和get
 */
public class NioTest5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(129);
        buffer.putLong(999999999L);
        buffer.putDouble(12.46577878);
        buffer.putChar('我');
        buffer.putShort((short)35);
        buffer.putChar('时');

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getChar());
    }
}
