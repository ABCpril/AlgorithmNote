package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName: LevelOrder.java
 * @Description: 二叉树的层序遍历
 * @Author: ABCpril
 * @Date: 2022/02/09
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 数组模拟队列
        TreeNode[] q = new TreeNode[2010];
        // 队尾吸收元素，队首排出元素
        int hh = 0, tt = -1;
        //1.创建一个队列，把起始节点都放到里面去(第 1 层节点)
        q[++tt] = root;
        //2.while队列不空，处理队列中的结点，并拓展出新的节点
        while (hh <= tt) {
            int size = tt - hh + 1;
            List<Integer> row = new ArrayList<>();
            //3.for上一层的节点，拓展下一层的节点
            for (int i = 0; i < size; i++) {
                TreeNode curt = q[hh];
                hh++;
                row.add(curt.val);
                if (curt.left != null) {
                    q[++tt] = curt.left;
                }
                if (curt.right != null) {
                    q[++tt] = curt.right;
                }
            }
            res.add(row);
        }

        return res;
    }
}
