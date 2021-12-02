package AcWing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName: Distance1toN.java
 * @Description: 图中点的层次
 * @Author: ABCpril
 * @Date: 2021/12/02
 */
public class Distance1toN {
    static int N = 100010, M = 100010, INF = 0x3f3f3f3f, n;
    // 稀疏图用邻接表存储
    static int[] h = new int[N], e = new int[M], ne = new int[M];
    // dist还充当visited[]数组的作用
    static int[] dist;
    static int idx;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        Arrays.fill(h, -1);
        dist = new int[n + 1];

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            add(a, b);
        }

        bfs();

        writer.write(dist[n] == INF ? "-1\n" : String.valueOf(dist[n]).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static void bfs() {
        Arrays.fill(dist, INF);
        dist[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // for上一层的节点，拓展下一层的节点
            for (int k = 0; k < size; k++) {
                int curt = queue.poll();
                for (int i = h[curt]; i != -1; i = ne[i]) {
                    // curt点连接的点，dist更新
                    int j = e[i];
                    // 由于是从 1 号点出发，每条边权重又都是1
                    // 如果dist不为INF，说明dist已被更新为离 1 号点更近的边权和
                    if (dist[j] == INF) {
                        dist[j] = dist[curt] + 1;
                        queue.offer(j);
                    }
                }
            }
        }
    }

    private static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; idx++;
    }
}
