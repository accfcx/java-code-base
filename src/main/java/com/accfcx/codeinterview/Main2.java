package com.accfcx.codeinterview;

public class Main2 {
    public static void main(String[] args) {
        // 找到删除一个数后，最大的乘积值
        long[] nums = {1,3, 4, 7, 11};

        // 用于记录删除nums 第i位后，剩下的数的乘积
        long[] dp = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }
        // 统计原数组0的个数
        int zeroNum = 0;
        // 记录0的下标，目标是用于只有1个0的情况
        int zeroIndex = 0;

        // 先计算出所有的数的乘积
        long value = 1;
        for (int i =0; i < nums.length;  i++) {
            if (nums[i] == 0) {
                zeroNum ++;
                zeroIndex = i;
            }
            value *= nums[i];
        }
        if (zeroNum > 1) {
            System.out.println(0);
        }else if (zeroNum == 1) {
            for (int i=0; i < nums[i]; i++) {
                if (i != zeroIndex){
                    dp[i] = 0;
                }
            }
        }

        long max = -999999;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] != 0) {
                dp[i] = (int) (value / nums[i]);
            }
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        System.out.println(max);
    }
}
