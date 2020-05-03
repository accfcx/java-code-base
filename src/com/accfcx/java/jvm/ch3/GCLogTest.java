package com.accfcx.java.jvm.ch3;

import java.util.Map;

public class GCLogTest {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        //
        byte[] t1, t2, t3, t4;
        t1 = new byte[2 * _1MB]; // 2MB
        t2 = new byte[2 * _1MB]; // 2MB
        t3 = new byte[2 * _1MB]; // 2MB
        t4 = new byte[4 * _1MB]; // 2MB
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();

        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : map.entrySet()) {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
            if (thread.equals(Thread.currentThread())) { continue; }
            System.out.print("\n线程：" + thread.getName() + "\n");
            for (StackTraceElement element : stack) {
                System.out.print("\t"+element+"\n");
            }

        }
    }
}
