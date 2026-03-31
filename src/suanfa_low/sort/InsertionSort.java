package suanfa_low.sort;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 当前待插入元素
            int j = i - 1;
            
            // 将大于key的元素向后移动
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            
            // 找到key的正确位置，插入
            arr[j + 1] = key;
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
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        insertionSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
