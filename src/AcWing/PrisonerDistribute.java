package AcWing;

import java.io.*;
import java.util.*;

/**
 * @FileName: PrisonerDistribute.java
 * @Description: 关押罪犯
 * @Author: ABCpril
 * @Date: 2021/11/27
 */
public class PrisonerDistribute {
    static int[] h, e, ne, w;
    // 0 未涂色，1 黑色，2 白色
    static int[] color;
    static int idx;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        // 罪犯的数目
        int n = Integer.parseInt(line[0]);
        // 存在仇恨的罪犯对数
        int m = Integer.parseInt(line[1]);

        h = new int[n + 1];
        e = new int[2 * m];
        ne = new int[2 * m];
        w = new int[2 * m];
        Arrays.fill(h, -1);
        color = new int[n + 1];
        List<Integer> anger = new ArrayList<>();
        anger.add(0);

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int a = Integer.parseInt(edge[0]);
            int b = Integer.parseInt(edge[1]);
            int c = Integer.parseInt(edge[2]);
            add(a, b, c);
            add(b, a, c);
            anger.add(c);
        }

        anger.sort((v1, v2) -> v1 - v2);
        // 二分法
        int size = anger.size();
        int l = 0, r = size;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(anger.get(mid))) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        writer.write(String.valueOf(anger.get(l)).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static boolean check(int limit) {
        // 是否权重高于limit的边，可以将图二分掉
        // color = 0代表未涂色
        Arrays.fill(color, 0);
        for (int i = 1; i < color.length; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, limit)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean dfs(int curt, int c, int limit) {
        color[curt] = c;
        for (int i = h[curt]; i != -1; i = ne[i]) {
            // 因为是判断怒气值高于limit的两个犯人（这条边）能否被分到两个不同监狱（左右半部）
            // 只需要判断边集中，权重大于limit的边，所在的连通块能否构成二分图，就知道两个犯人能否被分到两个监狱
            if (w[i] <= limit) {
                continue;
            }
            int j = e[i];
            // 如果相邻的j涂色与curt相同，二分失败
            if (color[j] == c) {
                return false;
            }
            else if (color[j] == 3 - c) {
                continue;
            }
            // j未涂色，从j开始下一层dfs
            else if (color[j] == 0) {
                boolean newCenter = dfs(j, 3 - c, limit);
                if (newCenter == false) {
                    return false;
                }
                // newCenter=true，还要继续进行其他相邻点的判断
            }
        }
        return true;
    }

    private static void add(int a, int b, int c) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx; w[idx] = c; idx++;
    }
}
