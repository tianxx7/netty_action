package txx.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import txx.proto.*;

import java.time.LocalDate;
import java.util.Iterator;

public class GrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost",8899)
                .usePlaintext()
                .build();
        //代表客户端
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc
                .newBlockingStub(managedChannel);
        StudentServiceGrpc.StudentServiceStub stub = StudentServiceGrpc.newStub(managedChannel);


        /*MyResponse response = blockingStub
                .getRealNameByUserName(MyRequest.newBuilder().setUsername("张三").build());
        System.out.println(response.getRealname());
        System.out.println("-----------------------");
        Iterator<StudentResponse> students = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(12).build());
        while (students.hasNext()) {
            StudentResponse next = students.next();
            System.out.println(next.getName() + "," + next.getAge() + "," + next.getCity());
        }

        System.out.println("--------------------");*/


        /*StreamObserver<StudentResponseList> studentResponseListStreamObserver = new StreamObserver<StudentResponseList>() {
            @Override
            public void onNext(StudentResponseList studentResponseList) {
                studentResponseList.getStudentResponseList().forEach(studentResponse -> {
                    System.out.println(studentResponse.getName() + "," +studentResponse.getAge() + "," + studentResponse.getCity());
                });
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        };

        //客户端以流式向服务端请求,都是异步的
        StreamObserver<StudentRequest> studentRequestStreamObserver =
                stub.getStudentWrapperByAges(studentResponseListStreamObserver);
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(12).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(14).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(17).build());
        studentRequestStreamObserver.onNext(StudentRequest.newBuilder().setAge(18).build());
        //客户端调用结束
        studentRequestStreamObserver.onCompleted();*/

        StreamObserver<StreamRequest> requestStreamObserver = stub.biTalk(new StreamObserver<StreamResponse>() {
            @Override
            public void onNext(StreamResponse streamResponse) {
                System.out.println(streamResponse.getResponseInfo());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("client onCompleted");
            }
        });

        for (int i = 0; i < 10; i++) {
            requestStreamObserver.onNext(StreamRequest.newBuilder().setRequestInfo(LocalDate.now().toString()).build());
            Thread.sleep(1000);
        }


        //异步会不等待结果直接往下走,所以,客户端已经结束了
        Thread.sleep(5000);
        //managedChannel.shutdown();
    }
}
