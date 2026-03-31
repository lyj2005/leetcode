package suanfa_low.sort;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // 逐个从堆顶取出元素
        for (int i = n - 1; i > 0; i--) {
            // 将当前堆顶（最大值）移到末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            
            // 对剩余元素重新构建最大堆
            heapify(arr, i, 0);
        }
    }
    
    // 调整以root为根的子树为最大堆
    private static void heapify(int[] arr, int n, int root) {
        int largest = root;      // 初始化最大值为根节点
        int left = 2 * root + 1; // 左子节点
        int right = 2 * root + 2; // 右子节点
        
        // 如果左子节点大于根节点
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        // 如果右子节点大于当前最大值
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        // 如果最大值不是根节点
        if (largest != root) {
            // 交换根节点和最大值
            int swap = arr[root];
            arr[root] = arr[largest];
            arr[largest] = swap;
            
            // 递归调整被影响的子树
            heapify(arr, n, largest);
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
        int[] arr = {12, 11, 13, 5, 6, 7};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        heapSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
