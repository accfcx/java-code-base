package com.accfcx.java.concurrent2;

import java.util.Random;

/**
 * @author accfcx
 * @desc
 * Thread.stop()方法 可能会导致数据不一致问题，慎用
 */
public class ThreadStopTest {

    static class User {
        private int id;
        private String name;

        public User() {
            this.id = 0;
            this.name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static User user = new User();

    static class ReadTest implements Runnable{
        @Override
        public void run() {
//            while(true) {
                synchronized (user) {
                    System.out.println("Read!!!!!!!!!!!!!!!!");

                    if (user.getId() != Integer.parseInt(user.getName())) {
                        System.out.println(user);
                    }
//                    Thread.yield();
//                }
            }
        }
    }

    static class WriteTest implements Runnable {
        @Override
        public void run() {
//            while(true) {
                synchronized (user) {
                    System.out.println("Write---------");
                    Random random = new Random();
                    int value = random.nextInt();

                    user.setId(value);
                    try{
                        Thread.sleep(100);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(value));
                }
                Thread.yield();
//            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new ReadTest());
        thread1.start();

//        while (true) {
            Thread thread2 = new Thread(new WriteTest());
            thread2.start();

            Thread.sleep(200);
            thread2.stop();
//        }
    }

}
