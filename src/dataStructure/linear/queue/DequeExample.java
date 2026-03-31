package dataStructure.linear.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        // 创建双端队列
        Deque<Integer> deque = new ArrayDeque<>();
        
        // 在前端添加元素
        deque.addFirst(10);
        deque.offerFirst(20);  // 优先使用offer方法，不会抛异常
        
        // 在后端添加元素
        deque.addLast(30);
        deque.offerLast(40);
        
        System.out.println("双端队列: " + deque);  // 输出: [20, 10, 30, 40]
        
        // 查看前端元素（不移除）
        int first = deque.peekFirst();
        System.out.println("前端元素: " + first);  // 输出: 20
        
        // 查看后端元素（不移除）
        int last = deque.peekLast();
        System.out.println("后端元素: " + last);  // 输出: 40
    
    // 从前端移除元素
        int removedFirst = deque.pollFirst();
        System.out.println("从前端移除: " + removedFirst);  // 输出: 20
    
    // 从后端移除元素
        int removedLast = deque.pollLast();
        System.out.println("从后端移除: " + removedLast);  // 输出: 40
        
        System.out.println("最终双端队列: " + deque);  // 输出: [10, 30]
        
        // 使用双端队列作为栈
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(50);  // 等同于addFirst()
        stack.push(60);
        System.out.println("栈顶元素: " + stack.peek());  // 输出: 60
        System.out.println("弹出元素: " + stack.pop());  // 输出: 60
    }
}
