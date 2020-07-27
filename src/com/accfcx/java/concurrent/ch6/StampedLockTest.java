package com.accfcx.java.concurrent.ch6;

import java.util.concurrent.locks.StampedLock;

/**
 * @author accfcx
 * @desc
 * 不可重入
 * 悲观读 - CAS加锁
 * 乐观读 - 没有使用CAS设置锁状态，为保证数据一致性，复制一份变量快照到方法栈中
 */
public class StampedLockTest {
    private StampedLock stampedLock = new StampedLock();
}
