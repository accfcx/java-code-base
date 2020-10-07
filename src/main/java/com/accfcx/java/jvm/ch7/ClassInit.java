package com.accfcx.java.jvm.ch7;

/**
 * @author accfcx
 * @desc 类加载过程中的初始化阶段：执行类构造器<clinit>()方法
 */
public class ClassInit {
    static {
         i = 1;
//        System.out.println(i);
    }
    static int i = 2;
}
