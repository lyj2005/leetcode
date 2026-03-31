package suanfa_high.backtracking;

import java.util.*;

public class Permutation {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // 用于标记元素是否已被使用
        boolean[] used = new boolean[nums.length];
        // 当前排列的路径
        List<Integer> path = new ArrayList<>();
        
        backtrack(nums, result, path, used);
        return result;
    }
    
    private static void backtrack(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] used) {
        // 当前排列已完成
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // 跳过已使用的元素
            if (used[i]) {
                continue;
            }
            
            // 做选择
            path.add(nums[i]);
            used[i] = true;
            
            // 进入下一层决策树
            backtrack(nums, result, path, used);
            
            // 撤销选择（回溯）
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        
        System.out.println("数组 " + Arrays.toString(nums) + " 的全排列：");
        for (List<Integer> perm : permutations) {
            System.out.println(perm);
        }
        
        System.out.println("总共有 " + permutations.size() + " 种排列");
    }
}
