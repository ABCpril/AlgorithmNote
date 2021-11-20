package AcWing;

import java.io.*;

/**
 * @FileName: SlidingWindow.java
 * @Description: 滑动窗口最小值与最大值(数组模拟法)
 * @Author: ABCpril
 * @Date: 2021/11/21
 */
public class SlidingWindow {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        // 数组长度
        int n = Integer.parseInt(line[0]);
        int[] nums = new int[n];
        // 滑动窗口的长度
        int k = Integer.parseInt(line[1]);

        String[] data = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(data[i]);
        }

        // 数组模拟单调队列
        int[] q = new int[n];
        // 队尾吸收元素，队头排出元素
        int hh = 0, tt = -1;

        for (int i = 0; i < n; i++) {
            // 有窗口时判断队头是否落在窗口左边界外
            if (hh <= tt && q[hh] < i - k + 1) {
                hh++;
            }
            // 当前 i 元素压扁左边比它大的元素，找准自己的位置
            while (hh <= tt && nums[i] < nums[q[tt]]) {
                tt--;
            }
            // 正式吸收当前元素
            q[++tt] = i;
            // 窗口左边界 >= 0 = 窗口完整
            if (i - k + 1 >= 0) {
                writer.write(String.valueOf(nums[q[hh]]).concat(" "));
            }
        }

        writer.write("\n");
        hh = 0; tt = -1;
        for (int i = 0; i < n; i++) {
            if (hh <= tt && q[hh] < i - k + 1) {
                hh++;
            }
            // 当前 i 元素压扁左边比它小的元素，找准自己的位置
            while (hh <= tt && nums[i] > nums[q[tt]]) {
                tt--;
            }
            // 正式吸收当前元素
            q[++tt] = i;
            // 窗口滑全了才打印
            if (i - k + 1 >= 0) {
                writer.write(String.valueOf(nums[q[hh]]).concat(" "));
            }
        }

        writer.flush(); reader.close(); writer.close();
    }
}
