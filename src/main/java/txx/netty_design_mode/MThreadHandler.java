package txx.netty_design_mode;

import com.sun.xml.internal.ws.api.server.WSEndpoint;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MThreadHandler implements Runnable {
    final SocketChannel channel;
    final SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    ExecutorService pool = Executors.newFixedThreadPool(2);
    static final int PROCESSING = 3;

    MThreadHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        selectionKey = channel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete(){
        /**/
        return false;
    }

    boolean outputComplete(){
        /**/
        return false;
    }

    void process(){
        /**/
        return;
    }


    @Override
    public void run() {
        try {
            if (state == READING){
                read();
            } else if(state == SENDING) {
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void read() throws IOException {
        channel.read(input);
        if (inputIsComplete()) {
            state = PROCESSING;
            pool.execute(new Processer());
        }
    }
    void send() throws IOException {
        channel.write(output);
        if (outputComplete()) {
            selectionKey.cancel();
        }
    }

    synchronized void processAndHandoff(){
        process();
        state = SENDING;
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }
    class Processer implements Runnable{

        @Override
        public void run() {
            processAndHandoff();
        }
    }
}
