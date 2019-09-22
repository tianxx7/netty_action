package txx.java.nio_demo;

import io.netty.buffer.ByteBuf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Nio client
 * 客户端
 *
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            //创建客户端
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);

            //连接服务端
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey selectionKey : selectionKeys) {
                    //为true 表示与服务器端已经建立好了连接
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        //是否是连接过程
                        if (client.isConnectionPending()) {
                            //完成连接
                            client.finishConnect();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            //启动一个线程去监听键盘输入
                            executorService.submit(() -> {
                                //等待输入
                                while (true) {
                                    try{
                                        writeBuffer.clear();
                                        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
                                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                                        String sendMessage = bufferedReader.readLine();
                                        writeBuffer.put(sendMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }


                                }
                            });
                        }
                        client.register(selector,SelectionKey.OP_READ);

                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int count = client.read(readBuffer);
                        if (count > 0) {
                            String receivedMessage = new String(readBuffer.array(),0,count);
                            System.out.println(receivedMessage);
                        }
                    }
                }
                selectionKeys.clear();
            }
            //获取键盘输入字符
            //向服务端发送

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
