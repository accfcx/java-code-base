package com.accfcx.java.jvm.ch7;

/**
 * @author accfcx
 * @desc
 */
public class StackFrameLocalTest {
    public static void main(String[] args) {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
//            bytes = null;
        }

        System.gc();
    }

    void test() {
        int x;
//        System.out.println(x);
    }
}
/**
 [0.014s][info][gc] Using G1
 [0.032s][info][gc] Periodic GC disabled
 [0.149s][info][gc] GC(0) Pause Full (System.gc()) 68M->66M(234M) 2.892ms
 */