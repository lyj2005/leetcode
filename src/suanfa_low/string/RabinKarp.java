package suanfa_low.string;

public class RabinKarp {
    private final static int PRIME = 101; // 哈希计算使用的质数
    
    public static int search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();
        
        if (m > n) return -1;
        if (m == 0) return 0;
        
        // 计算哈希乘数，等于d^(m-1) % PRIME，用于滚动哈希计算
        int h = 1;
        for (int i = 0; i < m - 1; i++) {
            h = (h * 256) % PRIME;
        }
        
        // 计算模式串和第一个窗口的哈希值
        int patternHash = 0;
        int textHash = 0;
        for (int i = 0; i < m; i++) {
            patternHash = (256 * patternHash + pattern.charAt(i)) % PRIME;
            textHash = (256 * textHash + text.charAt(i)) % PRIME;
        }
        
        // 滑动窗口，比较哈希值
        for (int i = 0; i <= n - m; i++) {
            // 哈希值相等时，检查是否真正匹配
            if (patternHash == textHash) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return i; // 找到匹配
                }
            }
            
            // 计算下一个窗口的哈希值
            if (i < n - m) {
                textHash = (256 * (textHash - text.charAt(i) * h) + text.charAt(i + m)) % PRIME;
                // 处理负数哈希值
                if (textHash < 0) {
                    textHash += PRIME;
                }
            }
        }
        
        return -1; // 未找到匹配
    }
    
    // 打印结果
    public static void main(String[] args) {
        String text = "ABABCABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        
        int position = search(text, pattern);
        if (position == -1) {
            System.out.println("未找到匹配");
        } else {
            System.out.println("模式串在位置 " + position + " 处匹配");
            System.out.println(text);
            // 打印指示匹配位置的指针
            for (int i = 0; i < position; i++) {
                System.out.print(" ");
            }
            System.out.println(pattern);
        }
    }
}
