package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: Prim.java
 * @Description: Prim算法求最小生成树
 * @Author: ABCpril
 * @Date: 2021/11/21
 */
public class Prim {
    static int N = 510, INF = 0x3f3f3f3f;
    static int[][] g = new int[N][N];
    static int[] dist = new int[N];
    static boolean[] state = new boolean[N];
    static int n;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        for (int i = 1; i <= n; i++) {
            Arrays.fill(g[i], INF);
        }
        int m = Integer.parseInt(line[1]);

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            int w = Integer.parseInt(edge[2]);
            // 无向图，两条边
            g[u][v] = Math.min(g[u][v], w);
            g[v][u] = g[u][v];
        }

        int t = prim();
        if (t == INF) {
            writer.write("impossible\n");
        }
        else {
            writer.write(String.valueOf(t).concat("\n"));
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static int prim() {
        Arrays.fill(dist, INF);
        // 树的权值总和
        int res = 0;

        // 迭代 n 次，每次将 1 个点加入最小生成树集合
        for (int i = 1; i <= n; i++) {
            // 找距离集合最近的点
            int t = -1;
            for (int j = 1; j <= n; j++) {
                if (!state[j] && (t == -1 || dist[j] < dist[t])) {
                    t = j;
                }
            }
            // 不是第一次选点，距离INF代表无法联通
            if (i > 1 && dist[t] == INF) {
                return INF;
            }
            // 提前把边权加入最小生成树结果，防止更新出边的时候，自己指向自己是负环，把自己更新了，结果就不对了
            if (i > 1) res += dist[t];

            // 更新出边
            for (int j = 1; j <= n; j++) {
                dist[j] = Math.min(dist[j], g[t][j]);
            }
            state[t] = true;
        }

        return res;
    }
}
