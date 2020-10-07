package com.accfcx.codeinterview;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HWMain {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String line = in.nextLine();
//
//        int len = line.length();
//        String[] strs = line.split("");
//
//        int[] nums = new int[len];
//        int size = 0;
//
//        for (int i = 0; i < len; i++) {
//            String s = strs[i];
//            int num = -1;
//            try{
//               num = Integer.parseInt(s);
//            }catch (Exception e){
//            }
//
//            if (num <=9 && num >= 0) {
//                nums[size++] = num;
//            }
//        }
//        Arrays.sort(nums, 0, size);
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < size; i++) {
//           stringBuilder.append(String.valueOf(nums[i]));
//        }
//        System.out.println(stringBuilder.toString());
//    }


    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);

        map.put("A", 10);
        map.put("B", 11);
        map.put("C", 12);
        map.put("D", 13);
        map.put("E", 14);
        map.put("F", 15);

        map.put("a", 10);
        map.put("b", 11);
        map.put("c", 12);
        map.put("d", 13);
        map.put("e", 14);
        map.put("f", 15);

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] bytes = line.split(" ");
        int packets = 0;

//        for (int i = 0; i < bytes.length; i++) {
//            if ("5a".equals(bytes[i])) {// 统计报文数
//                packets ++;
//            }
//        }
//        if (packets > 0) {
//            packets--;
//        }
        int count = 0;// 统计每个报文的净长度
        StringBuilder result = new StringBuilder();
        StringBuilder currentPacket = new StringBuilder();

        boolean doing = false; // 5a 75 cd bb 62 5a
        for (int i = 0; i < bytes.length; i++) {
            if ("5a".equals(bytes[i]) && !doing) {// start
                doing = true;
            }else if ("5a".equals(bytes[i]) && doing) {// end
                String packetLen = bytes[i - 1];
                // 校验len 需要考虑转义
                int len = 0;
                int a = map.get(packetLen.substring(0, 1));
                int b = map.get(packetLen.substring(1, 2));
                len += a * 16;
                len += b;
                if (len == count) {
                    result.append("5a " + currentPacket.toString() + packetLen + " ");
                }
                currentPacket = null;
                currentPacket = new StringBuilder();
                count = 0;
//                doing = false;
            } else if (doing){
                if ("5a".equals(bytes[i + 1])) {// 当前是长度字节
                    continue;
                }
                currentPacket.append(bytes[i] + " ");
                if ("5b".equals(bytes[i]) && ("ba".equals(bytes[i+1]) || "bb".equals(bytes[i + 1]))) {
                    i++;
                    currentPacket.append(bytes[i] + " ");
                }
                count++;
            }
        }
        if (result.length() > 0) {
            result.append("5a");
        }
        System.out.println(result.toString());
    }//5a bb 62 5a 34 cd 78 cc da fb 06 5a
}
