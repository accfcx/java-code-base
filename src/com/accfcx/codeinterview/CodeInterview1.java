package com.accfcx.codeinterview;

import java.util.*;

public class CodeInterview1 {

    public static void main(String[] args) {
	// write your code here
        String s = "I am a student.";
        LinkedList<Integer> list = new LinkedList<>();
//        int[] array = {10,14,12,11};
        int[] array = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows(array, 3));
        int i = 1;
        int j = 2;
        int k = 3;
    }

    public void test1() {
        // 剑指offer P208
        /**
         *
         * 采用阵地攻守的思想：
         * 第一个数字作为第一个士兵，守阵地；count = 1；
         * 遇到相同元素，count++;
         * 遇到不相同元素，即为敌人，同归于尽,count--；当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
         * 再加一次循环，记录这个士兵的个数看是否大于数组一般即可。
         */
        int [] array = {1,2,3,2,4,2,5,2,3};

        int result = array[0];
        int times = 1;
        for (int i = 1; i< array.length; i++) {
            if(times == 0) {
                result = array[i];
                times = 1;
            } else if(array[i] == result){
                times++;
            } else {
                times--;
            }
        }
        System.out.println(result);
    }

    public void test2() {
        // Java 最大堆
        int k = 6;

        // 重写最小堆为最大堆 了解比较器以及数据结构的用法
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        PriorityQueue<Integer> queue =
        new PriorityQueue<Integer>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

    }

    public int test3() {
        // 剑指 p231
        // todo 使用循环改造递归实现优化
        String num = "12258";
        char[] chs = new char[26];
        boolean[] visited = new boolean[num.length()];
        for(int i = 0; i< chs.length; i++) {
            chs[i] = (char) ('a' + i);
        }
        int index = 0;
        return handleFor3(num, index, visited);

    }
    private int handleFor3(String num, int index, boolean [] visited) {
        if(index == num.length()) {
            return 1;
        }
        if(index > num.length()) {
            return 0;
        }
        // 尝试当前1位和2位
        // 先尝试1位
        int result = 0;
        if(!visited[index]) {
            visited[index] = true;
            result += handleFor3(num, index + 1, visited);
            visited[index] = false;
        }

        // 再尝试2位，需要校验是否<=25
        if(index + 1 < num.length() && !visited[index] && !visited[index + 1] && Integer.parseInt(num.substring(index, index + 2)) <= 25) {
            visited[index] = true;
            visited[index + 1] = true;
            result += handleFor3(num, index + 2, visited);
            visited[index] = false;
            visited[index + 1] = false;
        }
        return result;
    }

    public int maxValue() {
        // dp 剑指 p233
        // todo 空间优化
        // dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]) + value[i][j]
        int[][] value = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        int rows = value.length;
        int cols = value[0].length;
        int[][] dp = new int[rows][cols];

        // init
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                dp[i][j] = value[i][j];
            }
        }
        // dp handle
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(row > 0 && col > 0) {
                    dp[row][col] += Math.max(dp[row - 1][col], dp[row][col - 1]);
                } else if(row > 0) {
                    dp[row][col] += dp[row - 1][col];
                } else if(col > 0){
                    dp[row][col] += dp[row][col - 1];
                }
            }
        }
        return dp[rows - 1][cols - 1];
    }

    public int maxLen43() {
        // 剑指 P236
        int max = 0;
        String str = "arabcacfr";
        String curStr = "";
        for(int i = 0; i < str.length(); i++) {
            // current char
            String curChar = str.substring(i, i + 1);

            // judge char in curStr
            if(curStr.contains(curChar)) {
                int chIndex = curStr.indexOf(curChar);
                // 截断重复字符后的字符串，并拼接重复字符；重新开始处理
                curStr = curStr.substring(chIndex + 1);
            }
            curStr =  curStr.concat(curChar);
            if(max < curStr.length()) {
                max = curStr.length();
            }
        }
        return max;
    }

    public int uglyNumFor49(int index) {
        // Wrong!
        int[] array = new int[index + 1];

        array[0] = 1;
        if(index > 1) {
            array[1] = 2;
        }
        if(index > 2) {
            array[2] = 3;
        }
        if(index > 3) {
            array[3] = 5;
        }

        // round
        int round = (index - 1) / 3;
        if((index-1) % 3 != 0) {
            round++;
        }
        for(int i =1; i < round ; i++) {
            // a b c -> a * b     a * c    b * c
            array[i * 3 + 1] = array[(i - 1) * 3 + 1] * array[(i - 1) * 3 + 2];
            array[i * 3 + 2] = array[(i -  1) * 3 + 1] * array[i * 3];
            array[i * 3 + 3] = array[(i - 1) * 3 + 2] * array[ i * 3];

        }
        return array[index - 1];
    }

    public char FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }
        String result = null;
        Map<String, Integer> map = new LinkedHashMap<>();
        String[] array = str.split("");
        int len = array.length;
        for(String s : array) {
            if(map.get(s) == null) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s)  + 1);
            }
        }

        // 遍历map
        for(String s: map.keySet()) {
            if(map.get(s) == 1) {
                result = s;
                break;
            }
        }

        return result.charAt(0);
    }

    public int binarySearch(int[] array, int left, int right, int k) {
        if(left >= array.length) {
            return -1;
        }
        if(left > right) {
            return -1;
        }
        int mid =(left + right) >> 1;
        if(array[mid] == k) {
            //
            if((mid - 1>=0 && array[mid-1] != k) || mid == 0) {
                return mid;
            } else {
                right = mid - 1;
            }
        }else if (array[mid] < k){
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return binarySearch(array, left, right, k);
    }

    public int lostNum(int[] array, int left, int right) {
        int mid = (left + right) >> 1;
        int result = -1;
        while(left <= right) {
            if(array[mid] != mid) {
                // 剑指 p265 二分相关 todo 边界怎么确定
                if(mid - 1 >= left && array[mid - 1] != mid - 1) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            } else {
                left = mid + 1;
            }
            mid = (left + right) >> 1;
        }
        return result;
    }

    public int equalsNum(int[] array, int left, int right) {
        if(array == null || array.length == 0) {
            return -1;
        }

        while(left <= right) {
            int mid = (left + right) >> 1;
            if(array[mid] == mid) {
                return mid;
            } else if(array[mid] < mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

    public String ReverseSentence(String str) {
        if(str == null || str.length() == 0) {
            return "";
        }
        StringBuilder allReversed = reverseStr(new StringBuilder(str), 0, str.length() - 1);

        int i = 0;
        while(i < allReversed.length()) {
            if(allReversed.charAt(i) == ' ') {
                i++;
            }
            int j = i;
            while(j < allReversed.length() && allReversed.charAt(j) != ' ') {
                j++;
            }
            reverseStr(allReversed, i, j-1);
            i = j;
        }
        return allReversed.toString();
    }

    public  StringBuilder reverseStr(StringBuilder sb, int start, int end) {
        int mid = (start + end) >> 1;
        for(int i = start; i <= mid; i++) {
            char first = sb.charAt(i);
            char last = sb.charAt(end- i + start);
            sb.setCharAt(i, last);
            sb.setCharAt(end - i + start, first);
        }
        return sb;
    }

    public static  ArrayList<Integer> maxInWindows(int [] num, int size){
        // P288 todo 过程未理解
        ArrayList<Integer> result  = new ArrayList<>();
        if(num == null || num.length == 0 || size < 1) {
            return result;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(0);
        for(int i = 1; i < num.length; i++ ) {
            if(i - queue.peek() >= size) {
                result.add(num[queue.peek()]);
                queue.pollFirst();
            }
            // 当前值大于最大值；
            if(queue.size() == 0 || num[i] > num[queue.peek()]) {
                while(queue.size() != 0) {
                    queue.poll();
                }
                queue.offer(i);
            } else{
                while(num[queue.peekLast()] < num[i]) {
                    queue.pollLast();
                }
                queue.offer(i);
            }
//            if(i >= size - 1) {
//                result.add(num[queue.peek()]);
//            }

        }
        result.add(num[queue.peek()]);
        System.out.println(queue);
        return result;
    }
}
