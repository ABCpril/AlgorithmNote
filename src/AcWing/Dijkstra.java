package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: Dijkstra.java
 * @Description: 朴素Dijkstra
 * @Author: ABCpril
 * @Date: 2021/12/03
 */
public class Dijkstra {
    static int N = 510, M = 100010, INF = 0x3f3f3f3f;
    // 稠密图用邻接矩阵存
    static int[][] g = new int[N][N];
    static int[] dist = new int[N];
    static boolean[] state = new boolean[N];
    static int n;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], INF);
            dist[i] = INF;
        }

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int c = Integer.parseInt(edge[2]);
            g[a][b] = Math.min(g[a][b], c);
        }

        int t = dijkstra();
        writer.write(String.valueOf(t).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static int dijkstra() {
        dist[1] = 0;
        // 每一轮确认(一个点到1号点最短距离已确定)这一事实
        for (int i = 0; i < n; i++) {
            int t = -1;
            for (int j = 1; j <= n; j++) {
                // 在最短距离没确定的点中贪心选取离1号点最近的点
                if (!state[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            state[t] = true;
            // 松弛相邻边
            for (int j = 1; j <= n; j++) {
                // 可加可不加state[j]=false的判断，因为最短距离已确定的点，dist[t]+g[t][j]只会更大
                dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
            }
        }
        if (dist[n] == INF) return -1;
        return dist[n];
    }
}
