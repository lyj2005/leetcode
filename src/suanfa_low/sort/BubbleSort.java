package suanfa_low.sort;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            // 每次遍历后，最大的i+1个元素已经排好序
            for (int j = 0; j < n - i - 1; j++) {
                // 如果当前元素大于下一个元素，则交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
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
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        bubbleSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
