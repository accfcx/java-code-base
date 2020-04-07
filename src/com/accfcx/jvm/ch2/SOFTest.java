package com.accfcx.jvm.ch2;

import java.util.logging.Logger;

/**
 * 递归模拟线程请求的栈速度超过虚拟机允许的最大深度
 */
public class SOFTest {
    public static void main(String[] args) {
        SOFTest t = new SOFTest(1);
        try{
            t.stack();
        }catch (Throwable e) {
            Logger.getLogger("").info("异常捕获：" + t.getStackLength());
//            throw e;
        }

    }

    public SOFTest(int stackLength) {
        this.stackLength = stackLength;
    }

    private int stackLength;

    private void stack() {
        this.stackLength ++;
        stack();
    }

    public int getStackLength() {
        return stackLength;
    }

    public void setStackLength(int stackLength) {
        this.stackLength = stackLength;
    }
}
