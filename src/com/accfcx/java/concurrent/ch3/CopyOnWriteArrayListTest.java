package com.accfcx.java.concurrent.ch3;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author accfcx
 * @desc
 * remove 1: getArray 2: array[index] 没有使用原子保证
 *
 * 在线程X获取array后，index之前，线程Y原子删除了index位置的元素【写时复制，并指向新数组】，
 * X仍然访问的旧数组中的数
 *
 * 迭代器的弱一致性：在遍历的过程中，如果对list做了增删改，会使用新的数组作为list的数据，之前遍历时获取的数组对象引用
 * 属于快照
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList();

        Class<?> clazz = CopyOnWriteArrayList.class;
        try{
//            clazz.
            Method method = clazz.getMethod("getArray");
            method.setAccessible(true);

            Object o = clazz.getConstructor().newInstance();
            method.invoke(o);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
//        System.out.println(arrayList.getArray());
        Object[] array = list.toArray();
        for (Object integer : array) {
            System.out.println((Integer)integer);
        }

        System.out.println(CopyOnWriteArrayList.class);
        System.out.println(list.getClass());

        CopyOnWriteArrayListTest.test3();
    }

    public static void test() {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try{
            System.out.println("hello");
        } finally {
            lock.unlock();
        }

    }

    /**
     * 演示迭代器弱一致性问题
     */
    private static volatile CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public static void test3() throws InterruptedException {
        list.add("hello");
        list.add("alibaba");
        list.add("welcome");
        list.add("to");
        list.add("bj");

        Thread thread = new Thread(()->{
            list.set(1, "baba");
            list.remove(2);
            list.remove(3);
        });

        Iterator<String> iterator = list.iterator();

        thread.start();

        thread.join();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
