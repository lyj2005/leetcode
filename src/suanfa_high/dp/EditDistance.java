package suanfa_high.dp;

public class EditDistance {
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // 创建DP表
        int[][] dp = new int[m + 1][n + 1];
        
        // 初始化边界条件
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // 填充DP表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 如果字符相同，不需要操作
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 取三种操作的最小值
                    dp[i][j] = Math.min(dp[i - 1][j - 1], // 替换
                                Math.min(dp[i - 1][j],    // 删除
                                         dp[i][j - 1]))   // 插入
                                + 1;
                }
            }
        }
        
        return dp[m][n];
    }
    
    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        
        System.out.println("将 " + word1 + " 转换为 " + word2 + " 的最少操作数：" + minDistance(word1, word2));
        
        String word3 = "intention";
        String word4 = "execution";
        
        System.out.println("将 " + word3 + " 转换为 " + word4 + " 的最少操作数：" + minDistance(word3, word4));
    }
}
