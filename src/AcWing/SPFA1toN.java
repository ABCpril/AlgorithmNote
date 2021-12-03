package AcWing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName: SPFA1toN.java
 * @Description: spfa求最短路
 * @Author: ABCpril
 * @Date: 2021/12/03
 */
public class SPFA1toN {
    static int N = 100010, M = 100010, INF = 0x3f3f3f3f;
    static int[] h, e, ne, w;
    static int[] dist = new int[N];
    // 标记在队列与否
    static boolean[] state = new boolean[N];
    static int idx, n;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        h = new int[n + 1];
        e = new int[m];
        ne = new int[m];
        w = new int[m];
        Arrays.fill(h, -1);

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int c = Integer.parseInt(edge[2]);
            add(a, b, c);
        }

        spfa();
        writer.write(dist[n] > INF / 2 ? "impossible" : String.valueOf(dist[n]));
        writer.flush(); reader.close(); writer.close();
    }

    private static void spfa() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        state[1] = true;

        while (!queue.isEmpty()) {
            int curt = queue.poll();
            // 出队列，st设为false
            state[curt] = false;
            // spfa与BellmanFord的区别，只松弛相邻边
            for (int i = h[curt]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[curt] + w[i]) {
                    dist[j] = dist[curt] + w[i];
                    // dist更新过的点，其相邻边才有松弛的价值，入队待命
                    // state数组并不是防止j多次入队，只是防止队列中同时存在多个j
                    if (!state[j]) {
                        // 如果当前的点距离变小，可能会再次进入队列，因此可以检验负环
                        queue.offer(j);
                        state[j] = true;
                    }
                }
            }
        }
    }

    private static void add(int a, int b, int c) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; w[idx] = c; idx++;
    }
}
