package com.accfcx.java.jvm.ch7;

import org.w3c.dom.ls.LSOutput;

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
