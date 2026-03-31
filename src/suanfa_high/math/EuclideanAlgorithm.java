package suanfa_high.math;

public class EuclideanAlgorithm {
    // 递归实现
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    // 迭代实现
    public static int gcdIterative(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // 扩展欧几里得算法，计算ax + by = gcd(a,b)的整数解
    public static int[] extendedGcd(int a, int b) {
        if (b == 0) {
            return new int[]{a, 1, 0};
        }
        
        int[] result = extendedGcd(b, a % b);
        int gcd = result[0];
        int x = result[2];
        int y = result[1] - (a / b) * result[2];
        
        return new int[]{gcd, x, y};
    }
    
    public static void main(String[] args) {
        int a = 252, b = 105;
        
        // 测试基本欧几里得算法
        System.out.println("GCD of " + a + " and " + b + " (递归): " + gcd(a, b));
        System.out.println("GCD of " + a + " and " + b + " (迭代): " + gcdIterative(a, b));
        
        // 测试扩展欧几里得算法
        int[] result = extendedGcd(a, b);
        System.out.println("GCD: " + result[0]);
        System.out.println("Coefficients x and y: " + result[1] + ", " + result[2]);
        System.out.println("Verification: " + a + " * " + result[1] + " + " + b + " * " + result[2] + " = " + result[0]);
    }
}
