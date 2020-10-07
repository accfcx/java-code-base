package com.accfcx.java.jvm.ch3;

public class ReferenceCountingTest {
    public static void testGC() {

        ReferenceCountingTest objA = new ReferenceCountingTest();
        ReferenceCountingTest objB = new ReferenceCountingTest();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        // 假设在这行发生GC，objA和objB是否能被回收？
        System.gc();
    }
    private Object instance;
    // 模拟对象，占用空间
    private byte[] bytes = new byte[2 * 1024 * 1024];

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
