package com.accfcx.java.jvm.ch9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author accfcx
 * @desc 字节码生成以及动态代理
 */
public class ByteCodeAndDynamicProxy {
    interface IHello {
        /**
         * 接口方法
         */
        void say();
    }

    static class Hello implements IHello {
        @Override
        public void say() {
            System.out.println("Hello.say method");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originObject;

        Object bind(Object originObject) {
            this.originObject = originObject;
            return Proxy.
                    newProxyInstance(originObject.getClass().getClassLoader(),
                                     originObject.getClass().getInterfaces(),
                                  this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");

            return method.invoke(originObject, args);
        }
    }

    public static void main(String[] args) {
        IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
        iHello.say();
    }

}
