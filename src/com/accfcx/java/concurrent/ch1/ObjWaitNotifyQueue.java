package com.accfcx.java.concurrent.ch1;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author accfcx
 * @desc X.wait()只会释放当前线程对共享变量X的锁，线程持有的其它锁不会释放
 */
public class ObjWaitNotifyQueue {
    private static Queue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void producer(Integer value) {
        synchronized (queue) {
            while(queue.size() == 10) {
                try{
                    queue.wait();
                }catch (Exception e) {
                    e.printStackTrace();
                }

                queue.add(value);
                queue.notifyAll();
            }
        }
    }

    public static void consumer()  {
        synchronized (queue) {
            while (queue.size() == 0) {
                try{
                    queue.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println(queue.poll());
                queue.notifyAll();
            }
        }
    }

}
