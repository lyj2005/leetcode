package suanfa_high.math;

public class FastPower {
    
    // 快速幂算法 - 迭代版本
    public static long fastPowerIterative(long base, long exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        
        long result = 1;
        
        while (exponent > 0) {
            // 如果当前位为1，将结果乘以当前base
            if ((exponent & 1) == 1) {
                result *= base;
            }
            
            // 将base平方
            base *= base;
            
            // 右移指数
            exponent >>= 1;
        }
        
        return result;
    }
    
    // 快速幂算法 - 递归版本
    public static long fastPowerRecursive(long base, long exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        
        if (exponent == 0) {
            return 1;
        }
        
        // 计算x^(n/2)
        long half = fastPowerRecursive(base, exponent / 2);
        
        // 如果指数是奇数
        if (exponent % 2 == 1) {
            return half * half * base;
        } else {
            return half * half;
        }
    }
    
    // 模幂运算：计算(base^exponent) % modulus
    public static long fastPowerMod(long base, long exponent, long modulus) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent must be non-negative");
        }
        
        if (modulus == 1) {
            return 0; // 任何数模1都为0
        }
        
        // 确保base在modulus范围内
        base %= modulus;
        
        long result = 1;
        
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = (result * base) % modulus;
            }
            
            base = (base * base) % modulus;
            exponent >>= 1;
        }
        
        return result;
    }
}
