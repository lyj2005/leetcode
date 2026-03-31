package suanfa_low.string;

import java.util.ArrayList;
import java.util.List;

public class KMP {
    // 构建部分匹配表（next数组）
    private static int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m];
        next[0] = 0; // 第一个字符的最长相等前后缀长度为0
        
        for (int i = 1, j = 0; i < m; i++) {
            // 当前字符不匹配，回退j
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            
            // 当前字符匹配，j向前移动
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            
            // 记录当前位置的最长相等前后缀长度
            next[i] = j;
        }
        
        return next;
    }
    
    // KMP搜索算法
    public static int kmpSearch(String text, String pattern) {
        if (pattern == null || pattern.length() == 0) {
            return 0;
        }
        
        if (text == null || text.length() < pattern.length()) {
            return -1;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // 构建next数组
        int[] next = buildNext(pattern);
        
        // 进行匹配
        for (int i = 0, j = 0; i < n; i++) {
            // 当前字符不匹配，根据next数组回退j
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            
            // 当前字符匹配，j向前移动
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            
            // 完全匹配，返回起始索引
            if (j == m) {
                return i - m + 1;
            }
        }
        
        return -1; // 未找到匹配
    }
    
    // 查找所有匹配位置
    public static List<Integer> kmpSearchAll(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        if (pattern == null || pattern.length() == 0) {
            return positions;
        }
        
        if (text == null || text.length() < pattern.length()) {
            return positions;
        }
        
        int n = text.length();
        int m = pattern.length();
        
        // 构建next数组
        int[] next = buildNext(pattern);
        
        // 进行匹配
        for (int i = 0, j = 0; i < n; i++) {
            // 当前字符不匹配，回退j
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            
            // 当前字符匹配，j向前移动
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            
            // 完全匹配，记录位置并继续匹配
            if (j == m) {
                positions.add(i - m + 1);
                // 回退j以寻找下一个匹配
                j = next[j - 1];
            }
        }
        
        return positions;
    }
    
    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        
        int pos = kmpSearch(text, pattern);
        List<Integer> allPos = kmpSearchAll(text, pattern);
        
        System.out.println("文本: " + text);
        System.out.println("模式: " + pattern);
        System.out.println("首次匹配位置: " + (pos != -1 ? pos : "未找到"));
        System.out.println("所有匹配位置: " + allPos);
        
        // 打印next数组，帮助理解
        int[] next = buildNext(pattern);
        System.out.print("next数组: ");
        for (int val : next) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
