package AcWing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName: SPFA.java
 * @Description: spfa判断负环
 * @Author: ABCpril
 * @Date: 2021/11/28
 */
public class SPFA {
    static int n, idx;
    // 记录每一个点到虚拟源点的最短距离
    static int[] dist;
    // 标记在队列与否，防止队列中同时出现多个x点
    static boolean[] state;
    static int[] h, e, ne, w;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        dist = new int[n + 1];
        state = new boolean[n + 1];
        h = new int[n + 1];
        e = new int[m];
        ne = new int[m];
        w = new int[m];
        Arrays.fill(h, -1);

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            int z = Integer.parseInt(edge[2]);
            add(x, y, z);
        }

        boolean hasLoop = spfa();
        writer.write(hasLoop ? "Yes\n" : "No\n");
        writer.flush(); reader.close(); writer.close();
    }

    private static boolean spfa() {
        Queue<Integer> queue = new LinkedList<>();
        // 虚拟源点到各个点都有一条长度为 0 的边
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
            state[i] = true;
        }

        // 各个点到虚拟源点最短路经过的边的条数
        int[] cnt = new int[n + 1];
        while (!queue.isEmpty()) {
            Integer curt = queue.poll();
            state[curt] = false;
            // spfa相较于bellman的不同，只松弛相邻边
            for (int i = h[curt]; i != -1; i = ne[i]) {
                int j = e[i];
                // dist更新的点，它的相邻边才有松弛的价值
                if (dist[j] > dist[curt] + w[i]) {
                    dist[j] = dist[curt] + w[i];
                    cnt[j] = cnt[curt] + 1;
                    if (cnt[j] > n - 1) {
                        return true;
                    }
                    // 如果点j到虚拟源点最短距离变小，j可能会再次进入队列，因此可以检验负环
                    if (!state[j]) {
                        queue.offer(j);
                        state[j] = true;
                    }
                }
            }
        }

        return false;
    }

    private static void add(int a, int b, int c) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; w[idx] = c; idx++;
    }
}
