package LintCode;

/**
 * @FileName: SwapNodes.java
 * @Description: 交换链表当中两个节点
 * @Author: ABCpril
 * @Date: 2021/12/08
 */
public class SwapNodes {
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        if (head == null || head.next == null || v1 == v2) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 防止v1或v2在第一个，v1Pre、v2Pre只能为null的情况
        ListNode tool = dummy, v1Pre = null, v2Pre = null;
        // v1->...->v2
        while (tool.next != null) {
            if (tool.next.val == v1) {
                v1Pre = tool;
                v1Pre.next = tool.next;
            }
            else if (tool.next.val == v2) {
                v2Pre = tool;
                v2Pre.next = tool.next;
            }
            tool = tool.next;
        }
        if (v1Pre == null || v2Pre == null) {
            return head;
        }
        ListNode v1Node = v1Pre.next, v2Node = v2Pre.next;
        // 交换节点，而非交换值
        if (v1Node.next == v2Node) {
            // ...->v1->v2->...
            v1Node.next = v2Node.next;
            v2Node.next = v1Node;
            v1Pre.next = v2Node;
        }
        else if (v2Node.next == v1Node) {
            // ...->v2->v1->...
            v2Node.next = v1Node.next;
            v1Node.next = v2Node;
            v2Pre.next = v1Node;
        }
        else {
            // ...->v1->...->v2->...
            // ...->v2->...->v1->...
            ListNode temp = v1Node.next;
            v2Pre.next = v1Node;
            v1Node.next = v2Node.next;
            v1Pre.next = v2Node;
            v2Node.next = temp;
        }
        return dummy.next;
    }
}
