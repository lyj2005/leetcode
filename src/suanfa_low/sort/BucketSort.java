package suanfa_low.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void bucketSort(double[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        
        // 确定桶的数量
        int bucketCount = arr.length;
        
        // 创建桶
        List<List<Double>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }
        
        // 找出数组中的最大值和最小值
        double max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        
        // 计算每个桶的范围大小
        double range = (max - min) / bucketCount;
        
        // 将元素分配到对应的桶中
        for (double item : arr) {
            // 计算元素应该放入哪个桶
            int bucketIndex = (int)((item - min) / range);
            
            // 处理最大值的边界情况
            if (bucketIndex == bucketCount) {
                bucketIndex--;
            }
            
            buckets.get(bucketIndex).add(item);
        }
        
        // 对每个桶中的元素进行排序
        for (List<Double> bucket : buckets) {
            Collections.sort(bucket);
        }
        
        // 将桶中排序好的元素放回原数组
        int index = 0;
        for (List<Double> bucket : buckets) {
            for (double item : bucket) {
                arr[index++] = item;
            }
        }
    }
    
    // 打印数组
    public static void printArray(double[] arr) {
        for (double item : arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    
    // 测试
    public static void main(String[] args) {
        double[] arr = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51};
        System.out.println("排序前的数组：");
        printArray(arr);
        
        bucketSort(arr);
        
        System.out.println("排序后的数组：");
        printArray(arr);
    }
}
