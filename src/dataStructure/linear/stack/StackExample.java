package dataStructure.linear.stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        // 创建栈
        Stack<Integer> stack = new Stack<>();
        
        // 入栈
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println("栈: " + stack); // 输出: [10, 20, 30]
        
        // 查看栈顶元素（不移除）
        int topElement = stack.peek();
        System.out.println("栈顶元素: " + topElement); // 输出: 30
        
        // 出栈
        int poppedElement = stack.pop();
        System.out.println("弹出的元素: " + poppedElement); // 输出: 30
        
        // 检查栈是否为空
        boolean isEmpty = stack.isEmpty();
        System.out.println("栈是否为空: " + isEmpty); // 输出: false
        
        // 获取栈大小
        int size = stack.size();
        System.out.println("栈大小: " + size); // 输出: 2
        
        System.out.println("最终栈: " + stack); // 输出: [10, 20]
    }
}
