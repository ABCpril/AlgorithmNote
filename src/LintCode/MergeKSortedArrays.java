package LintCode;

import java.util.PriorityQueue;

/**
 * @FileName: MergeKSortedArrays.java
 * @Description: 合并k个排序数组
 * @Author: ABCpril
 * @Date: 2022/02/12
 */
public class MergeKSortedArrays {
    /**
     * 方法1：分治法
     * 二分成logK层，每层都是N个数，时间复杂度为Nlog(K)
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return null;
        return merge(arrays, 0, arrays.length - 1);
    }

    private int[] merge(int[][] arrays, int left, int right) {
        // 递归的出口：规模小到1个数组
        if (left == right) return arrays[left];
        // 第一步：分成子问题
        int mid = (left + right) >> 1;
        // 第二步：递归处理子问题
        // 由于mid是向下取整，mid不会取到right，所以不会造成无限划分
        int[] leftArray = merge(arrays, left, mid);
        int[] rightArray = merge(arrays, mid + 1, right);
        // 第三步：合并子问题
        return mergeTwoArrays(leftArray, rightArray);
    }

    private int[] mergeTwoArrays(int[] arr1, int[] arr2) {
        int[] temp = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                temp[k++] = arr1[i++];
            }
            else {
                temp[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) {
            temp[k++] = arr1[i++];
        }
        while (j < arr2.length) {
            temp[k++] = arr2[j++];
        }
        return temp;
    }

    /**
     * 方法2：小根堆
     */
    public int[] mergekSortedArrays2(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return null;
        PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> node1.val - node2.val);
        // 总共多少个数
        int totalNum = 0;
        // 将每个数组的第一个值存入优先队列
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i] == null || arrays[i].length == 0) {
                continue;
            }
            pq.offer(new Node(0, i, arrays[i][0]));
            totalNum += arrays[i].length;
        }
        int[] res = new int[totalNum];
        int k = 0;
        while (!pq.isEmpty()) {
            Node curt = pq.poll();
            res[k++] = curt.val;
            // 如果该值不是其所在数组的最后一个
            if (curt.index < arrays[curt.arrIndex].length - 1) {
                pq.offer(new Node(
                        curt.index + 1, curt.arrIndex, arrays[curt.arrIndex][curt.index + 1]));
            }
        }
        return res;
    }
}

class Node {
    // 是数组中第几个数，下标
    int index;
    // 有序数组在k中的序号
    int arrIndex;
    // 值
    int val;

    public Node(int index, int arrIndex, int val) {
        this.index = index;
        this.arrIndex = arrIndex;
        this.val = val;
    }
}
