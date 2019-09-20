package txx.java.nio_demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁
 * 共享锁
 * 排他锁
 */
public class NioTest9_FileLock {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("Nio9.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        FileLock lock = channel.lock(0, 3, true);

        //锁是否有效
        System.out.println("valid:" + lock.isValid());
        System.out.println("lock type:" + lock.isShared());
        lock.release();
        randomAccessFile.close();
    }
}
