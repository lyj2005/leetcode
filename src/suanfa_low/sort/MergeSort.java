package suanfa_low.sort;

public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // 找出中间点
            int mid = left + (right - left) / 2;
            
            // 递归排序左右两半
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            
            // 合并已排序的两半
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // 计算两个子数组的大小
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        // 创建临时数组
        int[] L = new int[n1];
        int[] R = new int[n2];
        
        // 复制数据到临时数组
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];
        
        // 合并临时数组
        int i = 0, j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        
        // 复制L[]的剩余元素
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        
        // 复制R[]的剩余元素
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
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
        
        mergeSort(arr, 0, arr.length - 1);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
