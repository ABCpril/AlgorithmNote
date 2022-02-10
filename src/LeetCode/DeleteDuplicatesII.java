package LeetCode;

/**
 * @FileName: DeleteDuplicatesII.java
 * @Description: 删除排序链表中的重复元素II
 * @Author: ABCpril
 * @Date: 2022/02/10
 */
public class DeleteDuplicatesII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curt = dummy;
        // curt->[v1->v2->v3]->v4
        while (curt.next != null && curt.next.next != null) {
            if (curt.next.val == curt.next.next.val) {
                // 从deletePoint开始依次删除val=x的点
                ListNode deletePoint = curt.next;
                int x = curt.next.val;
                while (deletePoint != null) {
                    if (deletePoint.val == x) {
                        deletePoint = deletePoint.next;
                    }
                    else {
                        break;
                    }
                }
                curt.next = deletePoint;
            }
            else {
                // curt->v1->[v2->v3]->v4
                curt = curt.next;
            }
        }
        return dummy.next;
    }
}
