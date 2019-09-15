package txx.grpc;

import io.grpc.stub.StreamObserver;
import txx.proto.*;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息:" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张灿").build());
        responseObserver.onCompleted();//只能调用一次,方法已经执行完了
        // 突然心好难受,为什么又想起了她,明明已经删除了所有联系方式
    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端信息:"+request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老王").setAge(12).setCity("苏州").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老李").setAge(13).setCity("常州").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("老铁").setAge(14).setCity("太守").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王大锤").setAge(15).setCity("郑州").build());
        responseObserver.onCompleted();
    }
}
