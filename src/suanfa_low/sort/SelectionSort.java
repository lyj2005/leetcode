package suanfa_low.sort;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        
        // 遍历数组
        for (int i = 0; i < n - 1; i++) {
            // 找出从i到n-1中最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (i != minIndex) {
                // 将找到的最小值与当前位置i交换
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
            }
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
        int[] arr = {64, 25, 12, 22, 11};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        selectionSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
