package com.accfcx.java.concurrent2;


import java.util.concurrent.CyclicBarrier;

/**
 * @author accfcx
 * @desc
 * 循环栅栏
 */
public class CyclicBarrierTest {

    static class MyTask implements Runnable {
        String soldier;
        final CyclicBarrier cyclicBarrier;

        public MyTask(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try{
                cyclicBarrier.await();
                Thread.sleep(1000);
                System.out.println(soldier + " 完成工作");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class BarrierRun implements Runnable {
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag) {
                System.out.println("任务完成");
            }else {
                System.out.println("集合完成");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        Thread[] soldiers = new Thread[10];
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new BarrierRun(false, 10));
        for (int i = 0;  i < 10; i++) {
            System.out.println("集合" + i);
            soldiers[i] = new Thread(new MyTask("士兵" + i, cyclicBarrier));
            soldiers[i].start();
            if (i == 5) {
                soldiers[0].interrupt();
            }
        }
    }
}
