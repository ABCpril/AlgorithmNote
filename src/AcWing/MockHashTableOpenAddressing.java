package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: MockHashTableOpenAddressing.java
 * @Description: 开放寻址法模拟散列表
 * @Author: ABCpril
 * @Date: 2021/11/21
 */
public class MockHashTableOpenAddressing {
    // 开2～3倍，取质数
    static int N = 200003;
    static int[] h = new int[N];
    static int bound = 0x3f3f3f3f;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Arrays.fill(h, bound);
        String line = reader.readLine();
        int operCnt = Integer.parseInt(line);

        while (operCnt-- != 0) {
            String[] oper = reader.readLine().split(" ");
            String cmd = oper[0];
            int x = Integer.parseInt(oper[1]);
            int k = find(x);
            if ("I".equals(cmd)) {
                h[k] = x;
            }
            else if ("Q".equals(cmd)) {
                // 返回的是可以插入 x 的下标位置，说明 x 不存在
                if (h[k] == bound) {
                    writer.write("No\n");
                }
                else {
                    writer.write("Yes\n");
                }
            }
        }

        writer.flush(); reader.close(); writer.close();
    }

    // 返回 x 所在的下标，若 x 不存在，返回应该插入 x 的下标位置
    private static int find(int x) {
        // 为了让负数在正整数有映射，负数取模后仍为负数，+N为正后再取模即可
        int k = (x % N + N) % N;
        while (h[k] != bound && h[k] != x) {
            k++;
            if (k == N) {
                k = 0;
            }
        }
        return k;
    }
}
