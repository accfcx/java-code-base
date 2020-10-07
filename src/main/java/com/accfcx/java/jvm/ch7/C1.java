package com.accfcx.java.jvm.ch7;

/**
 * @author accfcx
 * @desc
 */
public class C1 extends P1 {
    static int B = A;
    static {
        System.out.println("C1: "+ B);
    }
}
