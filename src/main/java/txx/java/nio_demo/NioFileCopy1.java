package txx.java.nio_demo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * mmap
 */
public class NioFileCopy1 {
    public static void main(String[] args) throws IOException {
        String inputFile = "CopyFile.txt";
        String outPutFile = "OutCopyFile.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        //记住,没有只写模式
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outPutFile,"rw");

        long inputFileLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputDate = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputFileLength);

        //字符串编解码
        //为什么iso-8859-1中文没有乱码呢
        //原始文本utf-8 按iso-8859-1编解码后 还是按utf-8去查看文本,当然不影响
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer decode = decoder.decode(inputDate);
        ByteBuffer outputData = encoder.encode(decode);

        outputFileChannel.write(outputData);
        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}
