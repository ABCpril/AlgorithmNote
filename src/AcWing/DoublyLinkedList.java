package AcWing;

import java.io.*;

/**
 * @FileName: DoublyLinkedList.java
 * @Description: 双链表
 * @Author: ABCpril
 * @Date: 2021/11/29
 */
public class DoublyLinkedList {
    static int m, idx = 2;
    static int[] e, l, r;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        m = Integer.parseInt(reader.readLine());
        init();
        while (m-- != 0) {
            String[] operation = reader.readLine().split(" ");
            String cmd = operation[0];
            if ("L".equals(cmd)) {
                int x = Integer.parseInt(operation[1]);
                insertR(0, x);
            }
            else if ("R".equals(cmd)) {
                int x = Integer.parseInt(operation[1]);
                insertL(1, x);
            }
            else if ("D".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                remove(k + 1);
            }
            else if ("IL".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                int x = Integer.parseInt(operation[2]);
                insertL(k + 1, x);
            }
            else if ("IR".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                int x = Integer.parseInt(operation[2]);
                insertR(k + 1, x);
            }
        }

        for (int i = r[0]; i != 1; i = r[i]) {
            writer.write(String.valueOf(e[i]).concat(" "));
        }
        writer.flush(); reader.close(); writer.close();
    }

    // 在idx = k的数右侧插入 x
    private static void insertR(int k, int x) {
        e[idx] = x; l[idx] = k; r[idx] = r[k];
        l[r[k]] = idx; r[k] = idx; idx++;
    }

    // 在idx = k的数左侧插入 x
    private static void insertL(int k, int x) {
        insertR(l[k], x);
    }

    // 删除idx = k的数
    private static void remove(int k) {
        r[l[k]] = r[k];
        l[r[k]] = l[k];
    }

    private static void init() {
        e = new int[m + 2];
        l = new int[m + 2];
        r = new int[m + 2];
        // idx = 0、idx = 1保留为 head和 tail
        r[0] = 1;
        l[1] = 0;
        idx = 2;
    }
}
