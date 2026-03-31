package suanfa_low.sort;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int n = arr.length;
        
        // 初始步长为n/2，每次减半
        for (int gap = n/2; gap > 0; gap /= 2) {
            // 对每个步长进行插入排序
            for (int i = gap; i < n; i++) {
                // 保存当前元素
                int temp = arr[i];
                int j;
                
                // 对同一组的元素进行插入排序
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                
                // 将temp放到正确位置
                arr[j] = temp;
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
        int[] arr = {12, 34, 54, 2, 3, 1, 23, 45, 19, 92};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        shellSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
