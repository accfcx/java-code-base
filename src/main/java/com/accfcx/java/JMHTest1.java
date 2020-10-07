package com.accfcx.java;

import org.openjdk.jmh.runner.RunnerException;

/**
 * @author accfcx
 * @desc 性能测试 - jmh
 */
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHTest1 {
//    @Benchmark
//    public void test() {
//        System.out.println("hello");
//    }

    public static void main(String[] args) throws RunnerException {
//        Options opt = new OptionsBuilder()
//                .include(JMHTest1.class.getSimpleName())
//                .forks(1).build();
//        new Runner(opt).run();
        System.out.println(System.currentTimeMillis());
    }
}
