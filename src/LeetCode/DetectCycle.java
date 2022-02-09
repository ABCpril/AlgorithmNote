package LeetCode;

/**
 * @FileName: DetectCycle.java
 * @Description: 环形链表II
 * @Author: ABCpril
 * @Date: 2022/02/09
 */
public class DetectCycle {
    /**
     * ● D：从头节点到入环点的距离
     * ● S1：从入环点到首次相遇点的距离
     * ● S2：从首次相遇点到入环点的距离
     */
    public ListNode detectCycle(ListNode head) {
        // 相遇点走S2+0个或几个环到入环点，从head走D到入环点
        // 2 * (D + S1) = D + n(S1 + S2) + S1
        // D = (n - 1)(S1 + S2) + S2
        // fast从相遇点，slow从head开始走，最终会和fast在入环点相遇
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if(hasCycle) {
            slow = head;
            while(slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
}
