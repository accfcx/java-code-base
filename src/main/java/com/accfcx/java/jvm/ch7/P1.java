package com.accfcx.java.jvm.ch7;

/**
 * @author accfcx
 * @desc
 */
public class P1 {
    static int A = 1;
    static {
        System.out.println("P1: " + A);
        A = 2;
        System.out.println("P1: "+ A);
    }
}
