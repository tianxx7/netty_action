package txx.java.nio_demo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/***
 * 内存映射文件
 * MappedByteBuffer
 */
public class NioTest8 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("Nio8.txt", "rws");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        map.put(0,(byte)'s');
        map.put(3, (byte) 's');
        randomAccessFile.close();
    }
}
