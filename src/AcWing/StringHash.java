package AcWing;

import java.io.*;

/**
 * @FileName: StringHash.java
 * @Description: 字符串哈希
 * @Author: ABCpril
 * @Date: 2021/11/29
 */
public class StringHash {
    static int N = 100010;
    // 字符串前缀和，比较两字符串是否相同，就是比较两区间的部分和是否相等
    static long[] h = new long[N];
    static int P = 131;
    static long[] p;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        // 字符串长度
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        String str = reader.readLine();

        // P = 131的次方数组，溢出等价于对 2^64 取模
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + str.charAt(i - 1);
        }

        while (m-- != 0) {
            String[] query = reader.readLine().split(" ");
            int l1 = Integer.parseInt(query[0]);
            int r1 = Integer.parseInt(query[1]);
            int l2 = Integer.parseInt(query[2]);
            int r2 = Integer.parseInt(query[3]);
            writer.write(compute(l1, r1) == compute(l2, r2) ? "Yes\n" : "No\n");
        }

        writer.flush(); reader.close(); writer.close();
    }

    // 由前缀和计算部分和
    private static long compute(int l, int r) {
        // ABCDE 中求 DE，就用ABCDE - ABC * P ^ (r - l + 1)
        // ABCDE里的ABC比纯ABC多乘了 P的 2 次方
        //    ABCDE   ABC
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
