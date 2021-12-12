package LintCode;

/**
 * @FileName: RotateList.java
 * @Description: 旋转链表
 * @Author: ABCpril
 * @Date: 2021/12/12
 */
public class RotateList {
    // 1->2->3->4->5 k = 2
    // 4->5->1->2->3
    // 第 n - k 个结点（倒数第k个）是新的头结点
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        // 先走到尾结点，并统计结点个数
        ListNode tool = head;
        int nodesCnt = 1;
        while (tool.next != null) {
            tool = tool.next;
            nodesCnt++;
        }

        // 取模防止 k > 结点个数的情况
        int steps = nodesCnt - k % nodesCnt;
        // 代表k是结点个数的倍数
        if (steps == nodesCnt) {
            return head;
        }
        // 连成环的操作放在后面，否则k是结点个数倍数时，return的链表是带环的，未执行后面的断环环节
        tool.next = head;
        // 先走到newHead的前一个，即newTail，断开两者，返回newHead
        for (int i = 0; i < steps - 1; i++) {
            head = head.next;
        }
        ListNode newTail = head;
        ListNode newHead = newTail.next;
        // 断环
        newTail.next = null;
        return newHead;
    }
}
