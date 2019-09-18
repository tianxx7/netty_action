package txx.java.nio_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("README.md");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);//512字节
        fileChannel.read(buffer);//只读512字节的内容,后面的文本不读取
        buffer.flip();
        while (buffer.remaining() > 0) {
            byte b = buffer.get();
            System.out.println("Character: " + (char)b);//不支持中文,中文需要编码
        }
        buffer.clear();
        buffer = null;
        fileInputStream.close();
    }
}
