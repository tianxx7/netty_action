package txx.netty_promise;

import io.netty.util.concurrent.*;

public class PromiseTest {

    public static void main(String[] args) {
        //构造线程池
        //executor 相当于netty的EventLoop
        EventExecutor executor = new DefaultEventExecutor();
        //创建DefaultPromise实例
        Promise promise = new DefaultPromise(executor);
        promise.addListener(new GenericFutureListener<Future<Integer>>() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("任务结束,结果: " + future.get());
                } else {
                    System.out.println("任务失败,结果: " + future.cause());
                }
            }
        }).addListener(new GenericFutureListener<Future<Integer>>() {
            @Override
            public void operationComplete(Future future) throws Exception {
                System.out.println("任务结束,啦啦啦啦...");
            }
        });
        //提交任务到线程池
        executor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                promise.setSuccess(123545);
                promise.setFailure(new RuntimeException("人为异常"));
            }
        });

        //main线程阻塞等待结果
        try{
            //相当于bootstrap.bind().sync();
            promise.sync();//与await的区别是会抛出异常
            //promise.await();//不会有任何异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
