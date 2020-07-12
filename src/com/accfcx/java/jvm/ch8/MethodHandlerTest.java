package com.accfcx.java.jvm.ch8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;

/**
 * @author accfcx
 * @desc 方法句柄
 * 模拟invokeVirtual的分派逻辑：根据返回类型、函数名称、参数类型，去给定类型中查找方法；
 * 返回的MethodHandler是对于目标方法的引用
 *
 * reflection VS methodHandler
 * 都是模拟方法调用
 *
 * 重量级和轻量级
 *
 * Java代码层次 和 字节码层次
 * 反射不能使用字节码层面的调用点优化比如：方法内联
 * 方法句柄可以模拟优化
 */
public class MethodHandlerTest {
    static class Clazz{
        public void println(String s) {
            System.out.println(this.getClass().getName() + " " + s);
        }
    }

    public static void main(String[] args) throws Throwable {
//        Object object = (new Random()).nextBoolean() ? System.out : new Clazz();
        Object object =  new Clazz();
        getPrintlnMH(object).invokeExact("hello");
    }

    private static MethodHandle getPrintlnMH(Object rec) throws Throwable{
        MethodType methodType = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(rec.getClass(), "println2", methodType).bindTo(rec);
    }
}
