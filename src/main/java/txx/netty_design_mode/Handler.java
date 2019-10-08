package txx.netty_design_mode;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {
    final SocketChannel channel;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0,SENDING = 1;
    int state = READING;


    Handler(Selector selector, SocketChannel c) throws IOException {
        this.channel = c;
        c.configureBlocking(false);

        this.sk = channel.register(selector,0);
        //将handler作为callback对象
        this.sk.attach(this);

        //注册read就绪事件
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete(){
        return false;
    }

    boolean outputIsComplete(){
        return false;
    }

    void process(){
        return;
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void read() throws IOException {
        channel.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            //第三步,接收write就绪事件
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        channel.write(output);
        //write完就结束了,关闭select key
        if (outputIsComplete()) {
            sk.cancel();
        }
    }
}
