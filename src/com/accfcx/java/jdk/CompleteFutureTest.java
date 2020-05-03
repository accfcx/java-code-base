package com.accfcx.java.jdk;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class CompleteFutureTest {
    private static final Logger loger = Logger.getLogger("Test!!!!");

    public static void main(String[] args) {
        loger.info("主线程开始");
        CompletableFuture<String> future = findUser();
        future.whenComplete((t,u)->{
            if(u != null){
                loger.info("异步调用发生异常:" + u);
            }
            else {
               loger.info("异步调用执行正常: " + t);
            }
        });
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        loger.info("主线程任务执行完毕");
    }

    public static CompletableFuture<String> findUser(){
        CompletableFuture<String> future = new CompletableFuture();
        new Thread(){
            @Override
            public void run() {

                String result = null;
                loger.info("任务开始执行....");
                try{
                    Thread.sleep(10000);
                    //模仿RPC远程调用
                    loger.info("任务执行结束....");
                }
                catch(Exception ex){
                    future.completeExceptionally(ex);
                }
                future.complete(result);
            }
        }.start();
        return future;
    }
}
