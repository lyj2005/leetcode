package suanfa_low.search;

public class InterpolationSearch {
    // 迭代实现
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        
        while (low <= high && target >= arr[low] && target <= arr[high]) {
            // 使用插值公式计算位置
            int pos = low + (((target - arr[low]) * (high - low)) / 
                            (arr[high] - arr[low]));
            
            // 找到目标值
            if (arr[pos] == target) {
                return pos;
            }
            
            // 在左半部分继续查找
            if (arr[pos] > target) {
                high = pos - 1;
            }
            // 在右半部分继续查找
            else {
                low = pos + 1;
            }
        }
        
        // 未找到目标值
        return -1;
    }
    
    // 递归实现
    public static int interpolationSearchRecursive(int[] arr, int target, int low, int high) {
        // 基本条件检查
        if (low <= high && target >= arr[low] && target <= arr[high]) {
            // 使用插值公式计算位置
            int pos = low + (((target - arr[low]) * (high - low)) / 
                           (arr[high] - arr[low]));
            
            // 找到目标值
            if (arr[pos] == target) {
                return pos;
            }
            
            // 在左半部分继续查找
            if (arr[pos] > target) {
                return interpolationSearchRecursive(arr, target, low, pos - 1);
            }
            
            // 在右半部分继续查找
            return interpolationSearchRecursive(arr, target, pos + 1, high);
        }
        
        return -1;
    }
    
    // 测试
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int target = 70;
        
        // 迭代方法
        int result = interpolationSearch(arr, target);
        if (result == -1) {
            System.out.println("元素 " + target + " 不存在于数组中");
        } else {
            System.out.println("元素 " + target + " 在数组中的索引为 " + result);
        }
        
        // 递归方法
        result = interpolationSearchRecursive(arr, target, 0, arr.length - 1);
        if (result == -1) {
            System.out.println("元素 " + target + " 不存在于数组中");
        } else {
            System.out.println("元素 " + target + " 在数组中的索引为 " + result);
        }
    }
}
