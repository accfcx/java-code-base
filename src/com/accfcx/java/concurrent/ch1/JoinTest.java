package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc join - 主线程等待 X.join() 等待线程X完成
 *
 */
public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("A over");
        });

        Thread B = new Thread(()->{
            try{
                Thread.sleep(2000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("B Over");
        });


        A.start();
        B.start();

//        A.join();
//        B.join();
        System.out.println("main over");
    }
}
