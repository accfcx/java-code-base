package com.accfcx.java.concurrent.ch7;

/**
 * @author accfcx
 * @desc
 * 有界数组、阻塞队列
 * 一个ReentrantLock维护出入队操作：只能有一个线程操作【默认非公平独占锁】
 * notEmpty,notFull 条件变量用于同步出入队
 *
 * offer - 非阻塞; notEmpty.signal
 * put - 阻塞：放入条件变量的条件队列 NotFull.await()
 *
 * poll - 非阻塞; 通知notFull.signal
 * take - 阻塞；notEmpty.await()
 *
 * peek - 非阻塞
 *
 * size - 加锁获取，准确
 */
public class ArrayBlockingQueueTest {
}
