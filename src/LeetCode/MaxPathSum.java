package LeetCode;

/**
 * @FileName: MaxPathSum.java
 * @Description: 二叉树中的最大路径和
 * @Author: ABCpril
 * @Date: 2022/02/10
 */
public class MaxPathSum {
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int maxSum = Integer.MIN_VALUE;
    // 1.递归的含义：返回当前节点能为父节点提供的贡献值
    private int dfs(TreeNode root) {
        // 3.递归的出口：如果当前节点为null，对父节点贡献为0
        if (root == null) return 0;
        // 2.递归的拆解
        int leftRes = dfs(root.left);
        int rightRes = dfs(root.right);
        // 左右子树结果对最终结果的作用
        //    a
        //   / \
        //  b   c
        // b + a + c。
        // b + a + a 的父结点。
        // a + c + a 的父结点。
        maxSum = Math.max(maxSum, root.val + leftRes + rightRes);
        // 如果走root上去的路线，root节点能为父节点提供的最大贡献
        int outputMaxSum = Math.max(root.val + leftRes, root.val + rightRes);
        // 如果贡献 < 0，直接返回 0
        return outputMaxSum < 0 ? 0 : outputMaxSum;
    }
}
