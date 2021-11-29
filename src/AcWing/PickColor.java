package AcWing;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName: PickColor.java
 * @Description: 染色法判定二分图
 * @Author: ABCpril
 * @Date: 2021/11/29
 */
public class PickColor {
    static int n, idx;
    static int[] h, e, ne;
    // 0 代表未染色，1 代表黑色，2 代表白色
    static int[] color;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        h = new int[n + 1];
        e = new int[2 * m];
        ne = new int[2 * m];
        Arrays.fill(h, -1);
        color = new int[n + 1];

        while (m-- != 0) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            add(a, b); add(b, a);
        }

        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            // 未染色则以该点为起点，进行可达点(连通块)染色
            if (color[i] == 0) {
//                if (!dfs(i, 1)) {
//                    flag = false;
//                    break;
//                }

                if (!bfs(i, 1)) {
                    flag = false;
                    break;
                }
            }
        }

        writer.write(flag ? "Yes\n" : "No\n");
        writer.flush(); reader.close(); writer.close();
    }

    // 通过深度遍历的方式，进行交叉染色
    private static boolean dfs(int curt, int c) {
        color[curt] = c;
        // 相邻点染色若与curt相同，说明图无法二分
        for (int i = h[curt]; i != -1; i = ne[i]) {
            int j = e[i];
            if (color[j] == c) {
                return false;
            }
            else if (color[j] == 3 - c) {
                continue;
            }
            else if (color[j] == 0) {
                // 未染色，用3-c色尝试从j开始进行相邻点染色
                boolean newCenter = dfs(j, 3 - c);
                if (!newCenter) return false;
            }
        }
        return true;
    }

    // 采用广度遍历的方式，进行不同层的染色
    private static boolean bfs(int source, int c) {
        Queue<Integer> queue = new LinkedList<>();
        color[source] = c;
        queue.offer(source);

        // while队列不空，处理队列中的节点，并拓展出新的节点
        while (!queue.isEmpty()) {
            int size = queue.size();
            // for上一层的节点，拓展下一层的节点
            for (int k = 1; k <= size; k++) {
                int curt = queue.poll();
                int tempColor = color[curt];

                for (int i = h[curt]; i != -1; i = ne[i]) {
                    int j = e[i];
                    if (color[j] == tempColor) {
                        return false;
                    }
                    else if (color[j] == 3 - c) {
                        continue;
                    }
                    // j点未染色
                    else if (color[j] == 0) {
                        // 用3-tempColor去染，待下一层poll出j点，看对j点的相邻点染色是否会矛盾
                        color[j] = 3 - tempColor;
                        queue.offer(j);
                    }
                }
            }
        }

        return true;
    }

    private static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; idx++;
    }
}
