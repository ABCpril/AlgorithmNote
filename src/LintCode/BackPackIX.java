package LintCode;

/**
 * @FileName: BackPackIX.java
 * @Description: 背包问题IX
 * @Author: ABCpril
 * @Date: 2021/12/08
 */
public class BackPackIX {
    public double backpackIX1(int n, int[] prices, double[] probability) {
        // 物品价值在这里是，第k所大学在耗费prices[k-1]万元的申请费用下，
        // 至少拿到一份offer的情况有两种
        // 申请前k-1所大学成功，k大学无所谓成功或失败(概率为1) + 申请前k-1所大学失败k大学成功，贡献的概率
        double[][] dp = new double[prices.length + 1][n + 1];
        for (int i = 1; i <= prices.length; i++) {
            for (int j = 1; j <= n; j++) {
                // 申请费用在预算以内
                if (prices[i - 1] <= j) {
                    // 申请前i-1所大学得到offer且申请i大学(成功或失败)的概率+前i-1所没拿到，申请当前i大学拿到offer的概率
                    double pickThisSchool = dp[i - 1][j - prices[i - 1]]
                            + (1 - dp[i - 1][j - prices[i - 1]]) * probability[i - 1];
                    // 考虑申请前i-1所大学，不申请当前i大学，拿到offer的概率
                    dp[i][j] = Math.max(dp[i - 1][j], pickThisSchool);
                }
            }
        }
        return dp[prices.length][n];
    }

    // 以下是一维数组优化
    public double backpackIX2(int n, int[] prices, double[] probability) {
        // 物品价值在这里是，第k所大学在耗费prices[k-1]万元的申请费用下，
        // 至少拿到一份offer的情况有两种
        // 申请前k-1所大学成功，k大学无所谓成功或失败(概率为1) + 申请前k-1所大学失败k大学成功，贡献的概率
        double[] dp = new double[n + 1];
        for (int i = 1; i <= prices.length; i++) {
            // 保证申请费用在预算以内
            for (int j = n; j >= prices[i - 1]; j--) {
                // 申请前i-1所大学得到offer且申请i大学(成功或失败)的概率+前i-1所没拿到，申请当前i大学拿到offer的概率
                double pickThisSchool = dp[j - prices[i - 1]]
                        + (1 - dp[j - prices[i - 1]]) * probability[i - 1];
                // 考虑申请前i-1所大学，不申请当前i大学，拿到offer的概率
                dp[j] = Math.max(dp[j], pickThisSchool);
            }
        }
        return dp[n];
    }
}
