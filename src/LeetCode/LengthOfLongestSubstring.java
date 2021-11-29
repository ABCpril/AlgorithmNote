package LeetCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @FileName: LengthOfLongestSubstring.java
 * @Description: 无重复字符的最长子串
 * @Author: ABCpril
 * @Date: 2021/11/29
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Set<Character> window = new HashSet<>();
        int l = 0, r = -1, res = 0;
        char[] chars = s.toCharArray();

        while (r < s.length() - 1) {
            ++r;
            if (!window.contains(chars[r])) {
                window.add(chars[r]);
            }
            else {
                // when window needs shrink
                while (chars[l] != chars[r]) {
                    window.remove(chars[l]);
                    ++l;
                }
                // l此时指向与rightChar相同的字符，需再后移一位
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
}
