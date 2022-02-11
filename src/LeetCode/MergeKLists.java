package LeetCode;

import java.util.PriorityQueue;

/**
 * @FileName: MergeKLists.java
 * @Description: 合并K个升序链表
 * @Author: ABCpril
 * @Date: 2022/02/11
 */
public class MergeKLists {
    /**
     * 方法1：分治法
     * 二分成logK层，每层都是N个节点，时间复杂度为Nlog(K)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    // 类似归并排序
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 递归的出口：规模小到1条链表
        if (left == right) return lists[left];
        // 第一步：分成子问题
        int mid = (left + right) >> 1;
        // 第二步：递归处理子问题
        // 由于mid是向下取整，mid不会取到right，所以不会造成无限划分
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        // 第三步：合并子问题
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                lastNode.next = l1;
                l1 = l1.next;
            }
            else {
                lastNode.next = l2;
                l2 = l2.next;
            }
            lastNode = lastNode.next;
        }
        if (l1 != null) {
            lastNode.next = l1;
        }
        if (l2 != null) {
            lastNode.next = l2;
        }
        return dummy.next;
    }

    /**
     * 方法2：小根堆
     * 数组中存储的是各个链表的头结点，并且各个链表已经按升序排列了，
     * 那么将所有的头结点存储在小根堆中，重载运算符使得小根堆按各个链表头结点的值进行排序，
     * 每次输出最小的节点（heap.poll即可，时间复杂度为O(1)）到新的链表中，
     * 直至所有链表都为空即可。
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((node1, node2) -> node1.val - node2.val);
        // 结果链表
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        // 从小根堆中取出最小的
        while (!pq.isEmpty()) {
            ListNode curt = pq.poll();
            lastNode.next = curt;
            lastNode = lastNode.next;
            if (curt.next != null) {
                pq.offer(curt.next);
            }
        }
        return dummy.next;
    }
}
