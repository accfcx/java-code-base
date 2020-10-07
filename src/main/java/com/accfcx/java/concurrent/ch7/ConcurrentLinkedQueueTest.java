package com.accfcx.java.concurrent.ch7;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author accfcx
 * @desc
 *
 * 阻塞队列 - 锁
 * 非阻塞队列 - CAS算法
 *
 * ConcurrentLinkedQueue - 线程安全的无界非阻塞队列；单向链表，入队/出队CAS保证
 *      volatile - head, tail[Node]
 *      入队 - tail； Unsafe工具类的CAS操作保证出入对时，操作链表的原子性 - 多线程入队时，CAS只有一个成功，其它的自旋重试,特殊情况：offer的过程中做了poll操作
 *      出队 - head
 *
 * todo offer - poll 判断处理
 */
public class ConcurrentLinkedQueueTest {
    ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
//        String value = null;
        Integer value = null;
        Objects.requireNonNull(value, "空值");
    }
}
