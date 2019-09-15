package txx.grpc;

import io.grpc.stub.StreamObserver;
import txx.proto.*;

import java.util.UUID;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    //客户端请求,服务端返回单一结果
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息:" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张灿").build());
        responseObserver.onCompleted();//只能调用一次,方法已经执行完了
        // 突然心好难受,为什么又想起了她,明明已经删除了所有联系方式
    }

    //客户端请求,服务端返回流
    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息:"+request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老王").setAge(12).setCity("苏州").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老李").setAge(13).setCity("常州").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老铁").setAge(14).setCity("太守").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王大锤").setAge(15).setCity("郑州").build());
        responseObserver.onCompleted();
    }

    //客户端是流,服务端返回单一结果
    @Override
    public StreamObserver<StudentRequest> getStudentWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest studentRequest) {
                System.out.println("接收客户端消息:" + studentRequest.getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse studentResponse = StudentResponse.newBuilder().setName("李强").setAge(17).setCity("北京").build();
                StudentResponse studentResponse2 = StudentResponse.newBuilder().setName("张伟").setAge(18).setCity("广州").build();
                StudentResponseList studentResponseList = StudentResponseList.newBuilder()
                        .addStudentResponse(studentResponse).addStudentResponse(studentResponse2).build();
                responseObserver.onNext(studentResponseList);
                responseObserver.onCompleted();
                System.out.println("客户端全部发送完调用此方法,返回结果");
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>(){

            @Override
            public void onNext(StreamRequest streamRequest) {
                System.out.println(streamRequest.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
