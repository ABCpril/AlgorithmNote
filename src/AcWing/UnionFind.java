package AcWing;

import java.io.*;

/**
 * @FileName: UnionFind.java
 * @Description: 合并集合
 * @Author: ABCpril
 * @Date: 2021/11/24
 */
public class UnionFind {
    static int N = 100010;
    static int[] p = new int[N];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        for (int i = 1; i <= n; i++) {
            p[i] = i;
        }
        int m = Integer.parseInt(line[1]);

        while (m-- != 0) {
            String[] operation = reader.readLine().split(" ");
            String cmd = operation[0];
            int a = Integer.parseInt(operation[1]);
            int b = Integer.parseInt(operation[2]);
            if ("M".equals(cmd)) {
                a = find(a);
                b = find(b);
                if (a != b) {
                    p[a] = b;
                }
            }
            else if ("Q".equals(cmd)) {
                if (find(a) == find(b)) {
                    writer.write("Yes\n");
                }
                else {
                    writer.write("No\n");
                }
            }
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static int find(int x) {
        // 只有根节点的父亲是它自己
        if (x != p[x]) {
            // 路径压缩
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
