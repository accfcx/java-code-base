package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 */
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread A = new Thread(()->{
            while (true){}
        });

        final Thread current = Thread.currentThread();

        Thread B = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try{
                current.interrupt();
            }catch (Exception e) {
                System.out.println("B 线程中断了 main线程" + e);
            }
        });

        A.start();

        B.start();

        try{
            A.join();
        }catch (Exception e) {
            System.out.println("main阻塞过程中 遇到中断了:" + e);
        }

        System.out.println("main over");

        A.interrupt();

    }
}
