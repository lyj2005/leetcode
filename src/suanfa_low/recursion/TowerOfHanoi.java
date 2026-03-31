package suanfa_low.recursion;

public class TowerOfHanoi {
    public static void hanoi(int n, char source, char auxiliary, char target) {
        // 基准情况：只有一个圆盘
        if (n == 1) {
            System.out.println("移动圆盘 1 从 " + source + " 到 " + target);
            return;
        }
        
        // 将n-1个圆盘从源柱移动到辅助柱
        hanoi(n - 1, source, target, auxiliary);
        
        // 将第n个圆盘从源柱移动到目标柱
        System.out.println("移动圆盘 " + n + " 从 " + source + " 到 " + target);
        
        // 将n-1个圆盘从辅助柱移动到目标柱
        hanoi(n - 1, auxiliary, source, target);
    }
    
    public static void main(String[] args) {
        int n = 3; // 圆盘数量
        System.out.println("汉诺塔问题 - " + n + " 个圆盘:");
        hanoi(n, 'A', 'B', 'C');
        System.out.println("完成移动，共需要 " + ((1 << n) - 1) + " 步");
    }
}
