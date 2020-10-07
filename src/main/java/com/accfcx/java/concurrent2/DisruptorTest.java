package com.accfcx.java.concurrent2;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author accfcx
 * @desc
 * Disruptor 生产者消费者模式
 */
public class DisruptorTest {

    static class Request {
        int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    static class RequestFactory implements EventFactory<Request> {
        @Override
        public Request newInstance() {
            return new Request();
        }
    }

    static class Producer {
        private final RingBuffer<Request> ringBuffer;

        public Producer(RingBuffer<Request> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void push(ByteBuffer bb) {
            long se = ringBuffer.next();
            try{
                Request request = ringBuffer.get(se);
                request.setData(bb.getInt());
            }finally {
                ringBuffer.publish(se);
            }
        }
    }

    static class Consumer implements WorkHandler<Request> {
        @Override
        public void onEvent(Request request) throws Exception {
            System.out.println(Thread.currentThread().getName() + " 消费 ！！！！！！！！" + request);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        RequestFactory factory = new RequestFactory();

        int bufferSize = 1024;

        Disruptor<Request> disruptor = new Disruptor<Request>(factory,
                bufferSize,
                executorService,
                ProducerType.MULTI,
                new BlockingWaitStrategy());
        disruptor.handleEventsWithWorkerPool(new Consumer(), new Consumer());

        disruptor.start();

        RingBuffer<Request> ringBuffer = disruptor.getRingBuffer();
        Producer producer = new Producer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putInt(0, 1);
        producer.push(bb);

    }
}
