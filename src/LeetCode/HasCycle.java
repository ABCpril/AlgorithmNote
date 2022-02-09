package LeetCode;

/**
 * @FileName: HasCycle.java
 * @Description: 环形链表
 * @Author: ABCpril
 * @Date: 2022/02/09
 */
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if(head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
