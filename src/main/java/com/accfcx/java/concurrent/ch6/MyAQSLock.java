package com.accfcx.java.concurrent.ch6;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author accfcx
 * @desc
 * 基于AQS实现一个锁不可重入
 */
public class MyAQSLock implements Lock, Serializable {

    private static Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }


    private static class Sync extends AbstractQueuedSynchronizer {
        // 锁状态-不可重入
        private AtomicInteger state = new AtomicInteger(0);

        protected Sync() {
            super();
        }

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if(state.compareAndSet(0, 1)) {
//                super.tryAcquire(arg);
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 0;
            if(state.compareAndSet(1, 0)) {
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

//        @Override
//        protected int tryAcquireShared(int arg) {
//            return super.tryAcquireShared(arg);
//        }
//
//        @Override
//        protected boolean tryReleaseShared(int arg) {
//            return super.tryReleaseShared(arg);
//        }

        @Override
        protected boolean isHeldExclusively() {
            return state.get() == 1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }
}
