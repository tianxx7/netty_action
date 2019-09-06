package txx.netty_protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import txx.protobuf.DataInfo;

/* *
 * 描述:
 * @user tianxinxing
 * @date 2019/9/6
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student = DataInfo.Student
                .newBuilder()
                .setName("田新兴")
                .setAge(18)
                .setAddress("南京")
                .build();
        byte[] student2ByteArray = student.toByteArray();
        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);
        System.out.println(student2);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());
    }
}
