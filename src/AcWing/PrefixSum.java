package AcWing;

import java.io.*;

/**
 * @FileName: PrefixSum.java
 * @Description: 前缀和
 * @Author: ABCpril
 * @Date: 2021/11/24
 */
public class PrefixSum {
    static int[] a, s;
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String[] line = reader.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        a = new int[n + 1];
        s = new int[n + 1];
        int m = Integer.parseInt(line[1]);

        String[] data = reader.readLine().split(" ");
        for (int i = 1; i <= data.length; i++) {
            a[i] = Integer.parseInt(data[i - 1]);
            s[i] = s[i - 1] + a[i];
        }

        while (m-- != 0) {
            String[] query = reader.readLine().split(" ");
            int l = Integer.parseInt(query[0]);
            int r = Integer.parseInt(query[1]);
            writer.write(String.valueOf(s[r] - s[l - 1]).concat("\n"));
        }

        writer.flush(); reader.close(); writer.close();
    }
}
