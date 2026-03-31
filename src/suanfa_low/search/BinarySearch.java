package suanfa_low.search;

public class BinarySearch {
    // 迭代实现
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            // 避免整数溢出
            int mid = left + (right - left) / 2;
            
            // 找到目标值
            if (arr[mid] == target) {
                return mid;
            }
            // 在左半部分继续查找
            else if (arr[mid] > target) {
                right = mid - 1;
            }
            // 在右半部分继续查找
            else {
                left = mid + 1;
            }
        }
        
        // 未找到目标值
        return -1;
    }
    
    // 递归实现
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        
        int mid = left + (right - left) / 2;
        
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            return binarySearchRecursive(arr, target, left, mid - 1);
        } else {
            return binarySearchRecursive(arr, target, mid + 1, right);
        }
    }
    
    // 测试
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 50, 70, 80};
        int target = 10;
        
        // 迭代方法
        int result = binarySearch(arr, target);
        if (result == -1) {
            System.out.println("元素 " + target + " 不存在于数组中");
        } else {
            System.out.println("元素 " + target + " 在数组中的索引为 " + result);
        }
        
        // 递归方法
        result = binarySearchRecursive(arr, target, 0, arr.length - 1);
        if (result == -1) {
            System.out.println("元素 " + target + " 不存在于数组中");
        } else {
            System.out.println("元素 " + target + " 在数组中的索引为 " + result);
        }
    }
}
