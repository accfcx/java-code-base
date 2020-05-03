package com.accfcx.java.jvm.ch4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JConsoleTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread.sleep(2000);
//        new JConsoleTest().fillHeap(1000);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br.readLine();
//        createBusyThread();
//        br.readLine();
//
//        Object obj = new Object();
//        createLockThread(obj);
//        for (int i = 1; i <= 1000; i++) {
            new Thread(new SyncTest(1, 2)).start();
            new Thread(new SyncTest(2, 1)).start();
//        }
    }

    // JConsole Memory
    public void fillHeap(int num) throws InterruptedException{
        List<OOM> list = new ArrayList<>();
        for (int i = 1; i <= num; i++) {
            Thread.sleep(100);
            list.add(new OOM());
        }
        System.gc();
    }

    private static class OOM{
        private byte[] bytes = new byte[1024 * 64];// 64KB
    }

    // Thread

    // 1 Waiting outside resource:File/Network etc

    // 2 Dead Lock

    // 3 Infinite Loop


    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                }
            }
        }, "testBushThread");
        thread.start();
    }

    public static void createLockThread(final Object obj) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    try{
                        obj.wait();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static class SyncTest implements Runnable{
        private int a, b;

        public SyncTest(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

}
