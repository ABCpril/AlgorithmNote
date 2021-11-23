package LeetCode;

/**
 * @FileName: ReversePairs.java
 * @Description: 数组中的逆序对
 * @Author: ABCpril
 * @Date: 2021/11/23
 */
public class ReversePairs {
    static int cnt = 0;

    public int reversePairs(int[] nums) {
        int n = nums.length - 1;
        mergeSort(nums, 0, n);
        return cnt;
    }

    private static void mergeSort(int[] nums, int l, int r) {
        // 递归的出口
        if (l >= r) return;

        int mid = (l + r) >> 1;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);

        // 归并
        int[] temp = new int[nums.length];
        int i = l, j = mid + 1, k = 0;
        while (i <= mid && j <= r) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            }
            else {
                // nums[i]可以和当前j指向的数组成逆序对
                // 在i之后，比nums[i]更大的数自然也可以和nums[j]组成逆序对
                cnt += (mid - i + 1);
                temp[k++] = nums[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= r) {
            temp[k++] = nums[j++];
        }
        // 把temp中的数复制回原数组nums
        for (i = l, k = 0; i <= r; i++, k++) {
            nums[i] = temp[k];
        }
    }
}
