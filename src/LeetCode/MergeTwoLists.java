package LeetCode;

/**
 * @FileName: MergeTwoLists.java
 * @Description: 合并两个有序链表
 * @Author: ABCpril
 * @Date: 2022/02/11
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                lastNode.next = list1;
                list1 = list1.next;
            }
            else {
                lastNode.next = list2;
                list2 = list2.next;
            }
            lastNode = lastNode.next;
        }
        if (list1 != null) {
            lastNode.next = list1;
        }
        if (list2 != null) {
            lastNode.next = list2;
        }
        return dummy.next;
    }
}
