package com.p.seven.algorithm.sort;

/**
 * 选择排序
 */
public class SelectSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
        sort();
        print();
    }

    private static void sort() {
        int n = oriArray.length;
        for (int i = 0; i < n; i ++ ) {
            // 先找出当前最小值的位置
            int minIdx = i;
            for (int j = i+1; j< n;j++) {
                if (oriArray[j] < oriArray[minIdx]) {
                    minIdx = j;
                }
            }
            // 交换左边元素和最小值的位置
            int temp = oriArray[i];
            oriArray[i] = oriArray[minIdx];
            oriArray[minIdx] = temp;
        }
    }

    private static void print() {
        for (int i = 0; i < oriArray.length; i++ ) {
            System.out.print(oriArray[i]+ ", ");
        }
    }
}
