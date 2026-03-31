package suanfa_high.backtracking;

import java.util.*;

public class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        
        // 初始化棋盘
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        // 回溯求解
        backtrack(board, 0, result);
        return result;
    }
    
    private static void backtrack(char[][] board, int row, List<List<String>> result) {
        // 如果已经放置了n个皇后，记录解
        if (row == board.length) {
            result.add(constructSolution(board));
            return;
        }
        
        int n = board.length;
        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 检查是否可以放置
            if (isValid(board, row, col)) {
                // 放置皇后
                board[row][col] = 'Q';
                
                // 递归到下一行
                backtrack(board, row + 1, result);
                
                // 回溯，撤销选择
                board[row][col] = '.';
            }
        }
    }
    
    // 检查在指定位置放置皇后是否有效
    private static boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        
        // 检查同一列
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // 检查左上到右下对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        // 检查右上到左下对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        
        return true;
    }
    
    // 根据棋盘状态构造解决方案
    private static List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }
    
    // 打印棋盘
    private static void printBoard(List<String> board) {
        for (String row : board) {
            System.out.println(row);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int n = 4;
        List<List<String>> solutions = solveNQueens(n);
        
        System.out.println(n + "皇后问题的解：");
        System.out.println("共找到 " + solutions.size() + " 种解法 ");
        
        for (int i = 0; i < solutions.size(); i++) {
            System.out.println("解法 " + (i + 1) + ":");
            printBoard(solutions.get(i));
        }
    }
}
