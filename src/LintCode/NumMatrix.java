package LintCode;

/**
 * @FileName: SumRegion.java
 * @Description: 子矩阵求和
 * @Author: ABCpril
 * @Date: 2021/11/25
 */
public class NumMatrix {
    int[][] a, s;

    public NumMatrix(int[][] matrix) {
        a = matrix;
        // s[0][]=s[][0]=0，方便计算
        s = new int[matrix.length + 1][matrix[0].length + 1];
        // 求前缀和矩阵
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                // a[i - 1][j - 1]为第 i 行第 j 列的元素
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + a[i - 1][j - 1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++; col1++; row2++; col2++;
        return s[row2][col2] - s[row2][col1 - 1] - s[row1 - 1][col2] + s[row1 - 1][col1 - 1];
    }
}
