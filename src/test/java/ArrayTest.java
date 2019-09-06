import org.junit.Test;

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
}
