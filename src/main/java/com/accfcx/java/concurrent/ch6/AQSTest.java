package com.accfcx.java.concurrent.ch6;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author accfcx
 * @desc 抽象同步队列 - 实现同步器
 * juc - lock的底层使用AQS实现
 *
 * AQS - state
 *
 * 维护一个双端队列[Node], head, tail
 *
 * Node - 记录线程，阻塞模式[共享，互斥], pre,next, waitStatus
 *
 * ConditionObject - 每个条件对象对应一个条件队列【单向链表】，存储调用 条件变量.await()阻塞的线程; firstWaiter，lastWaiter
 *
 * ReentrantLock, ReentrantReadWriteLock, Semaphore, CountDownlatch中的state有不同的值
 *
 * 独占方式
 * acquire(): CAS state 0->1 - 成功返回；失败，构造独占模式的Node插入到队列尾部，LockSupport.park(this)阻塞线程
 * release(): CAS state 1->0 - LockSupport.unpark(thread)
 *
 * 共享方式
 *
 *
 * 基于AQS实现的锁，获取锁时，多个线程只有一个线程从获取锁，其它都会进入到<AQS的阻塞队列>中，但是线程并没有Park,而是在做CAS自旋尝试获取锁
 *
 * 在获取到锁的线程如果又调用了一个条件变量的await方法，线程会释放锁，线程会放到条件变量的条件队列中
 *
 * 释放掉的锁，会被AQS的阻塞队列中的一个线程获取到
 * 当有其它线程调用条件变量的signal方法，条件变量对应的条件队列中的一个线程移除到AQS的阻塞队列，尝试获取锁
 */
public class AQSTest {
    AbstractQueuedSynchronizer aqs = null;

}
