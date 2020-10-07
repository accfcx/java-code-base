package com.accfcx.java.jvm.ch8;


import java.util.Random;

/**
 * @author accfcx
 * @desc 静态分派
 */
public class StaticDispatch {
    static abstract class Human {
        static {
            System.out.println("Human init");
        }
    }

    static class Man extends Human{
        static {
            System.out.println("Man init");
        }
    }

    static class Woman extends Human{
        static {
            System.out.println("Woman init");
        }
    }

    public void say(Human human) {
        System.out.println("human class");
    }

    public void say(Man man) {
        System.out.println("man class");
    }

    public void say(Woman woman) {
        System.out.println("woman class");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch dispatch = new StaticDispatch();
//        dispatch.say(man);
//        dispatch.say(woman);

        Boolean value = (new Random()).nextBoolean();
        System.out.println(value);
        Human human = value ? new Man() : new Woman();

        /**
         * 静态类型：可cast转换
         * 动态类型：实际运行时才可以确定
         */
        dispatch.say((Man)human);
        dispatch.say((Woman)human);
    }
}
