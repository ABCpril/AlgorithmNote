package LintCode;

/**
 * @FileName: ReorderList.java
 * @Description: 重排链表
 * @Author: ABCpril
 * @Date: 2021/12/06
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        // L0->L1->...->L(mid)
        // Ln->Ln-1->...->L(n-mid)
        // 链表元素是单数 1->2->3->4->5->null
        // 1->2->3   5->4   1->5->2->4->3->null
        // 链表元素是双数 1->2->3->4->null
        // 1->2      4->3   1->4->2->3->null
        // 中间偏左
        ListNode mid = getMiddleNode(head);
        // 左右两半边断开
        ListNode rightHead = mid.next;
        mid.next = null;
        // 翻转链表
        rightHead = reverseList(rightHead);
        // 合并左右链表
        head = mergeTwoLists(head, rightHead);
    }

    private ListNode getMiddleNode(ListNode head) {
        // 快慢指针找中点
        ListNode slow = head, fast = head.next;
        //    s     f
        // s  f
        // 1->2->3->4->null
        //       s        f
        //    s     f
        // s  f
        // 1->2->3->4->5->null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        // prev、curt两兄弟翻转链表
        ListNode prev = null, curt = head;
        while (curt != null) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }

        return prev;
    }

    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        // 两边长度相同，left先冲到null
        // 两边长度不同，只可能是left多出一个节点
        ListNode dummy = new ListNode(0);
        ListNode lastNode = dummy;
        int i = 0;
        while (left != null && right != null) {
            // 链表元素是单数 1->2->3->4->5->null
            // 1->2->3   5->4   1->5->2->4->3->null
            if (i % 2 == 0) {
                lastNode.next = left;
                left = left.next;
            }
            else {
                lastNode.next = right;
                right = right.next;
            }
            lastNode = lastNode.next;
            i++;
        }
        if (left != null) {
            // mid.next=null也已做过左半边null处理
            lastNode.next = left;
        }
        if (right != null) {
            // 右半边已做过null处理
            lastNode.next = right;
        }
        return dummy.next;
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
