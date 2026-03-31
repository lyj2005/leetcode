package dataStructure.linear.queue;

//使用Java内置的Queue接口
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        // 创建队列（使用LinkedList实现）
        Queue<Integer> queue = new LinkedList<>();
        
        // 入队
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        
        System.out.println("队列: " + queue); // 输出: [10, 20, 30]
        
        // 查看队头元素（不移除）
        int frontElement = queue.peek();
        System.out.println("队头元素: " + frontElement); // 输出: 10
        
        // 出队
        int dequeuedElement = queue.poll();
        System.out.println("出队的元素: " + dequeuedElement); // 输出: 10
        
        // 检查队列是否为空
        boolean isEmpty = queue.isEmpty();
        System.out.println("队列是否为空: " + isEmpty); // 输出: false
        
        // 获取队列大小
        int size = queue.size();
        System.out.println("队列大小: " + size); // 输出: 2
        
        System.out.println("最终队列: " + queue); // 输出: [20, 30]
    }
}
