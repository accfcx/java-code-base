package com.accfcx.jvm.ch3;

import java.util.logging.Logger;

public class GCEscapeTest {
    private static GCEscapeTest object = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Logger.getLogger("").info("finalize method executed");
        // 这里触发第二次标记失败：重新建立关联
        GCEscapeTest.object = this;
    }

    public static GCEscapeTest getObject() {
        return object;
    }

    public static void setObject(GCEscapeTest object) {
        GCEscapeTest.object = object;
    }

    public static void main(String[] args) throws Throwable{
        object = new GCEscapeTest();
        object = null;
        System.gc();
        Thread.sleep(1000);
        if(object == null) {
            Logger.getLogger("").info("回收成功");
        } else{
            Logger.getLogger("").info("回收失败");
        }

        // 筛选时由于对象的finalize()已被执行，无法进行后续逃逸
        object = null;
        System.gc();
        Thread.sleep(1000);
        if(object == null) {
            Logger.getLogger("").info("回收成功");
        } else{
            Logger.getLogger("").info("回收失败");
        }
    }
}
