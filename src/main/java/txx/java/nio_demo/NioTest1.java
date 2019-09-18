package txx.java.nio_demo;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * nio demo
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            //SecureRandom  extends Random ,and SecureRandom is strong
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }
        buffer.flip();
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
}
