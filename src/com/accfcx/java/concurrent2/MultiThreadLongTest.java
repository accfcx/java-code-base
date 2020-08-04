package com.accfcx.java.concurrent2;

/**
 * @author accfcx
 * @desc
 * 多线程读写Long类型数据：32位系统，64位数据，非原子性
 */
public class MultiThreadLongTest {
    public static long count = 0;

    public static class ChangT implements Runnable {
        private long to;

        public ChangT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            for (;;) {
                MultiThreadLongTest.count = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{
        @Override
        public void run() {
            for (;;) {
                long tmp = MultiThreadLongTest.count;
                if (tmp != 111 &&
                tmp != -999 &&
                tmp != 333 &&
                tmp !=-444) {
                    System.out.println(tmp);
                }
                Thread.yield();
            }
        }

    }

    public static void main(String[] args) {
        new Thread(new ChangT(111)).start();
        new Thread(new ChangT(-999)).start();
        new Thread(new ChangT(333)).start();
        new Thread(new ChangT(-444)).start();
        new Thread(new ReadT()).start();
    }
}

