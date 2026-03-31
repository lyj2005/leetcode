package suanfa_high.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfEratosthenes {
    
    // 查找小于等于n的所有素数
    public static List<Integer> findPrimes(int n) {
        // 创建一个布尔数组，初始假设所有数都是素数
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        
        // 0和1不是素数
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;
        
        // 埃氏筛法核心逻辑
        for (int i = 2; i * i <= n; i++) {
            // 如果i是素数
            if (isPrime[i]) {
                // 将i的倍数标记为合数，从i*i开始
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // 收集所有素数
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    public static void main(String[] args) {
        int n = 100;
        List<Integer> primes = findPrimes(n);
        
        System.out.println("小于等于 " + n + " 的素数有:");
        System.out.println(primes);
        System.out.println("共 " + primes.size() + " 个素数");
    }
}
