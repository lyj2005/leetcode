package suanfa_low.sort;

public class RadixSort {
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // 找出最大值，确定最大位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        
        // 对每一位进行计数排序
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
    
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n]; // 输出数组
        int[] count = new int[10]; // 计数数组，默认为10，因为一位数字的范围是0~9
        
        // 统计当前位上每个数字出现的次数
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }
        
        // 计算累加数组，确定每个数字在输出数组中的位置
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        
        // 构建输出数组，从后向前遍历以保持稳定性
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        
        // 将排序好的数组复制回原数组
        for (int i = 0; i < n; i++) {
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
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        radixSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
