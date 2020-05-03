package com.accfcx.java.jvm.ch6;

/**
 * 方法重载-Java代码层次和字节码
 */
public class OverloadTest {
    public void test(int a, int b, int c){
        System.out.println(a + b);
    }

    public void test(int c, int d){
        System.out.println(c + d);
    }

    public static void main(String[] args) {
        new OverloadTest().test(1, 2);
    }
}
