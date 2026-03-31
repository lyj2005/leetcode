package suanfa_low.sort;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区点位置
            int pivotIndex = partition(arr, low, high);
            
            // 递归排序左右子数组
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // 选择最左侧元素作为基准
        int pivot = arr[low];
        int i = low + 1;
        
        for (int j = low + 1; j <= high; j++) {
            // 将小于基准的元素移到左侧
            if (arr[j] < pivot) {
                // 交换元素
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        
        // 将基准元素放到正确位置
        int temp = arr[low];
        arr[low] = arr[i - 1];
        arr[i - 1] = temp;
        
        return i - 1;
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
        int[] arr = {10, 7, 8, 9, 1, 5};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        quickSort(arr, 0, arr.length - 1);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
