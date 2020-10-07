package com.accfcx.java.concurrent2;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author accfcx
 * @desc
 * scheduleWithFixedDelay : initDelay, initDelay + (taskTime + delay), initDelay + 2 *(taskTime + delay)
 * scheduleAtFixedRate：initDelay + Delay, initDelay + 2 * Delay: 任务执行时间>=周期时间
 */
public class GuavaRateLimiterTest {
    static RateLimiter limiter = RateLimiter.create(1);

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis()/1000);
        }
    }


    public static void main(String[] args) {
        int N = 50;
        for (int i = 0; i < N; i++) {
            limiter.acquire();
            new Thread(new Task()).start();
        }
    }
}
