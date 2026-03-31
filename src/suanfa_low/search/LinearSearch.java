package suanfa_low.search;

public class LinearSearch {
    public static int linearSearch(int[] arr, int target) {
        // 遍历数组
        for (int i = 0; i < arr.length; i++) {
            // 找到目标值，返回索引
            if (arr[i] == target) {
                return i;
            }
        }
        // 未找到目标值，返回-1
        return -1;
    }
    
    // 测试
    public static void main(String[] args) {
        int[] arr = {10, 20, 80, 30, 60, 50, 110, 100, 130, 170};
        int target = 110;
        
        int result = linearSearch(arr, target);
        if (result == -1) {
            System.out.println("元素 " + target + " 不存在于数组中");
        } else {
            System.out.println("元素 " + target + " 在数组中的索引为 " + result);
        }
    }
}
