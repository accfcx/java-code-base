package com.accfcx.java.concurrent.ch6;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author accfcx
 * @desc
 * 可重入，独占锁
 *
 *
 * todo 为什么会获取锁时，判断当前线程和阻塞队列的头部是否一致呢，难道不是park吗
 */
public class ReentrantLockTest {
    ReentrantLock lock = new ReentrantLock();
}
