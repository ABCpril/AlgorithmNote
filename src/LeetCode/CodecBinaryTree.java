package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @FileName: CodecBinaryTree.java
 * @Description: 二叉树的序列化与反序列化
 * @Author: ABCpril
 * @Date: 2021/12/12
 */
public class CodecBinaryTree {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder levelTraverseRes = new StringBuilder();
        // 层序遍历
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 1; k <= size; k++) {
                TreeNode curt = q.poll();
                if (curt == null) {
                    levelTraverseRes.append("null,");
                }
                else {
                    levelTraverseRes.append(curt.val).append(",");
                    q.offer(curt.left);
                    q.offer(curt.right);
                }
            }
        }
        return levelTraverseRes.substring(0, levelTraverseRes.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        TreeNode root = (nodes[0].equals("null")) ? null : new TreeNode(Integer.parseInt(nodes[0]));
        if (root == null) return root;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 0;
        while (!q.isEmpty()) {
            TreeNode curt = q.poll();
            // 左右子树下标是i * 2 + 1和i * 2 + 2
            if (!nodes[i * 2 + 1].equals("null")) {
                curt.left = new TreeNode(Integer.parseInt(nodes[i * 2 + 1]));
                q.offer(curt.left);
            }
            if (!nodes[i * 2 + 2].equals("null")) {
                curt.right = new TreeNode(Integer.parseInt(nodes[i * 2 + 2]));
                q.offer(curt.right);
            }
            i++;
        }
        return root;
    }
}
