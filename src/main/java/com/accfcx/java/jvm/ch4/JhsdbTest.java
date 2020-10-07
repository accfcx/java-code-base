package com.accfcx.java.jvm.ch4;

public class JhsdbTest {
    // 采用性能监控和故障分析工具-jhsdb 分析 静态类对象，类实例，局部对象存放的位置！！！
    private static class Test{
        static ObjectHolder staticOH = new ObjectHolder();
        private ObjectHolder instance = new ObjectHolder();

        public void foo() {
            ObjectHolder objectHolder = new ObjectHolder();
            System.out.println("breakpoint");
        }
    }

    private static class ObjectHolder{

    }

    public static void main(String[] args) {
        Test test = new JhsdbTest.Test();
        test.foo();
    }
}
