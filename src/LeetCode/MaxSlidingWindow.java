package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @FileName: MaxSlidingWindow.java
 * @Description: 滑动窗口最大值
 * @Author: ABCpril
 * @Date: 2021/11/22
 */
public class MaxSlidingWindow {
    class MonotonicQueue {
        private LinkedList<Integer> q = new LinkedList<>();

        public void push(int x) {
            // 压扁的过程
            while (!q.isEmpty() && q.getLast() < x) {
                q.pollLast();
            }
            q.addLast(x);
        }
        // 队头为最大值
        public int max() {
            return q.getFirst();
        }

        public void pop(int x) {
            if (q.getFirst() != x)  {
                return;
            }
            q.pollFirst();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        MonotonicQueue window = new MonotonicQueue();

        for (int i = 0; i < nums.length; i++) {
            if (i - k + 1 < 0) {
                // 窗口滑全就进入else逻辑
                window.push(nums[i]);
            }
            else {
                window.push(nums[i]);
                res.add(window.max());
                // 窗口满了需要为下一次push腾位置
                window.pop(nums[i - k + 1]);
                // pop的逻辑决定了：如果nums[i-k+1]已经被压扁，单调队列只剩一个元素（窗口实际上
                // 呈现123这样的单调递增），此时不会真的把最后一个元素pop出去
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
