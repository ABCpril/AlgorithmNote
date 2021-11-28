package AcWing;

import java.io.*;

/**
 * @FileName: PrevSmaller.java
 * @Description: 单调栈
 * @Author: ABCpril
 * @Date: 2021/11/28
 */
public class PrevSmaller {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        int[] nums = new int[n];
        // 单调栈中存下标通用性更好，这里存数即可
        int[] stk = new int[n + 1];
        // 栈顶指针指向 0 代表栈空
        int tt = 0;

        String[] data = reader.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(data[i]);
            // nums[i]是我们目前拿到的最靠右的数，如果它比左边的数还小
            // 那么栈里没必要存这些数了，因为遍历 i 后面的数时，nums[i]就已经符合要求了，不需要再看 i 左边的数
            // 对于 i 左边比nums[i]更小的数需要保留，因为当nums[i]大小不符合要求时，这些数仍有机会满足大小要求
            while (tt > 0 && nums[i] <= stk[tt]) {
                tt--;
            }
            if (tt > 0) {
                writer.write(String.valueOf(stk[tt]).concat(" "));
            }
            else if (tt == 0) {
                writer.write("-1 ");
            }
            stk[++tt] = nums[i];
        }

        writer.flush(); reader.close(); writer.close();
    }
}
