package suanfa_high.backtracking;

public class SudokuSolver {
    public static boolean solveSudoku(int[][] board) {
        int N = board.length;
        
        // 寻找一个空位置
        int row = -1;
        int col = -1;
        boolean isEmpty = false;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }
        
        // 没有空位置，说明数独已解决
        if (!isEmpty) {
            return true;
        }
        
        // 尝试在空位置填入1-9
        for (int num = 1; num <= 9; num++) {
            if (isSafe(board, row, col, num)) {
                // 尝试放置数字
                board[row][col] = num;
                
                // 递归解决剩余部分
                if (solveSudoku(board)) {
                    return true;
                }
                
                // 如果当前数字不能解决问题，回溯
                board[row][col] = 0;
            }
        }
        
        // 尝试所有数字都失败，返回false触发回溯
        return false;
    }
    
    // 检查在指定位置放置数字是否安全
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // 检查行是否有重复
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        
        // 检查列是否有重复
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        
        // 检查3x3子网格是否有重复
        int sqrt = (int) Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;
        
        for (int i = boxRowStart; i < boxRowStart + sqrt; i++) {
            for (int j = boxColStart; j < boxColStart + sqrt; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        
        // 所有检查都通过，放置安全
        return true;
    }
    
    // 打印数独
    public static void printSudoku(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // 测试
    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        
        System.out.println("数独题目：");
        printSudoku(board);
        
        if (solveSudoku(board)) {
            System.out.println(" 数独解答：");
            printSudoku(board);
        } else {
            System.out.println(" 没有解答！");
        }
    }
}
