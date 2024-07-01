public class Solution {
    // Method to check if placing a number is safe
    public boolean isSafe(char[][] board, int row, int col, int number) {
        char numChar = (char)(number + '0');
        for(int i = 0; i < board.length; i++) {
            if(board[i][col] == numChar || board[row][i] == numChar) {
                return false;
            }
        }
        
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for(int i = startRow; i < startRow + 3; i++) {
            for(int j = startCol; j < startCol + 3; j++) {
                if(board[i][j] == numChar) {
                    return false;
                }
            }
        }
        return true;
    }

    // Helper method for recursion
    public boolean helper(char[][] board, int row, int col) {
        if(row == board.length) {
            return true; // Solved entire board
        }

        int nrow = row, ncol = col + 1;
        if(col == board.length - 1) {
            nrow = row + 1;
            ncol = 0;
        }

        if(board[row][col] != '.') {
            return helper(board, nrow, ncol);
        } else {
            for(int i = 1; i <= 9; i++) {
                if(isSafe(board, row, col, i)) {
                    board[row][col] = (char)(i + '0');
                    if(helper(board, nrow, ncol)) {
                        return true;
                    }
                    board[row][col] = '.';
                }
            }
        }
        return false; // Trigger backtracking
    }

    // Main method to solve Sudoku
    public void solveSudoku(char[][] board) {
        helper(board, 0, 0);
    }
}
