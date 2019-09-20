package txx.java.nio_demo;

import java.nio.ByteBuffer;

/**
 * 只读buffer,我们可以随时将一个普通buffer调用asReadOnlyBUffer方法返回一个只读buffer,
 * 但是不能将一个只读buffer转换为读写buffer
 */public class NioTest7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

    }
}
