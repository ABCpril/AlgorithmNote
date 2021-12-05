package LeetCode;

/**
 * @FileName: MaximumXOR.java
 * @Description: 数组中两个数的最大异或值
 * @Author: ABCpril
 * @Date: 2021/12/05
 */
public class MaximumXOR {
    // nums[i] <= 2 ^ 31 - 1，用0-30位bit可以存2 ^ 31 - 1
    final int HIGH_BIT = 30;
    int idx = 1;

    public int findMaximumXOR(int[] nums) {
        // 第一维数据总长度，第二维 0 1 二进制
        int[][] son = new int[30 * 20010][2];
        for (int x : nums) {
            insert(x, son);
        }
        int res = 0;
        for (int x : nums) {
            res = Math.max(res, query(x, son));
        }
        return res;
    }

    private void insert(int x, int[][] son) {
        int p = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            // x 的第 k 位是 0 还是 1
            int bit = x >> k & 1;
            if (son[p][bit] == 0) {
                son[p][bit] = idx++;
            }
            p = son[p][bit];
        }
    }

    private int query(int x, int[][] son) {
        int p = 0, res = 0;
        for (int k = HIGH_BIT; k >= 0; k--) {
            // x 的第 k 位是 0 还是 1
            int bit = x >> k & 1;
            // 1-bit存在，异或和增加
            if (son[p][1 - bit] != 0) {
                p = son[p][1 - bit];
                res += (1 << k);
            }
            else {
                p = son[p][bit];
            }
        }

        return res;
    }
}
