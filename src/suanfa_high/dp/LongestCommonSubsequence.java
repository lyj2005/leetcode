package suanfa_high.dp;

public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return 0;
        }
        
        int m = text1.length();
        int n = text2.length();
        
        // 创建dp表，dp[i][j]表示text1前i个字符和text2前j个字符的LCS长度
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充DP表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    // 当前字符匹配，LCS长度加1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 当前字符不匹配，取上方或左方的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // dp[m][n] 包含整个LCS的长度
        return dp[m][n];
    }
    
    // 重构最长公共子序列
    public static String getLCS(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return "";
        }
        
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        // 填充DP表格
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // 重构LCS
        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                // 字符匹配，加入LCS
                sb.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // 向上移动
                i--;
            } else {
                // 向左移动
                j--;
            }
        }
        
        // 需要反转结果
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
        String text1 = "ABCBDAB";
        String text2 = "BDCABA";
        
        int lcsLength = longestCommonSubsequence(text1, text2);
        String lcs = getLCS(text1, text2);
        
        System.out.println("序列1: " + text1);
        System.out.println("序列2: " + text2);
        System.out.println("最长公共子序列长度: " + lcsLength);
        System.out.println("一个最长公共子序列: " + lcs);
    }
}
