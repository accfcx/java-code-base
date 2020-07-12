package com.accfcx.java.jvm.ch7;

/**
 * @author accfcx
 * @desc 类加载过程中-初始化阶段并发clainit
 */
public class DeadLoopClaInit {
    static {
        if (true) {
            System.out.println(Thread.currentThread() + " init static code block");
            while (true){}
        }
    }
}
