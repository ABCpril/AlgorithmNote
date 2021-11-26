package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: MostMatches.java
 * @Description: 二分图的最大匹配（匈牙利算法）
 * @Author: ABCpril
 * @Date: 2021/11/26
 */
public class MostMatches {
    static int N = 510, idx;
    // 邻接表存稀疏图
    static int[] e, ne, h;
    // 记录第 X 个女孩当前匹配的男生
    static int[] match;
    // 预订数组，只在当前轮有效
    // 起到原来匹配的男生准备让的时候，不会再考虑同一个女孩的作用
    static boolean[] state = new boolean[N];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int n1 = Integer.parseInt(line[0]);
        int n2 = Integer.parseInt(line[1]);
        int m = Integer.parseInt(line[2]);

        e = new int[m];
        ne = new int[m];
        h = new int[n1 + 1];
        Arrays.fill(h, -1);
        match = new int[n2 + 1];
        state = new boolean[n2 + 1];

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            add(a, b);
        }

        int res = 0;
        // 为每一个男生寻找匹配女孩
        for (int i = 1; i <= n1; i++) {
            Arrays.fill(state, false);
            // 找到了
            if (find(i)) {
                res++;
            }
        }

        writer.write(String.valueOf(res).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static boolean find(int boy) {
        for (int i = h[boy]; i != -1; i = ne[i]) {
            int girl = e[i];
            if (!state[girl]) {
                state[girl] = true;
                // 如果女孩没有人占，或者女孩当前匹配的男生可以让出
                if (match[girl] == 0 || find(match[girl])) {
                    match[girl] = boy;
                    return true;
                }
            }
        }
        return false;
    }

    private static void add(int a, int b) {
        // 虽然是无向图，但是只看左半边男生指向右半边女孩的边，所以只添加边一次
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; idx++;
    }
}
