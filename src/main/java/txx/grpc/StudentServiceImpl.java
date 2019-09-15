package txx.grpc;

import io.grpc.stub.StreamObserver;
import txx.proto.MyRequest;
import txx.proto.MyResponse;
import txx.proto.StudentServiceGrpc;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到客户端信息:" + request.getUsername());
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张灿").build());
        responseObserver.onCompleted();//只能调用一次,方法已经执行完了
        // 突然心好难受,为什么又想起了她,明明已经删除了所有联系方式
    }
}
