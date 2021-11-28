package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: BellmanFord.java
 * @Description: 有边数限制的最短路
 * @Author: ABCpril
 * @Date: 2021/11/28
 */
public class BellmanFord {
    static int INF = 0x3f3f3f3f;
    static MyEdge[] edges;
    static int n, m, k;
    static int[] dist;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        k = Integer.parseInt(line[2]);

        edges = new MyEdge[m];
        dist = new int[n + 1];

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int w = Integer.parseInt(edge[2]);
            edges[i] = new MyEdge(a, b, w);
        }

        int t = bellmanFord();
        if (t == INF) {
            writer.write("impossible\n");
        }
        else {
            writer.write(String.valueOf(t).concat("\n"));
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static int bellmanFord() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        int[] backup;
        // k 代表路径经过的边数
        // 假设 1 号点到 n 号点是可达的，每一个点同时向指向的方向出发，更新相邻的点的最短距离
        // 通过循环 n-1 次操作，若图中不存在负环，则 1 号点一定会到达 n 号点
        for (int i = 1; i <= k; i++) {
            // 需要备份上一轮的dist结果
            backup = dist.clone();
            // for 所有边
            for (int j = 0; j < m; j++) {
                MyEdge edge = edges[j];
                int a = edge.a, b = edge.b, w = edge.w;
                // 由于是每个点同时向外出发，需防止串联效应
                // dist[a]如果在本轮已经更新过，那么拿dist[a]更新dist[b]，得到的并不是正确的本轮dist[b]结果
                // 松弛操作
                if (dist[b] > backup[a] + w) {
                    dist[b] = backup[a] + w;
                }
            }
        }

        if (dist[n] > INF / 2) return INF;
        else return dist[n];
    }
}

class MyEdge {
    int a; int b; int w;
    public MyEdge(int a, int b, int w) {
        this.a = a;
        this.b = b;
        this.w = w;
    }
}
