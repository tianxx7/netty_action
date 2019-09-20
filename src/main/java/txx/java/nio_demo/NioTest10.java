package txx.java.nio_demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于buffer的Scattering和gathering
 *  分散,收集
 *  linux测试命令: nc ip:port hellowor //回车也占用一个字节
 *  netcat
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        //服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        //阻塞
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            //读取信息
            int bytesRead = 0;
            while (bytesRead < messageLength) {
                long r = socketChannel.read(buffers);
                bytesRead += r;
                System.out.println("bytesRead:" + bytesRead);
                Arrays.asList(buffers).stream().map(buffer -> "position: " + buffer.position() + ",limit: " + buffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });
            long byteWriten = 0;
            while (byteWriten < messageLength) {
                long w = socketChannel.write(buffers);
                byteWriten += w;
            }
            Arrays.asList(buffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("bytesRead:" + bytesRead + ",bytesWritten:" + byteWriten + ",messageLength:" + messageLength);
        }

    }
}
