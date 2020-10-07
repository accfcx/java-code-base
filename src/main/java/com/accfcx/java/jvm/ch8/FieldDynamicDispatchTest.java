package com.accfcx.java.jvm.ch8;

/**
 * @author accfcx
 * @desc 验证Field没有动态分配
 *
 * 子类字段不覆盖父类：使用父类字段
 */
public class FieldDynamicDispatchTest {
    static class Parent {
        private int x = 1;

        public Parent() {
            x = 2;
            System.out.println("P init:" + this.x);
            showNum();
        }

        public void showNum() {
            System.out.println("Parent :" + this.x);
        }
    }

    static class Child extends Parent {
        private int x = 3;

        public Child() {
            x = 4;
            System.out.println("C init:" + this.x);
            showNum();
        }

        @Override
        public void showNum() {
            System.out.println("Child: " + this.x);
        }
    }

    public static void main(String[] args) {
        Parent child = new Child();
        System.out.println(child.x);
        int[][] array = new int[10][-1];
    }
}
