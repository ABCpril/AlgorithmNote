package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: NextGreaterElementI.java
 * @Description: 下一个更大元素I
 * @Author: ABCpril
 * @Date: 2021/12/02
 */
public class NextGreaterElementI {
    // nums1是nums2的子集，找出nums1中每个元素在nums2中的下一个更大元素
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // nums1、nums2中的所有整数 互不相同
        Map<Integer, Integer> mapping = new HashMap<>();

        // 数组模拟单调栈，存下标更通用，这里存数即可
        int[] stack = new int[nums2.length + 1];
        // 栈顶指针指向0代表栈空
        int tt = 0;

        for (int i = 0; i < nums2.length; i++) {
            int x = nums2[i];
            while (tt > 0 && x > stack[tt]) {
                // 说明 i 所指元素 x 是目前栈中元素的下一个更大元素
                // 弹栈直到栈空
                mapping.put(stack[tt], x);
                tt--;
            }
            stack[++tt] = x;
        }

        for (int i = 0; i < nums1.length; i++) {
            // 下一个更大元素不存在，返回-1
            nums1[i] = mapping.getOrDefault(nums1[i], -1);
        }
        return nums1;
    }
}
