package com.p.seven.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
        sort();
        print();
    }

    private static void sort() {
        int n = oriArray.length;
        // i代表需要遍历的趟数
        for (int i = 0; i < n; i++) {
            // 如果前一个比后一个大，则交换位置
            for (int j = 0; j < n - 1 - i; j++) {
                int temp = 0;
                if (oriArray[j] > oriArray[j + 1]) {
                    temp = oriArray[j];
                    oriArray[j] = oriArray[j + 1];
                    oriArray[j + 1] = temp;
                }
            }
        }
    }

    private static void print() {
        for (int i = 0; i < oriArray.length; i++ ) {
            System.out.print(oriArray[i]+ ", ");
        }
    }
}
