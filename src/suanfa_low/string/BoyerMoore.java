package suanfa_low.string;

public class BoyerMoore {
    private final int R; // 字符集大小
    private int[] badChar; // 坏字符表
    private int[] goodSuffix; // 好后缀表
    private int[] borderPos; // 边界位置表
    private String pattern; // 模式串
    
    public BoyerMoore(String pattern) {
        this.R = 256; // ASCII字符集
        this.pattern = pattern;
        int m = pattern.length();
        
        // 初始化坏字符表
        badChar = new int[R];
        for (int c = 0; c < R; c++) {
            badChar[c] = -1; // 初始化为-1
        }
        for (int j = 0; j < m; j++) {
            badChar[pattern.charAt(j)] = j; // 记录每个字符最右出现位置
        }
        
        // 初始化好后缀表和边界位置表
        goodSuffix = new int[m];
        borderPos = new int[m];
        processSuffixes();
    }
    
    // 预处理好后缀表
    private void processSuffixes() {
        int m = pattern.length();
        int i = m, j = m + 1;
        borderPos[i] = j;
        
        // 计算边界位置
        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (goodSuffix[j] == 0) {
                    goodSuffix[j] = j - i;
                }
                j = borderPos[j];
            }
            i--; j--;
            borderPos[i] = j;
        }
        
        // 计算好后缀表
        j = borderPos[0];
        for (i = 0; i <= m; i++) {
            if (goodSuffix[i] == 0) {
                goodSuffix[i] = j;
            }
            if (i == j) {
                j = borderPos[j];
            }
        }
    }
    
    // 搜索文本串中的匹配
    public int search(String text) {
        int n = text.length();
        int m = pattern.length();
        if (m == 0) return 0;
        
        int skip;
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            for (int j = m - 1; j >= 0; j--) {
                if (pattern.charAt(j) != text.charAt(i + j)) {
                    // 坏字符规则
                    skip = Math.max(1, j - badChar[text.charAt(i + j)]);
                    // 好后缀规则
                    if (j < m - 1) {
                        skip = Math.max(skip, goodSuffix[j + 1]);
                    }
                    break;
                }
            }
            if (skip == 0) return i; // 找到匹配
        }
        return -1; // 没有找到匹配
    }
    
    // 测试
    public static void main(String[] args) {
        String text = "HERE IS A SIMPLE EXAMPLE";
        String pattern = "EXAMPLE";
        
        BoyerMoore bm = new BoyerMoore(pattern);
        int position = bm.search(text);
        
        if (position == -1) {
            System.out.println("未找到匹配");
        } else {
            System.out.println("模式串在位置 " + position + " 处匹配");
            System.out.println(text);
            for (int i = 0; i < position; i++) {
                System.out.print(" ");
            }
            System.out.println(pattern);
        }
    }
}
