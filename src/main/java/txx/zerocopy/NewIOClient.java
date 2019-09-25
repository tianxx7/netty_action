package txx.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 客户端上传文件到服务端
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);

        String fileName = "";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();
        //可读取的ByteChannel ,把信息读过来
        //fileChannel.transferFrom()

        //如果文件很大,是否会占用很多内存
        long transferTo = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送字节数:" + transferTo + ",耗时:" + (System.currentTimeMillis() - startTime));
    }
}
