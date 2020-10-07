package com.accfcx.java.concurrent.ch7;

/**
 * @author accfcx
 * @desc
 * 阻塞队列 - 锁
 *
 * 单向链表，head,tail[Node], count,
 * TakeLock, PutLock[ReentrantLock]
 * NotEmpty,NotFull[ConditionObject]
 *
 *
 * NotFull
 * offer - 非阻塞，满了直接返回false - notFull.signal【唤醒put的阻塞】, notEmpty.signal[唤醒take的阻塞]
 * put - 阻塞，满了会放到notFull条件变量的条件队列中; notFull.wait()
 *
 * NotEmpty
 *  poll - 非阻塞
 *  take - 阻塞
 *  peek - 非阻塞
 *
 *  remove-takeLock, putLock阻塞
 */
public class LinkedBlockingQueueTest {
}
