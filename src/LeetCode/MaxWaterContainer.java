package LeetCode;

/**
 * @FileName: MaxWaterContainer.java
 * @Description: 盛最多水的容器
 * @Author: ABCpril
 * @Date: 2021/12/09
 */
public class MaxWaterContainer {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, Vol = 0;
        while (i < j) {
            if (height[i] < height[j]) {
                // i是短边，木桶效应
                Vol = Math.max(Vol, (j - i) * height[i]);
                i++;
            }
            else {
                Vol = Math.max(Vol, (j - i) * height[j]);
                j--;
            }
        }
        return Vol;
    }
}
