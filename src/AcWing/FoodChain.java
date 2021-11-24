package AcWing;

import java.io.*;

/**
 * @FileName: FoodChain.java
 * @Description: 食物链
 * @Author: ABCpril
 * @Date: 2021/11/24
 */
public class FoodChain {
    static int N = 50010, n;
    static int[] p = new int[3 * N + 1];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        // n 个动物
        n = Integer.parseInt(line[0]);
        for (int i = 1; i < 3 * N + 1; i++) {
            p[i] = i;
        }
        // K 句话
        int K = Integer.parseInt(line[1]);
        int falseCnt = 0;

        while (K-- != 0) {
            String[] assumption = reader.readLine().split(" ");
            int DKind = Integer.parseInt(assumption[0]);
            int X = Integer.parseInt(assumption[1]);
            int Y = Integer.parseInt(assumption[2]);
            if (!judge(DKind, X, Y)) {
                falseCnt++;
            }
        }

        writer.write(String.valueOf(falseCnt).concat("\n"));
        writer.flush(); reader.close(); writer.close();
    }

    private static boolean judge(int DKind, int X, int Y) {
        if (X > n || Y > n) return false;
        if (DKind == 1) {
            // X和Y是同类，X的捕食域和天敌域不该有Y
            if (find(X + N) == find(Y) || find(X + N + N) == find(Y)) {
                return false;
            }
            // X的同类域和Y的同类域连通
            p[find(Y)] = find(X);
            // X的捕食域和Y的捕食域连通
            p[find(Y + N)] = find(X + N);
            // X的天敌域和Y的天敌域连通
            p[find(Y + N + N)] = find(X + N + N);
        }
        else if (DKind == 2) {
            // X吃Y，X的同类域和天敌域不该有Y
            if (X == Y) return false;
            if (find(X) == find(Y) || find(X + 2 * N) == find(Y)) {
                return false;
            }
            // X的捕食域和Y的同类域连通
            p[find(Y)] = find(X + N);
            // X的天敌域和Y的捕食域连通（Y吃的是X的天敌）
            p[find(Y + N)] = find(X + N + N);
            // X的同类域和Y的天敌域连通
            p[find(Y + N + N)] = find(X);
        }
        return true;
    }

    private static int find(int x) {
        if (x != p[x]) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
