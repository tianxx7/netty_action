package txx.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(8899)
                .addService(new StudentServiceImpl())
                .build().start();
        System.out.println("server start");
    }

    private void stop(){
        if (null != this.server) {
            this.server.shutdown();
        }
    }

    private void awaitTermination() throws InterruptedException {
        if (null != this.server) {
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        grpcServer.awaitTermination();//没有这一行,会自动结束,所以需要阻塞进程
        //grpcServer.stop();
    }
}
