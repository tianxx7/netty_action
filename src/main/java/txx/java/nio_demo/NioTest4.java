package txx.java.nio_demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("README.md");
        FileOutputStream outputStream = new FileOutputStream("output.txt");
        FileChannel inChannel = inputStream.getChannel();
        FileChannel outChannel = outputStream.getChannel();

        ByteBuffer buff = ByteBuffer.allocate(1024);
        while (true) {
            buff.clear();
            int read = inChannel.read(buff);
            System.out.println("read:" + read);
            if (-1 == read) {
                break;
            }

            buff.flip();
            outChannel.write(buff);
        }
        inputStream.close();
        outputStream.close();
    }
}
