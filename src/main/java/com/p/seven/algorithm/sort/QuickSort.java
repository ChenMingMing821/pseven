package com.p.seven.algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
        int[] result = sort(oriArray, 0, oriArray.length - 1);
        print(result);
    }

    private static int[] sort(int[] arr, int left, int right) {
        if (left < right) {
            int index = partition(arr, left, right);
            print(arr);
            System.out.println(left + "-" + (index - 1) + ", " + (index + 1) + "-" + right);

            sort(arr, left, index - 1);
            sort(arr, index + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 定义左边第一个为基准值，基准值是不会变的
        int pivot = arr[left];

        // 此时需要定义3个变量：最左边位置left, 最右边位置right, 当前比较的位置index
        // 因为最左边实际是pivot，所以初始时index的位置是不等于left的，最终每轮index都会+1，
        // 而left和right会根据比较情况移动一位，最终当index(=left+1)=right时，比较结束。
        //int left = 0;
        //int right = oriArray.length - 1;
        int index = left + 1;

        // 循环终止的条件是index(=left+1)=right, 或者是left < right
        while (left < right) {
            int temp = 0;
            if (arr[index] <= pivot) {
                // 将index位置的数据移动到left，left向右一位，index向右一位
                arr[left++] = arr[index++];
            } else if (arr[index] > pivot) {
                // 将index位置的数据移动到right，将right数据移动到index，right向左一位，index不变
                temp = arr[right];
                arr[right--] = arr[index];
                arr[index] = temp;
            }
            print(arr);
        }
        arr[left] = pivot;

        return --index;
    }

    private static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
