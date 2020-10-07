package com.accfcx.java.concurrent2;

import java.util.logging.Logger;

/**
 * @author accfcx
 * @desc
 * Notify/Wait替代
 */
public class WaitNotifyReplaceSuspendTest {

    static Logger logger = Logger.getLogger("Test");
    private static Object object = new Object();

    public static class ChangeObject extends Thread {
        private volatile boolean suspend = false;

        public void suspendMe() throws InterruptedException {
            this.suspend = true;

        }

        public void resumeMe() throws InterruptedException {
            this.suspend = false;
            synchronized (this) {
                this.notify();
            }
        }

        @Override
        public void run() {
            while(true) {
                synchronized (this) {
                    while (suspend) {
                        try{
                            wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    synchronized (object) {
                        try{
                            Thread.sleep(1);
                        }catch (Exception e) {

                        }
                        System.out.println("in ChangeObject");
                    }
                    Thread.yield();
                }
            }
        }
    }


    public static class ReadObject extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (object) {
                    try{
                        Thread.sleep(1);
                    }catch (Exception e) {

                    }

                    System.out.println(" in readObject");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObject t1 = new ChangeObject();
        ReadObject t2 = new ReadObject();

        t1.start();

        t2.start();

        Thread.sleep(2);

        t1.suspendMe();

        logger.info("suspend t1 2 sec");
        Thread.sleep(2);

        logger.info("resume t1");
        t1.resumeMe();

    }
}
