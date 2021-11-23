package AcWing;

import java.io.*;

/**
 * @FileName: NumOfPointsInUnion.java
 * @Description: 连通块中点的数量
 * @Author: ABCpril
 * @Date: 2021/11/23
 */
public class NumOfPointsInUnion {
    static int N = 100010;
    static int[] p = new int[N];
    // 存储点所在连通块中点的数量
    static int[] cnt = new int[N];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int find(int x) {
        // 根节点的父亲才是它自己
        if (x != p[x]) {
            // 不是根节点的点都进行路径压缩，递归进行，自己、父亲、父亲的父亲都会连到根节点
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        for (int i = 1; i <= n; i++) {
            p[i] = i;
            // 初始都只有自己，集合合并时才会改变
            cnt[i] = 1;
        }
        int m = Integer.parseInt(line[1]);

        while (m-- != 0) {
            String[] operation = reader.readLine().split(" ");
            String cmd = operation[0];
            if ("C".equals(cmd)) {
                int a = Integer.parseInt(operation[1]);
                int b = Integer.parseInt(operation[2]);
                if (find(a) != find(b)) {
                    cnt[find(b)] += cnt[find(a)];
                    // 把两个点连接，就是让a的祖宗认b的祖宗作父亲
                    p[find(a)] = find(b);
                }
            }
            else if ("Q1".equals(cmd)) {
                int a = Integer.parseInt(operation[1]);
                int b = Integer.parseInt(operation[2]);
                if (find(a) == find(b)) {
                    writer.write("Yes\n");
                }
                else {
                    writer.write("No\n");
                }
            }
            else if ("Q2".equals(cmd)) {
                int a = Integer.parseInt(operation[1]);
                writer.write(String.valueOf(cnt[find(a)]).concat("\n"));
            }
        }

        writer.flush(); reader.close(); writer.close();
    }
}
