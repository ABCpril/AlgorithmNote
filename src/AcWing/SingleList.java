package AcWing;

import java.io.*;

/**
 * @FileName: SingleList.java
 * @Description: 单链表
 * @Author: ABCpril
 * @Date: 2021/12/05
 */
public class SingleList {
    static int[] e, ne;
    static int m, idx, head;

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        m = Integer.parseInt(reader.readLine());
        init();
        while (m-- != 0) {
            String[] operation = reader.readLine().split(" ");
            String cmd = operation[0];
            if ("H".equals(cmd)) {
                int x = Integer.parseInt(operation[1]);
                e[idx] = x; ne[idx] = head; head = idx; idx++;
            }
            else if ("D".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                // k = 0 表示删除头结点，意为让head指向第二个数
                if (k == 0) {
                    head = ne[head];
                }
                else {
                    remove(k);
                }
            }
            else if ("I".equals(cmd)) {
                int k = Integer.parseInt(operation[1]);
                int x = Integer.parseInt(operation[2]);
                insert(k, x);
            }
        }

        for (int i = head; i != -1; i = ne[i]) {
            writer.write(String.valueOf(e[i]).concat(" "));
        }
        writer.flush(); reader.close(); writer.close();
    }

    // 删除idx = k后面的数
    private static void remove(int k) {
        ne[k] = ne[ne[k]];
    }

    // 在idx = k 的数后面插入 x
    private static void insert(int k, int x) {
        e[idx] = x; ne[idx] = ne[k]; ne[k] = idx; idx++;
    }

    private static void init() {
        head = -1;
        e = new int[m];
        ne = new int[m];
        // 第 k 个插入，idx = k
        idx = 1;
    }
}
