import io.netty.util.NettyRuntime;
import org.junit.Test;
import sun.plugin2.util.SystemUtil;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/9/5
 */
public class ArrayTest {
    @Test
    public void test1(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(256);
        list.add(291);
        list.add(290);
        Object[] objects = list.toArray();
        Integer[] arr = new Integer[3];
        Integer[] integers = list.toArray(arr);
        System.out.println();
    }

    @Test
    public void threadNum() {
        int max = Math.max(1, NettyRuntime.availableProcessors() * 2);
        System.out.println(max);
    }
}
