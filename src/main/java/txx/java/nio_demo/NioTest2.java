package txx.java.nio_demo;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class NioTest2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("README.md");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);//512字节
        fileChannel.read(buffer);//只读512字节的内容,后面的文本不读取
        buffer.flip();
        byte[] bytes = new byte[512];
        while (buffer.remaining() > 0) {
            buffer.get(bytes,0,buffer.limit());
            System.out.println("Character: " + new String(bytes, Charset.forName("UTF-8")));//不支持中文,中文需要编码
        }
        buffer = null;
        fileInputStream.close();
    }
}
