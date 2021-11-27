package AcWing;

import java.io.*;
import java.util.Arrays;

/**
 * @FileName: Kruskal.java
 * @Description: Kruskal算法求最小生成树
 * @Author: ABCpril
 * @Date: 2021/11/27
 */
public class Kruskal {
    static int N = 100010, M = 200010, INF = 0x3f3f3f3f;
    static int[] p;
    static Edge[] edges;
    static int n;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int w = Integer.parseInt(edge[2]);
            edges[i] = new Edge(a, b, w);
        }

        int t = kruskal();
        if (t == INF) {
            writer.write("impossible\n");
        }
        else {
            writer.write(String.valueOf(t).concat("\n"));
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static int kruskal() {
        int res = 0;
        // 记录加进集合中边的条数
        int edgeCnt = 0;
        // 将边按权重排序
        Arrays.sort(edges, (e1, e2) -> e1.w - e2.w);
        for (Edge edge : edges) {
            int a = edge.a, b = edge.b, w = edge.w;
            a = find(a); b = find(b);
            // 新加的边不会构成回路
            if (a != b) {
                res += w;
                // 把 a 和 b 连通
                p[b] = a;
                edgeCnt++;
            }
        }

        return (edgeCnt == n - 1) ? res : INF;
    }

    private static int find(int x) {
        // 只有根节点的父亲等于自己
        if (x != p[x]) {
            // 路径压缩
            p[x] = find(p[x]);
        }
        return p[x];
    }

    static class Edge {
        int a;
        int b;
        int w;

        public Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
}
