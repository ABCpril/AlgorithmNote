package AcWing;

import java.io.*;

/**
 * @FileName: MockHeap.java
 * @Description: 模拟堆
 * @Author: ABCpril
 * @Date: 2021/11/23
 */
public class MockHeap {
    static int N = 100010;
    static int[] heap = new int[N];
    // ph: 第k个插入的点在堆中的下标是什么
    // hp: 堆里某个点是第几个插入的点
    static int[] ph = new int[N], hp = new int[N];
    static int idx = 1, size;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(reader.readLine());
        while (n-- != 0) {
            String[] operation = reader.readLine().split(" ");
            String cmd = operation[0];
            if ("I".equals(cmd)) {
                int x = Integer.parseInt(operation[1]);
                heap[++size] = x;
                hp[size] = idx;
                ph[idx++] = size;
                up(size);
            }
            else if ("D".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                // 删除第k个插入的数
                int heapOrder = ph[k];
                heapSwap(heapOrder, size);
                size--;
                up(heapOrder);
                down(heapOrder);
            }
            else if ("PM".equals(cmd)) {
                writer.write(String.valueOf(heap[1]).concat("\n"));
            }
            else if ("DM".equals(cmd)) {
                // 删除最小值
                heapSwap(1, size);
                size--;
                down(1);
            }
            else if ("C".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                int x = Integer.parseInt(operation[2]);
                // 修改第k个插入的数，将其变为x
                int heapOrder = ph[k];
                heap[heapOrder] = x;
                up(heapOrder);
                down(heapOrder);
            }
        }

        writer.flush(); reader.close(); writer.close();
    }

    // up
    private static void up(int u) {
        while (u / 2 >= 1 && heap[u / 2] > heap[u]) {
            heapSwap(u, u / 2);
            u /= 2;
        }
    }

    // down
    private static void down(int u) {
        int t = u;
        if (u * 2 <= size && heap[u * 2] < heap[t]) {
            t = u * 2;
        }
        if (u * 2 + 1 <= size && heap[u * 2 + 1] < heap[t]) {
            t = u * 2 + 1;
        }
        if (t != u) {
            heapSwap(u, t);
            down(t);
        }
    }

    // u, v是heap数组里的下标
    private static void heapSwap(int u, int v) {
        int insertUOrder = hp[u], insertVOrder = hp[v];
        swap(heap, u, v);
        swap(hp, u, v);
        swap(ph, insertUOrder, insertVOrder);
    }

    private static void swap(int[] arr, int u, int v) {
        int temp = arr[u];
        arr[u] = arr[v];
        arr[v] = temp;
    }
}
