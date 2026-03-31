package suanfa_high.dp;

public class MatrixChainMultiplication {
    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1; // 矩阵数量
        
        // 创建dp表，dp[i][j]表示从第i个矩阵到第j个矩阵的最小代价
        int[][] dp = new int[n + 1][n + 1];
        
        // 初始化：单个矩阵的代价为0
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }
        
        // 填表：自底向上计算所有子链的最优解
        // L是当前考虑的矩阵链长度
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1; // 子链的结束位置
                dp[i][j] = Integer.MAX_VALUE;
                
                // 尝试所有可能的分割点
                for (int k = i; k < j; k++) {
                    // 当前分割的代价 = 左子链代价 + 右子链代价 + 合并两子链的代价
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    
                    // 更新最小代价
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }
        
        // 返回整个链的最小代价
        return dp[1][n];
    }
    
    // 获取最优括号方案
    public static String getOptimalParenthesis(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];
        int[][] split = new int[n + 1][n + 1]; // 记录最优分割点
        
        // 初始化
        for (int i = 1; i <= n; i++) {
            dp[i][i] = 0;
        }
        
        // 填表
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1;
                dp[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        split[i][j] = k; // 记录最优分割点
                    }
                }
            }
        }
        
        // 构建括号方案
        return buildParenthesis(split, 1, n);
    }
    
    private static String buildParenthesis(int[][] split, int i, int j) {
        if (i == j) {
            return "A" + i;
        }
        
        int k = split[i][j];
        String left = buildParenthesis(split, i, k);
        String right = buildParenthesis(split, k + 1, j);
        
        return "(" + left + right + ")";
    }
    
    public static void main(String[] args) {
        int[] dimensions = {30, 35, 15, 5, 10};
        int minOperations = matrixChainOrder(dimensions);
        String optimalParenthesis = getOptimalParenthesis(dimensions);
        
        System.out.println("最小乘法运算次数: " + minOperations);
        System.out.println("最优括号方案: " + optimalParenthesis);
        
        // 另一个例子
        int[] dimensions2 = {5, 10, 3, 12, 5, 50, 6};
        int minOperations2 = matrixChainOrder(dimensions2);
        String optimalParenthesis2 = getOptimalParenthesis(dimensions2);
        
        System.out.println(" 另一个例子:");
        System.out.println("最小乘法运算次数: " + minOperations2);
        System.out.println("最优括号方案: " + optimalParenthesis2);
    }
}
