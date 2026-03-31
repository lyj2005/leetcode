package suanfa_low.recursion;

public class Factorial {
    public static int factorial(int n) {
        // 基准情况
        if (n == 0 || n == 1) {
            return 1;
        }
        
        // 递归情况：n! = n * (n-1)!
        return n * factorial(n - 1);
    }
    
    // 测试
    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            System.out.printf("%d! = %d ", i, factorial(i));
        }
    }
}
