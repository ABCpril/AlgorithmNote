package AcWing;

import java.io.*;

/**
 * @FileName: DifferenceMatrix.java
 * @Description: 差分矩阵
 * @Author: ABCpril
 * @Date: 2021/12/05
 */
public class DifferenceMatrix {
    static int n, m;
    // a为原矩阵，b为差分矩阵，b求前缀和可得 a
    static int[][] a, b;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        m = Integer.parseInt(line[1]);
        int q = Integer.parseInt(line[2]);

        a = new int[n + 1][m + 1];
        b = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            String[] data = reader.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                a[i][j] = Integer.parseInt(data[j - 1]);
                // 初始化差分矩阵 b，可以看成 a 为零矩阵，b也为零矩阵，b为 a的差分矩阵成立
                // a矩阵一个一个位置加上a[i][j]，b矩阵相应变化后仍为 a的差分矩阵
                insert(i, j, i, j, a[i][j]);
            }
        }

        while (q-- != 0) {
            String[] query = reader.readLine().split(" ");
            int x1 = Integer.parseInt(query[0]);
            int y1 = Integer.parseInt(query[1]);
            int x2 = Integer.parseInt(query[2]);
            int y2 = Integer.parseInt(query[3]);
            int c = Integer.parseInt(query[4]);
            insert(x1, y1, x2, y2, c);
        }

        // 差分矩阵 b求前缀和得变换后矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 求前缀和公式
                b[i][j] = b[i][j - 1] + b[i - 1][j] - b[i - 1][j - 1] + b[i][j];
                writer.write(String.valueOf(b[i][j]).concat(" "));
            }
            writer.write("\n");
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static void insert(int x1, int y1, int x2, int y2, int c) {
        // [x1, 无穷), [y1, 无穷)矩形区域全部加上 c
        b[x1][y1] += c;
        b[x2 + 1][y1] -= c;
        b[x1][y2 + 1] -= c;
        // [x2 + 1, 无穷), [y2 + 1, 无穷)矩形区域多减了一次 c
        b[x2 + 1][y2 + 1] += c;
    }
}
