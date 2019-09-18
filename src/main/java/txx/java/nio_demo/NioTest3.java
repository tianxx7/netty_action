package txx.java.nio_demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 写文件
 */
public class NioTest3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Niotest3.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(512);

        byte[] message = "hello world ,txx".getBytes();

        for (int i = 0; i < message.length; i++) {
            buffer.put(message[i]);
        }

        buffer.flip();
        channel.write(buffer);
        fileOutputStream.close();
    }
}
