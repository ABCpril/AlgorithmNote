package LintCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName: MinWindow.java
 * @Description: 最小子串覆盖
 * @Author: ABCpril
 * @Date: 2021/12/06
 */
public class MinWindow {
    public String minWindow(String source, String target) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        int matchCnt = 0;

        for (char c : target.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        char[] sourceChars = source.toCharArray();
        // 包含target的最短子串长度，与开始下标
        int l = 0, r = -1, length = Integer.MAX_VALUE, startIndex = -1;

        while (r < sourceChars.length - 1) {
            ++r;
            char rightChar = sourceChars[r];
            int newFreqR = window.getOrDefault(rightChar, 0) + 1;
            window.put(rightChar, newFreqR);
            if (need.containsKey(rightChar)) {
                // ==保证了超出target所需个数时，matchCnt不变
                if (newFreqR == need.get(rightChar)) {
                    ++matchCnt;
                }
            }
            // when window needs shrink (窗口合规时，尝试缩小左边界)
            while (matchCnt == need.size()) {
                // 在最开始进行判断，窗口一定满足包含target字符
                if (r - l + 1 < length) {
                    length = r - l + 1;
                    startIndex = l;
                }
                char leftChar = sourceChars[l];
                ++l;
                if (need.containsKey(leftChar)) {
                    int newFreqL = window.get(leftChar) - 1;
                    if (newFreqL < need.get(leftChar)) {
                        --matchCnt;
                    }
                    window.put(leftChar, newFreqL);
                }
            }
        }

        return length == Integer.MAX_VALUE ? "" : new String(sourceChars, startIndex, length);
    }
}
