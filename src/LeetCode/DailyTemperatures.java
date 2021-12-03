package LeetCode;

/**
 * @FileName: DailyTemperatures.java
 * @Description: 每日温度
 * @Author: ABCpril
 * @Date: 2021/12/03
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int days = temperatures.length;
        int[] res = new int[days];
        // 数组模拟单调栈，栈中存下标
        int[] stack = new int[days + 1];
        // 栈顶指针，指向0代表栈空
        int tt = 0;

        for (int i = 0; i < days; i++) {
            int x = temperatures[i];
            // 栈中存的是下标
            while (tt > 0 && x > temperatures[stack[tt]]) {
                // 需要求等待的天数
                res[stack[tt]] = i - stack[tt];
                tt--;
            }
            stack[++tt] = i;
        }

        return res;
    }
}
