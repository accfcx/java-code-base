package com.accfcx.java.jvm.ch12;

import java.util.Vector;

/**
 * @author accfcx
 * @desc
 */
public class ConstantTest {
    public static void main(String[] args) {
//        System.out.println(Constants.value);
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(0);
        vector.forEach(System.out::println);
        vector.remove(0);
//        vector.forEach(System.out::println);
        System.out.println(vector.get(0));
    }
}
