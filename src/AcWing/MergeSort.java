package AcWing;

import java.io.*;

/**
 * @FileName: MergeSort.java
 * @Description: 归并排序
 * @Author: ABCpril
 * @Date: 2021/11/29
 */
public class MergeSort {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];
        String[] data = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(data[i]);
        }

        int[] temp = new int[n];
        mergeSort(nums, 0, n - 1, temp);
        for (int i = 0; i < n; i++) {
            writer.write(String.valueOf(nums[i]).concat(" "));
        }
        writer.flush(); reader.close(); writer.close();
    }

    // 1.递归的定义：分别对左右两半进行排序，再将左右分别有序的两半合并为有序整体
    private static void mergeSort(int[] nums, int l, int r, int[] temp) {
        // 3.递归的出口
        if (l >= r) return;

        // 2.递归的拆解
        int mid = (l + r) >> 1;
        mergeSort(nums, l, mid, temp);
        mergeSort(nums, mid + 1, r, temp);

        // 左右结果合并
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            }
            else {
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= r) {
            temp[k++] = nums[j++];
        }
        // 把temp复制回原数组
        for (i = l, k = 0; i <= r; ) {
            nums[i++] = temp[k++];
        }
    }
}
