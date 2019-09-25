package txx.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class OldServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        while (true) {
            //阻塞的
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            try {
                byte[] byteArray = new byte[4069];
                while (true) {
                    int read = dataInputStream.read(byteArray);
                    if (read == -1) {
                        break;
                    }
                }
            } finally {
                inputStream.close();
            }
        }
    }
}
