package suanfa_high.dp;

public class Fibonacci {
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        
        // 创建dp数组存储计算结果
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        // 自底向上计算
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
        int n = 10;
        System.out.println("F(" + n + ") = " + fibonacci(n));
        
        System.out.println("斐波那契数列前10项：");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }
}
