package LeetCode;

/**
 * @FileName: IsValidBST.java
 * @Description: 验证二叉查找树
 * @Author: ABCpril
 * @Date: 2021/12/07
 */
public class IsValidBST {
    // ResultType法
    public boolean isValidBST1(TreeNode root) {
        return helper(root).isBST;
    }

    // 1.递归的定义：返回以root为根的子树的BST情况：是否是BST、左子树最大值、右子树最小值
    private ResultType helper(TreeNode root) {
        // 3.递归的出口
        if (root == null) {
            return new ResultType(true);
        }
        // 2.递归的拆解
        ResultType leftRes = helper(root.left);
        ResultType rightRes = helper(root.right);
        // 左右子树结果对最终结果的作用
        // 左子树或右子树不是BST
        if (!leftRes.isBST || !rightRes.isBST) {
            return new ResultType(false);
        }
        // 左子树不为空，BST要求root.val 严格> 左子树最大值
        if (leftRes.maxValue != null && root.val <= leftRes.maxValue) {
            return new ResultType(false);
        }
        // 右子树不为空，BST要求root.val 严格< 右子树最小值
        if (rightRes.minValue != null && root.val >= rightRes.minValue) {
            return new ResultType(false);
        }
        return new ResultType(true, leftRes.minValue == null ? root.val : leftRes.minValue,
                rightRes.maxValue == null ? root.val : rightRes.maxValue);
    }

    class ResultType {
        public boolean isBST;
        public Integer minValue, maxValue;

        public ResultType(boolean isBST, int min, int max) {
            this.isBST = isBST;
            minValue = min; maxValue = max;
        }

        public ResultType(boolean isBST) {
            this.isBST = isBST;
            minValue = null; maxValue = null;
        }
    }

    // 中序遍历递归法
    public boolean isValidBST2(TreeNode root) {
        inOrderTraverse(root);
        return isOrdered;
    }

    boolean isOrdered = true;
    // -2^31 <= Node.val <= 2^31-1，正好是Integer的MIN_VALUE和MAX_VALUE
    Integer pre = null;
    // 1.递归的含义：以root为根的子树，左中右严格递增判别
    private void inOrderTraverse(TreeNode root) {
        // 3.递归的出口
        if (root == null) return;
        // 2.递归的拆解
        inOrderTraverse(root.left);
        if (pre != null && root.val <= pre) {
            isOrdered = false;
            return;
        }
        pre = root.val;
        inOrderTraverse(root.right);
    }

    // 中序遍历非递归法
    // -2^31 <= Node.val <= 2^31-1，正好是Integer的MIN_VALUE和MAX_VALUE
    Integer preVal = null;
    public boolean isValidBST3(TreeNode root) {
        TreeNode[] stack = new TreeNode[10010];
        int tt = 0;

        TreeNode curt = root;
        while (curt != null || tt > 0) {
            while (curt != null) {
                stack[++tt] = curt;
                curt = curt.left;
            }
            curt = stack[tt];
            tt--;
            // res.add(curt.val);
            if (preVal != null && curt.val <= preVal) {
                return false;
            }
            preVal = curt.val;
            curt = curt.right;
        }

        return true;
    }
}

class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}
