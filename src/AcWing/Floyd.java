package AcWing;

import java.io.*;

/**
 * @FileName: Floyd.java
 * @Description: Floyd求最短路
 * @Author: ABCpril
 * @Date: 2021/11/28
 */
public class Floyd {
    static int INF = 0x3f3f3f3f;
    static int n;
    // 用邻接矩阵存储
    static int[][] dist;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int Q = Integer.parseInt(line[2]);

        dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            int z = Integer.parseInt(edge[2]);
            dist[x][y] = Math.min(dist[x][y], z);
        }

        floyd();

        while (Q-- != 0) {
            String[] query = reader.readLine().split(" ");
            int x = Integer.parseInt(query[0]);
            int y = Integer.parseInt(query[1]);
            if (dist[x][y] > INF / 2) {
                writer.write("impossible\n");
            }
            else {
                writer.write(String.valueOf(dist[x][y]).concat("\n"));
            }
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static void floyd() {
        // f[k][i][j]代表（k的取值范围是从1到n），在考虑了从1到k的节点作为中间经过的节点时，从i到j的最短路径的长度。
        // f[k][i][j]可以从两种情况转移而来：
        //    【1】从f[k−1][i][j]转移而来，表示i到j的最短路径不经过k这个节点
        //    【2】从f[k−1][i][k]+f[k−1][k][j]转移而来，表示i到j的最短路径经过k这个节点

        // 总结就是：f[k][i][j]=min(f[k−1][i][j],f[k−1][i][k]+f[k−1][k][j])
        // 从总结上来看，发现f[k]只可能与f[k−1]有关，因此可以使用滚动数组优化，省略一维
        for (int passK = 1; passK <= n; passK++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][passK] + dist[passK][j]);
                }
            }
        }
    }
}
