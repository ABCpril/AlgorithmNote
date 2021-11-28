package AcWing;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @FileName: DijkstraWithHeap.java
 * @Description: Dijkstra求最短路(堆优化)
 * @Author: ABCpril
 * @Date: 2021/11/28
 */
public class DijkstraWithHeap {
    static int INF = 0x3f3f3f3f;
    // 稀疏图用邻接表存储
    static int[] h, e, ne, w;
    // 记录该点的最短距离是否已经确定
    static boolean[] state;
    // 记录每一个点到第 1 个点的距离
    static int[] dist;
    static int n, idx;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        h = new int[n + 1];
        e = new int[m + 1];
        ne = new int[m + 1];
        w = new int[m + 1];
        Arrays.fill(h, -1);
        dist = new int[n + 1];
        state = new boolean[n + 1];

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            int z = Integer.parseInt(edge[2]);
            add(x, y, z);
        }

        writer.write(String.valueOf(dijkstra()).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static int dijkstra() {
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<HeapNode> pq = new PriorityQueue<>((node1, node2) -> node1.distance - node2.distance);
        pq.offer(new HeapNode(1, 0));

        while (!pq.isEmpty()) {
            // 从小根堆顶上取出的，相当于贪心求的距离 1 号点最近的点
            HeapNode curt = pq.poll();
            if (state[curt.num]) {
                // 点的dist反复更新，会多次进队，continue来忽略最短距离已确定的点
                continue;
            }
            // 松弛相邻边
            for (int i = h[curt.num]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[curt.num] + w[i]) {
                    dist[j] = dist[curt.num] + w[i];
                    pq.offer(new HeapNode(j, dist[j]));
                }
            }
            state[curt.num] = true;
        }

        if (dist[n] == INF) return -1;
        return dist[n];
    }

    private static void add(int x, int y, int z) {
        e[idx] = y; ne[idx] = h[x]; h[x] = idx; w[idx] = z; idx++;
    }
}

class HeapNode {
    int num;
    int distance;

    public HeapNode(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }
}
