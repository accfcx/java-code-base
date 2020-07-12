package com.accfcx.java.jvm.ch9;

/**
 * @author accfcx
 * @desc 热部署类加载器
 * constructor: 加载HotSwapClassLoader类的类加载器作为HotSwap的父类加载器
 * HotSwap类查找范围与父类加载器完全一致 按照双亲委派模型交给父类加载器加载
 *
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte) {
        return defineClass(null,
                classByte,
                0,
                classByte.length);
    }
}
