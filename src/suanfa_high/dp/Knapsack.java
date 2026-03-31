package suanfa_high.dp;

public class Knapsack {
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length || capacity <= 0) {
            return 0;
        }
        
        int n = weights.length;
        // 创建DP表
        int[][] dp = new int[n + 1][capacity + 1];
        
        // 填充DP表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 当前物品的重量和价值（索引从0开始）
                int currentWeight = weights[i - 1];
                int currentValue = values[i - 1];
                
                if (currentWeight > j) {
                    // 当前物品太重，无法放入
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可以选择放或不放，取较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - currentWeight] + currentValue);
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    // 回溯找出所选物品
    public static boolean[] getSelectedItems(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // 填充DP表
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        
        // 回溯确定所选物品
        boolean[] selected = new boolean[n];
        int remainingCapacity = capacity;
        
        for (int i = n; i > 0; i--) {
            if (dp[i][remainingCapacity] != dp[i - 1][remainingCapacity]) {
                // 当前物品被选中
                selected[i - 1] = true;
                remainingCapacity -= weights[i - 1];
            }
        }
        
        return selected;
    }
    
    public static void main(String[] args) {
        int[] weights = {3, 4, 5};
        int[] values = {4, 5, 6};
        int capacity = 10;
        
        int maxValue = knapsack01(weights, values, capacity);
        boolean[] selected = getSelectedItems(weights, values, capacity);
        
        System.out.println("最大价值: " + maxValue);
        System.out.println("选择的物品:");
        
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                System.out.println("物品 " + (i + 1) + "，重量: " + weights[i] + "，价值: " + values[i]);
            }
        }
    }
}
