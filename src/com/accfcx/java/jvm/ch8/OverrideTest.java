package com.accfcx.java.jvm.ch8;


/**
 * @author accfcx
 * @desc 动态分派
 */
public class OverrideTest {
    static abstract class Human {
        /**
         * abstract method
         */
        public abstract void say();
    }


    static class Man extends Human {
        @Override
        public void say() {
            System.out.println("man say");
        }
    }

    static class Woman extends Human {
        @Override
        public void say() {
            System.out.println("woman say");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.say();
        woman.say();

        man = new Woman();
        man.say();
    }
}
