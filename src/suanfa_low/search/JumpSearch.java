package suanfa_low.search;

public class JumpSearch {
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        
        // 确定最佳步长
        int step = (int) Math.floor(Math.sqrt(n));
        
        // 跳跃查找阶段
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;  // 未找到元素
            }
        }
        
        // 线性查找阶段
        while (arr[prev] < target) {
            prev++;
            
            // 如果已到达下一步长或数组末尾，则未找到元素
            if (prev == Math.min(step, n)) {
                return -1;
            }
        }
        
        // 检查是否找到目标元素
        if (arr[prev] == target) {
            return prev;  // 返回元素索引
        }
        
        return -1;  // 未找到元素
    }
    
    // 测试
    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610};
        int target = 55;
        
        int index = jumpSearch(arr, target);
        
        if (index != -1) {
            System.out.println("元素 " + target + " 在索引 " + index + " 处找到");
        } else {
            System.out.println("元素 " + target + " 未在数组中找到");
        }
    }
}
