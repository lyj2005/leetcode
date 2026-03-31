package suanfa_high.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 初始化每个位置的LIS长度为1
        
        int maxLength = 1;
        
        // 动态规划过程
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
    
    // 获取最长递增子序列
    public static List<Integer> getLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int n = nums.length;
        int[] dp = new int[n];
        int[] prev = new int[n]; // 记录前驱节点
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);
        
        int maxLength = 1;
        int endIndex = 0;
        
        // 动态规划过程
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j; // 记录前驱
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                endIndex = i; // 记录最长子序列的结束位置
            }
        }
        
        // 根据前驱数组构造LIS
        List<Integer> lis = new ArrayList<>();
        while (endIndex != -1) {
            lis.add(nums[endIndex]);
            endIndex = prev[endIndex];
        }
        Collections.reverse(lis); // 需要反转
        
        return lis;
    }
    
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int lisLength = lengthOfLIS(nums);
        List<Integer> lis = getLIS(nums);
        
        System.out.println("数组: " + Arrays.toString(nums));
        System.out.println("最长递增子序列的长度: " + lisLength);
        System.out.println("一个最长递增子序列: " + lis);
    }
}
