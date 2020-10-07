package com.accfcx.java.concurrent2;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author accfcx
 * @desc
 * 模拟线程池里多个线程执行同一个任务，避免因为消费过慢，开启新线程执行
 * ArrayBlockingQueue 作为解耦生产者消费者
 *
 * todo: 怎么关系线程，让queue少的时候，关闭多余的线程
 */
public class ProducerConsumerTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    static class Producer implements Runnable {
        boolean stopped = false;

        ArrayBlockingQueue<Request> queue;



        public Producer(ArrayBlockingQueue<Request> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理队列:" + System.identityHashCode(queue));
            while(!this.stopped) {
                try {
                    doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        void doWork() throws InterruptedException {
            if (Thread.currentThread().isInterrupted()) {
                this.stopped =true;
                return;
            }
            Thread.sleep(1000);
            int i = new Random().nextInt();
            Request request = new Request(i, " msg " + i);
            System.out.println(Thread.currentThread().getName() + " 生产  " + request);
            boolean result = queue.offer(request);
            if (!result) {
                System.out.println(Thread.currentThread().getName() + " offer failed: " + request);
            }
        }

        void stop() {
            this.stopped = true;
            Thread.currentThread().interrupt();
        }

    }


    static class Consumer implements Runnable {
        ArrayBlockingQueue<Request> queue;

        volatile AtomicInteger count = new AtomicInteger(0);

        public Consumer(ArrayBlockingQueue<Request> queue) {
            this.queue = queue;
            count.set(1);
        }

        @Override
        public void run() {
            while(true) {
                Request request = queue.poll();
                // 模拟消费慢，开启多个线程执行消费queue
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (request != null) {
                    System.out.println(Thread.currentThread().getName() + " 消费 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + request);
                }

            }
        }

        public void feedRequest(Request request) {
            boolean result = queue.offer(request);
            if (result) {
                System.out.println("queue新增 ------------------" + request);
            }

            // queue流量多时，开启新线程消费
            if (queue.size() > 5 && queue.size() < 70) {
                count.incrementAndGet();
                executorService.submit(this);
                System.out.println("当前有" + count.get() + " 个线程消耗");
            }

        }
    }

    static class Request {
        int data;
        String msg;

        public Request(int data, String msg) {
            this.data = data;
            this.msg = msg;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "data=" + data +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);

        Consumer consumer = new Consumer(queue);
        executorService.submit(consumer);

        while(true) {
            int i = new Random().nextInt(100);
            Thread.sleep(5 * i);
            Request request = new Request(i, " msg " + i);
            consumer.feedRequest(request);
        }

//        Producer producer = new Producer(queue);
//        executorService.submit(producer);
//        executorService.submit(producer);
//
//        Consumer consumer = new Consumer(queue);
//        executorService.submit(consumer);
//        executorService.submit(consumer);
    }
}
