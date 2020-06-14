package com.p.seven.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
        sort();
        print();
    }

    private static void sort() {
        int len = oriArray.length;

        // 进行分组，最开始的增量为数组长度的一半，每轮减半
        for (int round = len / 2; round > 0; round = round / 2) {
            // 找到第二个分组，开始向前比较，逐渐递增
            for (int i = round; i < len; i++) {
                // 分别和之前的每组数字分别比较
                for (int j = i - round; j >= 0; j = j - round) {
                    if (oriArray[j + round] < oriArray[j]) {
                        int temp = oriArray[j];
                        oriArray[j] = oriArray[j + round];
                        oriArray[j + round] = temp;
                    }
                }
            }
        }
    }

    private static void print() {
        for (int i = 0; i < oriArray.length; i++) {
            System.out.print(oriArray[i] + ", ");
        }
    }
}
