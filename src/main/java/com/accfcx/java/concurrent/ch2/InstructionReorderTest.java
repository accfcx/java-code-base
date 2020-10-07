package com.accfcx.java.concurrent.ch2;

/**
 * @author accfcx
 * @desc volatile:
 * 写 - volatile之前的操作不会排序到volatile写后面
 * 读 - volatile之后的操作不会排序到volatile读前面
 */
public class InstructionReorderTest {
    static int num;
    static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread readThread = new ReadThread();
        readThread.start();

        WriteThread writeThread = new WriteThread();
        writeThread.start();

        Thread.sleep(1);
        readThread.interrupt();

        System.out.println("main over");

    }

    static class ReadThread extends Thread{
        @Override
        public void run() {
                while(!Thread.currentThread().isInterrupted()) {
                    if (ready) {
                        System.out.println(num + num);
                    }
                    System.out.println("read ");
                }
        }
    }

    static class WriteThread extends Thread{
        @Override
        public void run() {
            num = 2;
            ready = true;
            System.out.println("write set over");
        }
    }
}
