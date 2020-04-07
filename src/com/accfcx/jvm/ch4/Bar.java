package com.accfcx.jvm.ch4;

import java.util.HashMap;
import java.util.Map;

public class Bar {

        int a = 1; static int b = 2;

        public int sum(int c) { return a + b + c; }

        public static void main(String[] args) {
                new Bar().sum(3);
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < 10000000; i++) {
                        map.put(i, i);
                }
        }

}
