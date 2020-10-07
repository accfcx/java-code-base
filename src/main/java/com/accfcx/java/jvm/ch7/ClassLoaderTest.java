package com.accfcx.java.jvm.ch7;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author accfcx
 * @desc
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        throw new ClassNotFoundException(name + " is null");
//                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object myLoaderAndClassObjectAndInstance = classLoader.loadClass("ClassLoaderTest").getDeclaredConstructor().newInstance();
        System.out.println(myLoaderAndClassObjectAndInstance.getClass());
        System.out.println(myLoaderAndClassObjectAndInstance instanceof ClassLoaderTest);
    }
}
