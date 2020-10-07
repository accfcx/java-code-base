package com.accfcx.java.concurrent.ch1;

/**
 * @author accfcx
 * @desc
 */
public class NotifyTest {
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(()->{
            synchronized (object) {
                System.out.println("A get lock of object" );

                System.out.println("A release lock and wait");
                try{
                    object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("A continue run from blocking and over");
            }
        });

        Thread B = new Thread(()->{
            synchronized (object) {
                System.out.println("B get lock of object");

                System.out.println("B release lock and wait");
                try{
                    object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("B continue run from blocking and over");
            }
        });

        Thread C = new Thread(()->{
            synchronized (object) {

                try{
                    object.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        A.start();
        B.start();

        Thread.sleep(5000);

        C.start();


        A.join();
        B.join();
        C.join();
        System.out.println("mai over");
    }
}
