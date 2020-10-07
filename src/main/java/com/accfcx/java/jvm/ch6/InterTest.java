package com.accfcx.java.jvm.ch6;

public class InterTest implements InterfaceDescTest {
    @Override
    public void test() {
        System.out.println(a);
    }

    public static void main(String[] args) {
        new InterTest().test();
    }
}
