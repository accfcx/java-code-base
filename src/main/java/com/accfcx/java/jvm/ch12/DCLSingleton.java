package com.accfcx.java.jvm.ch12;

/**
 * @author accfcx
 * @desc Double Check Lock
 *
 */
public class DCLSingleton {
    private static  DCLSingleton instance;

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(DCLSingleton.getInstance());
    }
}
