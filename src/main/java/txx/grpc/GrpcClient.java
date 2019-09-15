package txx.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import txx.proto.*;

import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost",8899)
                .usePlaintext()
                .build();
        //代表客户端
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
                .newBlockingStub(managedChannel);
        MyResponse response = blockingStub
                .getRealNameByUserName(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response.getRealname());
        System.out.println("-----------------------");
        Iterator<StudentResponse> students = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(12).build());
        while (students.hasNext()) {
            StudentResponse next = students.next();
            System.out.println(next.getName() + "," + next.getAge() + "," + next.getCity());
        }
        //managedChannel.shutdown();
    }
}
