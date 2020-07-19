package com.accfcx.java.concurrent.ch2;

/**
 * @author accfcx
 * @desc 悲观锁 - 严格事务保证
 * 乐观锁 - version 根据update结果影响的行数，是否重试
 *
 *
 * 悲观锁-乐观锁 -数据库,用户保证
 * 公平锁-非公平锁 ReentrantLock
 * 独占锁-共享锁 -ReentrantLock，ReadWriteLock
 *
 * 可重入锁 - synchronized内置锁 标志当前线程和计数器
 */
public class LockTest {
}
