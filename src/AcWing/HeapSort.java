package AcWing;

import java.io.*;

/**
 * @FileName: HeapSort.java
 * @Description: 堆排序
 * @Author: ABCpril
 * @Date: 2021/11/25
 */
public class HeapSort {
    static int[] h;
    static int size;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        size = n + 1;
        h = new int[n + 1];

        int m = Integer.parseInt(line[1]);
        String[] data = reader.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            h[i] = Integer.parseInt(data[i - 1]);
        }
        // 从 堆size / 2(最后一个叶节点的父节点)开始down操作，小根堆即成型
        for (int i = size >> 1; i >= 1; i--) {
            down(i);
        }
        // 输出前 m 小的数
        for (int i = 1; i <= m; i++) {
            writer.write(String.valueOf(min()).concat(" "));
            remove(1);
        }

        writer.write("\n");
        writer.flush(); reader.close(); writer.close();
    }

    // down
    private static void down(int u) {
        int t = u;
        if (u * 2 < size && h[u * 2] < h[t]) {
            t = u * 2;
        }
        if (u * 2 + 1 < size && h[u * 2 + 1] < h[t]) {
            t = u * 2 + 1;
        }

        if (t != u) {
            int temp = h[u];
            h[u] = h[t];
            h[t] = temp;

            down(t);
        }
    }

    // remove
    private static void remove(int u) {
        h[u] = h[size - 1];
        size--;
        down(u);
    }

    // min
    private static int min() {
        return h[1];
    }
}
