package com.accfcx.java.concurrent.ch6;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author accfcx
 * @desc
 * 可重入读写锁-读写分离：多个线程同时持有读锁
 *
 * firstReader:Thread
 * firstReaderHoldCount: count - first thread
 * cachedHoldCounter - last thread
 * readHolds - ThreadLocal<HoldCounter> 非第一个线程获取的读锁的可重入次数
 *
 * tryLock - 会尝试获取锁，成功；失败不会阻塞
 *
 * todo Reader Lock 的具体细节
 */
public class ReentrantRWLockTest {
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
}
