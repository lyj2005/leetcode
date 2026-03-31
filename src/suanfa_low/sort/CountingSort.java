package suanfa_low.sort;

public class CountingSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // 找出数组中的最大值和最小值
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        // 计算计数数组的大小
        int range = max - min + 1;
        
        // 创建计数数组并统计每个元素出现的次数
        int[] count = new int[range];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }
        
        // 计算累加数组，确定每个元素在排序后的位置
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        
        // 创建临时数组存储排序结果
        int[] output = new int[arr.length];
        
        // 从后往前遍历原数组，保证排序的稳定性
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        
        // 将排序结果复制回原数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }
    
    // 打印数组
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    // 测试
    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        countingSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
