package com.p.seven.algorithm.sort;

/**
 * 选择排序
 */
public class InsertSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
        sort();
        print();
    }

    private static void sort() {
        int n = oriArray.length;
        // i左边是已排序的有序列表
        for (int i = 1; i < n-1;i++) {
            int temp = 0;
            // 加上有序列表右边元素j，重新执行排序
            for (int j = i; j > 0; j--) {
                // 如果j比有序队列最右边小，则交换位置，继续向左比较
                if (oriArray[j] < oriArray[j-1]) {
                    temp = oriArray[j-1];
                    oriArray[j-1] = oriArray[j];
                    oriArray[j] = temp;
                } else {
                    // 如果j比有序队列最右边大，则本轮排序结束
                    break;
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
