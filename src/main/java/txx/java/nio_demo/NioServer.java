package txx.java.nio_demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 聊天程序服务端
 * 使用一个通道(一个channel)
 * 多个客户端连接同一个通道
 *  与传统的io编程不同的是服务端只有一个线程
 *  接收消息,向多个客户端进行分发
 *  服务端需要保存客户端连接,可以是socketChannel
 *
 *  nio编程范式,模板代码
 *
 */
public class NioServer {
    private static Map<String,SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        //通过ServerSocketChannel获取服务端ServerSocket对象
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try{
                //返回的是selector关注的事件的数量
                int select = selector.select();
                //获取感兴趣的事件的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                //这里没有用while(iterator.hasNext())所以方法内不用remove();
                //不对,上一句话的想法不对
                //底层实现是迭代器,需要remove()
                selectionKeys.forEach(selectionKey -> {
                    //对应客户端的socket对象
                    final SocketChannel client;

                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key,client);
                        } else if (selectionKey.isReadable()) {
                            client = (SocketChannel) selectionKey.channel();
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            int count = client.read(readBuffer);
                            if (count == -1) {
                                client.close();
                            }
                            if (count > 0) {
                                readBuffer.flip();
                                Charset charset = Charset.forName("UTF-8");
                                //转编码
                                String receivedMessage = String.valueOf(charset.decode(readBuffer).array());
                                System.out.println(client + ":" + receivedMessage);
                                //发送消息的人的key
                                String senderKey = null;
                                for (Map.Entry<String,SocketChannel> entry: clientMap.entrySet()) {
                                    if (client == entry.getValue()) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }

                                //向其他客户发送消息
                                for (Map.Entry<String,SocketChannel> entry:clientMap.entrySet()) {
                                    SocketChannel value = entry.getValue();
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                    writeBuffer.put((senderKey + ":" + receivedMessage).getBytes());
                                    writeBuffer.flip();
                                    value.write(writeBuffer);
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                //相当于iterator.remove();
                selectionKeys.clear();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
