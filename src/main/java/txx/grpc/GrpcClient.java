package txx.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import txx.proto.MyRequest;
import txx.proto.MyResponse;
import txx.proto.StudentServiceGrpc;

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
        managedChannel.shutdown();
    }
}
