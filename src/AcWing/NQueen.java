package AcWing;

import java.io.*;

/**
 * @FileName: NQueen.java
 * @Description: n-皇后问题
 * @Author: ABCpril
 * @Date: 2021/11/21
 */
public class NQueen {
    static int N = 10, n;
    static char[][] g = new char[N][N];
    // 对角线和反对角线都是 2n-1 条
    static boolean[] col = new boolean[N], dg = new boolean[2 * N], udg = new boolean[2 * N];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = '.';
            }
        }

        dfs(0);

        writer.flush(); reader.close(); writer.close();
    }

    // y = x + b  b = y - x
    // y = -x + b b = y + x
    // y看作行，x看作列
    private static void dfs(int y) throws IOException {
        // 递归出口：行数过n行
        if (y == n) {
            for (int i = 0; i < n; i++) {
                StringBuilder row = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    row.append(g[i][j]);
                }
                writer.write(row.toString().concat("\n"));
            }
            writer.write("\n");
            return;
        }
        // 在当前行一列列试着放
        for (int x = 0; x < n; x++) {
            if (!col[x] && !dg[y - x + n] && !udg[y + x]) {
                col[x] = true; dg[y - x + n] = true; udg[y + x] = true;
                g[y][x] = 'Q';
                dfs(y + 1);
                g[y][x] = '.';
                col[x] = false; dg[y - x + n] = false; udg[y + x] = false;
            }
        }
    }
}
