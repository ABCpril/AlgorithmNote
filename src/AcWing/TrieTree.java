package AcWing;


import java.io.*;

/**
 * @FileName: TrieTree.java
 * @Description: TrieTree字符串统计
 * @Author: ABCpril
 * @Date: 2021/11/20
 */
public class TrieTree {
    static int N = 100010;
    // 第一维是输入数据也就是多次输入字符串总长度，第二维代表26个小写英文字母
    static int[][] son = new int[N][26];
    // 当前使用的下标，下标0：既是根节点也是空节点
    static int idx = 1;
    // 以当前这个点结尾的单词有多少个
    static int[] cnt = new int[N];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String operCount = reader.readLine();
        int operCnt = Integer.parseInt(operCount);
        while (operCnt-- != 0) {
            String[] oper = reader.readLine().split(" ");
            if ("I".equals(oper[0])) {
                insert(oper[1]);
            }
            else if ("Q".equals(oper[0])) {
                writer.write(String.valueOf(query(oper[1])).concat("\n"));
            }
        }

        writer.flush(); reader.close(); writer.close();
    }

    private static void insert(String s) {
        char[] chars = s.toCharArray();
        // 从根节点 0 出发
        int p = 0;
        for (int i = 0; i < chars.length; i++) {
            // 当前字母的子节点编号
            int x = chars[i] - 'a';
            // 不存在对应字母，需要创建
            if (son[p][x] == 0) {
                son[p][x] = idx++;
            }
            // 走到子节点
            p = son[p][x];
        }
        // 以这个点结尾的单词数量+1
        cnt[p]++;
    }

    // 查找字符串出现的次数
    private static int query(String s) {
        char[] chars = s.toCharArray();
        int p = 0;
        for (int i = 0; i < chars.length; i++) {
            int x = chars[i] - 'a';
            // 如果子节点不存在，说明集合中不存在这个单词
            if (son[p][x] == 0) {
                return 0;
            }
            p = son[p][x];
        }
        // 以最后字母的idx找计数cnt
        return cnt[p];
    }
}
