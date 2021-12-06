package LeetCode;

/**
 * @FileName: TwoSumII.java
 * @Description: 两数之和II - 输入有序数组
 * @Author: ABCpril
 * @Date: 2021/12/05
 */
public class TwoSumII {
    // 1.二分搜索target - x
    public int[] twoSum1(int[] numbers, int target) {
        int[] res = new int[2];

        for (int i = 0; i < numbers.length; i++) {
            int x = numbers[i];
            // index2一定比index1大，所以二分搜索范围是[i + 1, r]
            int index = binarySearch(numbers, i, target - x);
            if (index != -1) {
                res[0] = i + 1; res[1] = index + 1;
                break;
            }
        }

        return res;
    }

    private int binarySearch(int[] nums, int index1, int x) {
        int l = index1 + 1, r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        if (nums[l] != x) return -1;
        return l;
    }

    //2.双指针
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;

        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[] {l + 1, r + 1};
            }
            // 数组已排好序
            else if (sum < target) {
                l++;
            }
            else if (sum > target) {
                r--;
            }
        }

        return new int[]{};
    }
}
