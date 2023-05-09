package com.example.springbootstudy.algorithm;

import java.util.Arrays;

/**
 * @author xhq
 * @version 1.0
 * @description Create by 2023/4/27 14:12
 */
public class WxRedPacket {

    public static void main(String[] args) {
        double sum = 0.0;
        double[] doubles = distributeRedPacket(10, 10);
        for (double temp : doubles){
            System.out.println(temp);
            sum += temp;
        }
        System.out.println(sum);
    }


    public static double[] distributeRedPacket(int totalAmount, int count) {
        double[] amounts = new double[count];
        Arrays.fill(amounts, 0.01);
        double leftAmount = totalAmount - count * 0.01;
        double factor = leftAmount / count;
        for (int i = 0; i < count; i++) {
            double rand = Math.random();
            double amount = amounts[i] + rand * factor;
            amounts[i] = (double) Math.round(amount * 100) / 100;
            leftAmount -= amounts[i] - 0.01;
        }
        while (leftAmount > 0) {
            for (int i = 0; i < count; i++) {
                if (leftAmount <= 0) {
                    break;
                }
                amounts[i] += 0.01;
                leftAmount -= 0.01;
            }
        }
        return amounts;
    }
}
