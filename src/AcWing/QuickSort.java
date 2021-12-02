package AcWing;

import java.io.*;
import java.util.Random;

/**
 * @FileName: QuickSort.java
 * @Description: 第 k 个数(快速排序)
 * @Author: ABCpril
 * @Date: 2021/12/02
 */
public class QuickSort {
    static int n;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        int[] nums = new int[n];
        String[] data = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(data[i]);
        }

        quickSort(nums, 0, n - 1);
        writer.write(String.valueOf(nums[k - 1]).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    /**
     * 第一个区间所有的数 <= x，第二个区间 >= x
     * ①确定分界点，l、r、【(l+r)/2】、随机
     * ②调整区间（难点）
     * ③递归处理左右两段
     */
    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;

        int index = new Random().nextInt(r - l) + l;
        if (index == l) index++;
//        if (index == r) index--;
        int x = nums[index], i = l - 1, j = r + 1;

        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        // i不能取到l，否则(i, r)会变成(l, r)死循环
        quickSort(nums, l, i - 1);
        quickSort(nums, i, r);

        // j不能取到r，否则(l, j)会变成(l, r)死循环
//        quickSort(nums, l, j);
//        quickSort(nums, j + 1, r);
    }
}
