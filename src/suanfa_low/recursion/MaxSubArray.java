package suanfa_low.recursion;

public class MaxSubArray {
    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // 选择当前元素，或将当前元素加入到现有子数组
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // 更新全局最大和
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        
        return maxSoFar;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("最大子数组和: " + maxSubArray(arr1)); // 输出: 6 (子数组 [4, -1, 2, 1])
        
        int[] arr2 = {1};
        System.out.println("最大子数组和: " + maxSubArray(arr2)); // 输出: 1
        
        int[] arr3 = {5, 4, -1, 7, 8};
        System.out.println("最大子数组和: " + maxSubArray(arr3)); // 输出: 23 (整个数组)
    }
}
