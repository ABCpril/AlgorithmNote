package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: Permutation.java
 * @Description: 全排列
 * @Author: ABCpril
 * @Date: 2021/12/03
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        dfs(nums, res, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    // 1.递归的含义：以permutation开头的排列，如果长度达到 n，就加入res
    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> permutation, boolean[] visited) {
        // 3.递归的出口：排列长度达到 n
        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }

        // 2.递归的拆解
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            permutation.add(nums[i]);
            visited[i] = true;
            dfs(nums, res, permutation, visited);
            // 回溯
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
