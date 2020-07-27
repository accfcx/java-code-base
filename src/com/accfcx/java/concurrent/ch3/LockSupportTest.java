package com.accfcx.java.concurrent.ch3;

import com.accfcx.java.concurrent.ch2.UnsafeTest;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.IntStream;

/**
 * @author accfcx
 * @desc park
 * 线程是否持有许可证：是直接返回；否则阻塞线程
 *
 * 线程 X park【中断时，不会出现中断异常】
 * 线程 Y 1 X.interrupt 2 LockSupport.unpark(X) 3 X被虚假唤醒
 *
 * X线程调用 LockSupport.park() - 验证线程X是否具有许可证：有则跳过，否则阻塞
 *
 * LockSupport.unpark(X) - 让X线程持有许可证
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
//        Thread mainThread = Thread.currentThread();
//        Thread thread = new Thread(()->{
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            mainThread.interrupt();
//            LockSupport.unpark(mainThread);
//        });
//        thread.start();
//
//        System.out.println("begin park");
//        LockSupport.park();
//        System.out.println("end park");

//        Thread thread1 = new Thread(()->{
//            System.out.println("thread1 run");
//        });
//        thread1.start();

//        LockSupport.unpark(Thread.currentThread());
//        LockSupport.park(Thread.currentThread());
//        LockSupport.park(Thread.currentThread());

//        LockSupportTest.test();
//        new LockSupportTest().test5();

        FIFOClock clock = new FIFOClock();

        CountDownLatch countDownLatch = new CountDownLatch(5);

        IntStream.range(0, 5).forEach(i -> new Thread(() -> {
            clock.lock();

            try {
                IntStream.range(0, 100).forEach(j -> {
                    count++;
                });
            } finally {
                clock.unlock();
            }
            countDownLatch.countDown();
            System.out.println(Thread.currentThread() + " is over");
        }, "tt-" + i).start());

        countDownLatch.await();
        System.out.println(count);

    }

    private static int count = 0;


    /**
     * 验证park() 后中断唤醒，要验证条件是否满足
     */
    public static void test() throws InterruptedException {
        Thread thread = new Thread(()->{
//            try{
//
//            }
//            while(Thread.interrupted()) {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is parking");
                LockSupport.park();
            }
        }, "test-thread");
        thread.start();

        Thread.sleep(1000);

//        LockSupport.
        System.out.println("main interrupt");
        thread.interrupt();
    }

    public void test5() {
        LockSupport.park(this);
    }

    public void test6() {
        ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();
        queue.add(Thread.currentThread());

        queue.remove();
    }

    static class FIFOClock {
        //锁用来保证多线程
        /**
         * 实现一个锁的关键：
         * 一个状态标记-是否已被持有【如果考虑可重入，还需要记录持有锁的线程】
         * CAS-更新状态
         * 队列/链表-记录阻塞的线程
         *
         * lock()
         * 尝试获取锁-成功返回；失败，进入阻塞线程队列
         *
         * 被唤醒后-从线程队列中移除
         *
         * unlock() - 恢复解锁状态/按照锁的特性和规则唤醒线程
         *
         */

//        private volatile int state = 0;
        private AtomicBoolean state = new AtomicBoolean(false);
        private static Queue<Thread> queue = new ConcurrentLinkedQueue<>();

//        private static Unsafe unsafe;
//        private static long offset = 0;
//
//        static {
//            try{
//                Field field = Unsafe.class.getDeclaredField("theUnsafe");
//                field.setAccessible(true);
//                unsafe = (Unsafe) field.get(null);
//
//                offset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }

        public  void lock() {
            // 中断标志
            boolean isInterrupted = false;

            // 先进入队列
            queue.add(Thread.currentThread());

            // 阻塞条件 - 不是head/是头，但是锁被其它线程持有
//            while(queue.peek() != Thread.currentThread() || !unsafe.compareAndSwapInt(this, offset, 0, 1)) {
            while(queue.peek() != Thread.currentThread() || !state.compareAndSet(false, true)) {
                LockSupport.park();
                // 如果因为中断唤醒，记录
                if (Thread.currentThread().isInterrupted()) {
                    isInterrupted = true;
                }
            }

            // 当前线程是队列头部，并且以及持有了锁
            queue.remove();

            if (isInterrupted) {
                Thread.currentThread().interrupt();
            }
        }

        public void unlock() {
//            unsafe.compareAndSwapInt(this, offset, 1, 0);
            state.set(false);
            LockSupport.unpark(queue.peek());
        }
    }
}
