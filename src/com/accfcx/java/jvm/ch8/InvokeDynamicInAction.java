package com.accfcx.java.jvm.ch8;

import org.w3c.dom.ls.LSOutput;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @author accfcx
 * @desc 动态调用的应用
 */
public class InvokeDynamicInAction {
    static class GrandFather {
        void say() {
            System.out.println("GrandFather say");
        }
    }

    static class Father extends GrandFather {
        @Override
        void say() {
            System.out.println("Father say");
        }
    }

    static class Son extends Father {
        @Override
        void say() {
            try{
                MethodType methodType = MethodType.methodType(void.class);
//                MethodHandle methodHandle = MethodHandles.lookup().findSpecial(GrandFather.class,
//                        "say",
//                        methodType
//                        ,getClass());
//                methodHandle.invoke(this);

                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,"say", methodType, GrandFather.class);
                mh.invoke(this);
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        (new InvokeDynamicInAction.Son()).say();
    }
}
