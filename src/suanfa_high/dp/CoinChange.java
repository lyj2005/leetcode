package suanfa_high.dp;

import java.util.Arrays;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        // 定义dp数组，dp[i]表示凑成金额i所需的最少硬币数
        int[] dp = new int[amount + 1];
        
        // 初始化dp数组，除了dp[0]=0外，其他都初始化为一个较大的值
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        // 遍历所有可能的金额
        for (int i = 1; i <= amount; i++) {
            // 遍历所有硬币面值
            for (int coin : coins) {
                // 只有当硬币面值小于等于当前金额时才能使用
                if (coin <= i) {
                    // 状态转移方程：使用当前硬币与不使用当前硬币取最小值
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // 如果dp[amount]仍然是初始值，说明无法凑出amount
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        System.out.println("最少需要 " + coinChange(coins, amount) + " 个硬币");
        
        int[] coins2 = {2};
        int amount2 = 3;
        
        System.out.println("最少需要 " + coinChange(coins2, amount2) + " 个硬币");
    }
}
