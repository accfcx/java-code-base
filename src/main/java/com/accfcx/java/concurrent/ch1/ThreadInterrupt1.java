package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc A线程执行
 * B线程调用A线程的interrupt
 * 实际上：仅仅是设置了中断标志，只有A调用了wait/join/sleep时，A才会抛出中断异常
 */
public class ThreadInterrupt1 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (;;){
                try {
                    System.out.println("中断标志:" + Thread.currentThread().isInterrupted());
                    Thread.sleep(10000);
//                    Thread.currentThread().join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(1000);
        try{
            System.out.println("开始中断");
            thread.interrupt();
        } catch (Exception e) {
            System.out.println("main线程出错了：" + e);
        }

        System.out.println("main over");
    }
}
