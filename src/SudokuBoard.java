import java.util.Random;

public class SudokuBoard {
    private int[][] board;
    private int difficulty;

    public SudokuBoard(int difficulty) {
        this.difficulty = difficulty;
        generateSolvableBoard();
    }

    private void generateSolvableBoard() {
        board = new int[9][9];
        fillDiagonalBoxes();
        fillRemaining(0, 3);
        removeKDigits();
    }

    private void fillDiagonalBoxes() {
        for (int i = 0; i < 9; i += 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = new Random().nextInt(9) + 1;
                } while (!unUsedInBox(row, col, num) || !unUsedInRow(row + i, num) || !unUsedInCol(col + j, num));
                board[row + i][col + j] = num;
            }
        }
    }

    private boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowStart + i][colStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean unUsedInRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean unUsedInCol(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean fillRemaining(int i, int j) {
        if (j >= 9 && i < 8) {
            i = i + 1;
            j = 0;
        }
        if (i >= 9 && j >= 9) {
            return true;
        }
        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < 6) {
            if (j == (int) (i / 3) * 3) {
                j = j + 3;
            }
        } else {
            if (j == 6) {
                i = i + 1;
                j = 0;
                if (i >= 9) {
                    return true;
                }
            }
        }
        for (int num = 1; num <= 9; num++) {
            if (checkIfSafe(i, j, num)) {
                board[i][j] = num;
                if (fillRemaining(i, j + 1)) {
                    return true;
                }
                board[i][j] = 0;
            }
        }
        return false;
    }

    private boolean checkIfSafe(int i, int j, int num) {
        return unUsedInRow(i, num) && unUsedInCol(j, num) && unUsedInBox(i - i % 3, j - j % 3, num);
    }

    private void removeKDigits() {
        int count = difficulty == 0 ? 30 : difficulty == 1 ? 40 : 50;
        while (count > 0) {
            int cellId = new Random().nextInt(81);
            int i = cellId / 9;
            int j = cellId % 9;
            if (board[i][j] != 0) {
                count--;
                board[i][j] = 0;
            }
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public static boolean isSolved(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0 || !isValid(board, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        return isValidRow(board, row, num) && isValidCol(board, col, num) && isValidBox(board, row - row % 3, col - col % 3, num);
    }

    private static boolean isValidRow(int[][] board, int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidCol(int[][] board, int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (board[row][col] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidBox(int[][] board, int rowStart, int colStart, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowStart + i][colStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}