package com.eden;

import java.util.Arrays;

public class SortDemo {
    public static void main(String[] args) {

        int[] a = {3, 4, 52, 14, 5, 1, 2, 9, 7};

//        bubbleSort(a);

//        insertSort(a);


        quickSort(a);
    }

    private static void quickSort(int[] a) {
        int len;
        if (a == null
                || (len = a.length) == 0
                || len == 1) {
            return;
        }

        doQuickSort(a, 0, len - 1);

    }

    private static void doQuickSort(int[] a, int left, int right) {
        if (left > right)
            return;
        // base中存放基准数
        int base = a[left];
        int i = left;
        int j = right;

        while (i != j) {

            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (a[j] > base && i < j) {
                j--;
            }
            // 再从左往右边找，直到找到比base值大的数
            while (a[i] <= base && i < j) {
                i++;
            }
            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        a[left] = a[i];
        a[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        doQuickSort(a, left, i - 1);
        doQuickSort(a, i + 1, right);

        System.out.println("快排：" + Arrays.toString(a));
    }

    private static void insertSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
        System.out.println("插入排序：" + Arrays.toString(a));
    }

    private static void bubbleSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }


        System.out.println("冒泡：" + Arrays.toString(a));
    }
}
