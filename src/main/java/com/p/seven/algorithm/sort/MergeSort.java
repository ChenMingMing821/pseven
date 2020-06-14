package com.p.seven.algorithm.sort;

/**
 * 归并排序
 */
public class MergeSort {
    private static int[] oriArray = new int[]{9, 6, 5, 3, 8, 12, 43, 6, 7, 3, 16, 11, 13, 19, 24, 26, 28};

    public static void main(String[] args) {
//        sort1();
        int[] result = new int[oriArray.length];
        sort2(oriArray, result, 0, oriArray.length - 1);
        oriArray = result;
        print();
    }

    private static void sort1() {
        int len = oriArray.length;
        int[] result = new int[len];

        // 从每个分组1个元素开始迭代，每次分组元素合并后变成原来的2倍
        for (int block = 1; block < (len / 2) + 1; block *= 2) {
            for (int s = 0; s < len; s += 2 * block) {
                int low = s;
                int mid = s + block;
                int hign = s + 2 * block;
                // 计算两个块的下标[,)
                int b1s = low;
                int b1e = mid;
                int b2s = mid;
                int b2e = hign;

                // 对两个block进行归并操作
                while (b1s < b1e && b2s < b2e) {
                    result[low++] = (oriArray[b1s] < oriArray[b2s]) ? oriArray[b1s++] : oriArray[b2s++];
                }
                // 如果两个block合并结束后，仍有一个block有数据，则需要单独处理
                while (b1s < b1e) {
                    result[low++] = oriArray[b1s++];
                }
                while (b2s < b2e) {
                    result[low++] = oriArray[b2s++];
                }
            }
        }

        oriArray = result;
    }

    private static void sort2(int[] arr, int[] result, int s, int e) {
        if (s >= e) {
            return;
        }

        int len = e - s;
        int mid = (len / 2) + s;
        // 计算两个块的下标[,)
        int b1s = s;
        int b1e = mid;
        int b2s = mid;
        int b2e = e;

        // 递归拆分
        sort2(arr, result, b1s, b1e);
        sort2(arr, result, b2s, b2e);

        // 对两个block进行归并操作
        int k = s;
        while (b1s < b1e && b2s < b2e) {
            result[s++] = (arr[b1s] < arr[b2s]) ? arr[b1s++] : arr[b2s++];
        }
        // 如果两个block合并结束后，仍有一个block有数据，则需要单独处理
        while (b1s < b1e) {
            result[s++] = arr[b1s++];
        }
        while (b2s < b2e) {
            result[s++] = arr[b2s++];
        }

        // 将合并后的子集更新到源数据中
        for (int i = s; i <= e; i++) {
            arr[i] = result[i];
        }
    }

    private static void print() {
        for (int i = 0; i < oriArray.length; i++) {
            System.out.print(oriArray[i] + ", ");
        }
    }
}
