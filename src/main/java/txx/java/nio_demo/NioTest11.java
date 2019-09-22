package txx.java.nio_demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * selector 概念
 * 1.如果 监听的10300端口的ServerSocketChannel remove() 那么SelectedKeys中和keys()中是否还有该ServerSocketChannel
 *  iterator.remove() 后会从selectedKeys()中移除,keys()不受影响,注册的感兴趣的事件越多,keys的集合就越大
 * 2.如果客户端关闭,服务端的channel会怎样
 *  没有处理关闭的话,下面示例代码会一直输出:
 *     numbers:1
 *     selectionKeys:[sun.nio.ch.SelectionKeyImpl@47c62251]
 *     读取: 0, 来自于:java.nio.channels.SocketChannel[connected local=/192.168.1.214:10300 remote=/192.168.1.114:36030]
 * 3.如何处理客户端关闭问题:
 *  客户端关闭的话,还是会发生读事件,读取的是-1
 *  判断是-1的话就socketChannel.close()
 *
 */
public class NioTest11 {
    public static void main(String[] args) throws IOException {
        int[] ports = new int[5];
        //五个客户端端口,代表五个客户端
        ports[0] = 10300;
        ports[1] = 10400;
        ports[2] = 10500;
        ports[3] = 10600;
        ports[4] = 10700;
        //获取一个selector对象
        Selector selector = Selector.open();
//        System.out.println(selector.getClass().getName());

        for (int i = 0; i < ports.length; i++) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket = serverSocketChannel.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            serverSocket.bind(address);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口:" + ports[i]);
        }

        while (true) {
            int numbers = selector.select();//返回来的keys的数量,存在事件
            System.out.println("numbers:" + numbers);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();//只返回我们注册的感兴趣的SelectionKeyImpl
            Set<SelectionKey> keys1 = selector.keys();//返回的是所有的SelectionKeyImpl
            //selector.keys()和selector.selectedKeys()区别
            System.out.println("selectionKeys:" + selectionKeys);
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            while (iter.hasNext()) {
                SelectionKey selectionKey = iter.next();
                iter.remove();//必须移除
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                    System.out.println("serverSocketChannel" + serverSocketChannel.getLocalAddress());
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);//重要  不能漏掉
                    client.register(selector, SelectionKey.OP_READ);

                    System.out.println("获得客户端连接:" + client);
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;
                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = channel.read(byteBuffer);
                        if (read == -1) {
                            channel.close();
                            break;
                        }
                        if (read <= 0) {
                            break;
                        }

                        byteBuffer.flip();
                        channel.write(byteBuffer);

                        bytesRead += read;

                    }
                    System.out.println("读取: " + bytesRead + ", 来自于:" + channel);
                }
            }
        }
    }
}
