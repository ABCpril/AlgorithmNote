package LintCode;

/**
 * @FileName: ReverseList.java
 * @Description: 翻转链表(一)
 * @Author: ABCpril
 * @Date: 2021/12/08
 */
public class ReverseList {
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curt = head;
        while (curt != null) {
            ListNode temp = curt.next;
            curt.next = prev;
            prev = curt;
            curt = temp;
        }
        return prev;
    }
}
