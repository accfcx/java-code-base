package com.accfcx.codeinterview;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer N;

        Map<Integer, Integer> map = new HashMap<>();// 正确格式是有3个key，且每个key对应的value都是4

        N = in.nextInt();
        Integer A,B;
        while(N>0) {// 每组读取 6行数据 每行2个数字
            N--;
            for (int i = 0; i < 6; i++) {
                if (!in.hasNextInt()) {
                    break;
                }
                A = in.nextInt();
                if (!in.hasNextInt()) {
                    break;
                }
                B = in.nextInt();
               if (map.containsKey(A)) {
                   map.put(A, map.get(A) + 1);
               } else {
                   map.put(A, 1);
               }
               if (map.containsKey(B)) {
                   map.put(B, map.get(B) + 1);
               } else {
                   map.put(B, 1);
               }
            }
            // 读取完成，处理map
            if (map.size() != 3) {
                System.out.println("IMPOSSIBLE");
            } else {
                //校验每个是否都是4
                AtomicBoolean success = new AtomicBoolean(true);
                map.forEach((k, v)->{
                    if (v !=4) {
                        success.set(false);
                    }
                });
                if (success.get()) {
                    System.out.println("POSSIBLE");
                }

            }
            map.clear();
        }
    }
}

/**
 * 有n位乘客乘坐一列列车，列车一共会依次经过105个站点，从1到105编号。
 *
 * 我们已知每一位乘客的上车站点和下车站点，但是不知道这些乘客的订票顺序。
 *
 * 当一位乘客订票时，他会在当前还空余的座位中选择一个他喜欢的位置，但是我们不知道乘客的喜好，所有他具体订哪个位置我们是不知道的。
 *
 * 现在你需要计算列车最少需要安排多少座位，可以使得无论乘客的订票情况和顺序是怎么样的，所有乘客都有座位可以坐。
 *
 * 举个例子，有三位乘客：
 *
 * A：1→2
 *
 * B：2→3
 *
 * C：1→3
 *
 * 若订票顺序是A, C, B，那么只需要两个座位就一定能满足。当A订票时，他会选择一个座位，当C订票时，可用座位只剩下一个，他会订这个剩余的座位，当B订票时，可用座位也只有一个，他会订这个座位(即最开始A的那个座位)；
 *
 * 若订票顺序是A, B, C，那么有可能会需要三个座位，A订了一个座位，B订了与A不同的座位，此时C来订票时他只能订第三个座位。
 *
 * 所以对于这组例子，答案是3。
 */
