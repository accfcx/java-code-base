package com.accfcx.java.jvm.ch12;

import java.util.HashMap;
import java.util.Map;

/**
 * @author accfcx
 * @desc 避免指令重排序-依赖于某个开关型的变量，而开关变量在进行了某些操作后才会启用
 */
public class InstructionReorderTest {

    Map configOptions;
    char[] configText;

    volatile boolean init = false;

    {
        configOptions = new HashMap();
        configText = readConfigFileName();
        init = true;

        while(!init) {
            try{
                Thread.sleep(1000L);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public void test() {

    }

    public static char[] readConfigFileName() {
        char[] charArray = {'A', 'B'};
        return charArray;
    }

    public static void processConfigOptions(char[] array, Map map) {
        System.out.println("Array: " + array);
        System.out.println("Map: " + map);

    }
}
