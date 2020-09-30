package com.accfcx.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
