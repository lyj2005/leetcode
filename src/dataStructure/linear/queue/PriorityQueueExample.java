package dataStructure.linear.queue;

import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // 创建最小堆（默认）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        // 添加元素
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(20);
        
        System.out.println("优先队列: " + minHeap); // 注意：打印顺序可能不是实际的优先级顺序
        
        // 查看最高优先级元素（最小值）
        int highestPriority = minHeap.peek();
        System.out.println("最高优先级元素: " + highestPriority); // 输出: 10
        
        // 移除最高优先级元素
        int removed = minHeap.poll();
        System.out.println("移除的元素: " + removed); // 输出: 10
        
        // 创建最大堆（通过比较器）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(20);
        
        System.out.println("最大堆顶部元素: " + maxHeap.peek()); // 输出: 30
    }
}
