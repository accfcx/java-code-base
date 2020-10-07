package com.accfcx.codeinterview;

import java.util.*;

public class Main {
//    public static void main(String[] args) throws Exception{
//        Scanner in = new Scanner(System.in);
//        Long  n;
//        Long  sum = 0L;
//        n = in.nextLong ();
//
//        if(n<3) {
//            throw new Exception("牌数不满足扎银花的条件");
//        }
//
//        // 最大堆;用于保存最大的3个数
//        PriorityQueue<Long> maxHeapA = new PriorityQueue<Long>(3, new Comparator<Long >() {
//            @Override
//            public int  compare(Long  o1, Long  o2) {
//                return o2.compareTo(o1);
//            }
//        });
//        PriorityQueue<Long> maxHeapB = new PriorityQueue<Long>(3, new Comparator<Long >() {
//            @Override
//            public int  compare(Long  o1, Long  o2) {
//                return o2.compareTo(o1);
//            }
//        });
//
//        int  j = 0;
//        // 先读取第一组牌
//
//        for(j = 0; j < n; j++) {
//            Long  value = in.nextLong ();
//            if(maxHeapA.size() < n) {
//                maxHeapA.offer(value);
//            } else {
//                if(maxHeapA.peek() <= value) {
//                    maxHeapA.poll();
//                    maxHeapA.offer(value);
//                }
//            }
//        }
//
//        j = 0;
//        // 读取第二组牌
//        for(j = 0; j < n; j++) {
//            Long  value = in.nextLong ();
//            if(maxHeapB.size() < n) {
//                maxHeapB.offer(value);
//            } else {
//                if(maxHeapB.peek() <= value) {
//                    maxHeapB.poll();
//                    maxHeapB.offer(value);
//                }
//            }
//        }
//        Long  sum1 = 0L;
//        Long  sum2 = 0L;
//
//        for(j = 0; j < 3; j++) {
//            Long  value1 = maxHeapA.poll();
//            Long  value2 = maxHeapB.poll();
//            sum1+=value1;
//            sum2+=value2;
//        }
//        System.out.println((Math.max(sum1, sum2)));
//    }

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int  n;
//        n = in.nextInt ();
//
//        int i = 0;
//        int[] array = new int[n];
//        int[] deletedArray = new int[n-1];
//        while(i < n) {
//            array[i++] = in.nextInt();
//        }
//        i = 0;
//        int max = 0;
//        // 每次循环输入为去掉第i位的数组；返回位当前数组的maxLen
//        for(; i < n; i++) {
//            int x = 0;
//            // 复制前半部分
//            for(int k = 0; k < i; k++) {
//                deletedArray[x++] = array[k];
//            }
//            // 复制后半部分
//            for(int j = i + 1; j < n; j++) {
//                deletedArray[x++] = array[j];
//            }
//
//            int len = maxLength(deletedArray);
//                if(max < len) {
//                    max = len;
//                }
//        }
//        System.out.println(max);
//
//    }
//    // 怎么找到一组数中的最长上升子序列的长度
//    // 查找删除第一个数字后的最长上升子串的长度
//
//    private static int maxLength(int[] nums) {
//        int max = 1;
//
//        int tempLength = 0;
//        for(int i = 0; i < nums.length; i++) {
//            // 遍历过程中调整临时长度
//
//            if(tempLength == 0) {
//                tempLength=1;
//                i++;
//                continue;
//            }
//            if(nums[i] > nums[i - 1]) {
//                tempLength ++;
//            } else {
//                tempLength = 1;
////                i++;
//            }
//            // 更新最长长度
//            if(tempLength > max) {
//                max = tempLength;
//            }
//        }
//        return max;
//    }
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n, k, m;
//        int p,q;
//        n = in.nextInt();
//        k = in.nextInt();
//        m = in.nextInt();
//
//        p = in.nextInt();
//        q = in.nextInt();
//        int[] times = new int[k];
//        int i = 0;
//        while(i < k) {
//            times[i++] = in.nextInt();
//        }
////        System.out.println(maxScore(m,));
//
//    }
//
//    // 背包问题 m 中放k个东西，数量最多
//    public static int maxScore(int m, int[] value, int[] time) {
//         // 背包问题，先构造最基本的结构
//         int[] dp = new int[m + 1];
//
//         // dp[i] = max(dp[i - time[i]] + value[i],
//         for(int i = 0; i < value.length; i++ ) {
//             dp[i] = value[i];
//         }
//         for(int i = 1; i < value.length; i++) {
//             dp[i] = Math.max(dp[i - time[i]] + value[i], dp[i]);
//         }
//         return dp[m];
//    }
//}

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int[] height = new int[10000];
//        int num = 0;
//        while(in.hasNext()) {
//            height[num] = in.nextInt();
//            num++;
//        }
//        System.out.println(num);
//        System.out.println(height);
//        int[] heights = {175, 173, 174, 163, 182, 177};
        int[] heights = {199, 13, 11, 10, 12, 177};
        int[] results = DistanceToHigher(heights);
        for (int i : results) {
            System.out.print(i + " ");
        }
//        int [] A = {1, 22, 22, 33, 22, 12, 45, 44, 5};
//        int [] A = {1 ,22 ,54 ,123};
//        int[] A =
//        Arrays.sort(A);
//        for(int c : A) {
//            System.out.print(c + " ");
//        }
//        secondProblem(A);
    }

    public static int[] DistanceToHigher (int[] height) {
        int[] results = new int[height.length];
        int headMaxIndex = 0;
        if(height.length > 0) {
            results[0] = 0;
        }
        for(int i = 1; i < height.length; i++) {
            // 比较当前数和前一个数
            if(height[i] < height[i - 1]) {
                results[i] = i - (i - 1);
            } else {
                // 比较当前位和i位前的临时临时最大值
                if(height[i] < height[headMaxIndex]) {
                    results[i] = i - (headMaxIndex);
                } else {
                    results[i] = 0;
                }
            }

            if(height[i] > height[headMaxIndex]) {
               headMaxIndex = i;
            }
        }
        return results;
    }

    public static List<Integer> secondProblem(int[] A) {
        List<Integer> result = new ArrayList<>();
        if(A == null || A.length == 0) {
            result.add(-1);
            return result;
        }
        // 标记最大值是否多次出现
        boolean again = false;
        // 标记最大值和次大值下标
        int firstMaxIndex = 0;
        int secondMaxIndex = 0;
        for(int i = 1; i < A.length; i++) {
            // 规律稍后考虑
            // 比较当前值和最大值
            if(A[firstMaxIndex] > A[i] && A[i] >= A[secondMaxIndex]) {
                // A > C > B
                if(!again) {
                    result.add(i);
                }
            } else if(A[firstMaxIndex] == A[i]) {
                // 最大值重复出现
                again = true;
            } else {
                // 更新最大值和次大值
                secondMaxIndex =firstMaxIndex;
                firstMaxIndex = i;
                again = false;
            }
        }
        if(result.size() == 0) {
            result.add(-1);
        }
        System.out.println(result);
        return result;
    }

    public List<String> phoneNumber(String strs) {
        List<String> result = new ArrayList<>();
        String[] phones = strs.split(",");

        return result;
    }

    // 计分标志 20+豹子 10+顺子
    // 统计一个号码的分数:不管是顺子还是豹子： 1位对于1分，8位对应8分
    public int getScore(String phone) {
        String[] phones = phone.substring(3).split("");
        int[] nums = new int[8];
        for(int i = 0; i< 8; i++) {
            nums[i] = Integer.parseInt(phones[i]);
        }
        Arrays.sort(nums);
        int sScore = 10;
        boolean asc = true;

        int bScore = 20;
        // 先找顺子
        // asc
        for(int i = 4; i < 11; i++) {
//            i
        }
        // desc
        // 再找豹子

        return 0;

    }
}

