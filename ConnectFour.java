public class ConnectFour {
    private final int rows = 6;
    private final int cols = 7;
    private final int[][] board = new int[rows][cols];

    public boolean dropPiece(int col, int player) {
        if (col < 0 || col >= cols) {
            return false;
        }
        for (int row = rows - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    public int checkWinner() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int player = board[row][col];
                if (player != 0) {
                    if (col + 3 < cols &&
                            player == board[row][col + 1] &&
                            player == board[row][col + 2] &&
                            player == board[row][col + 3]) {
                        return player;
                    }
                    if (row + 3 < rows &&
                            player == board[row + 1][col] &&
                            player == board[row + 2][col] &&
                            player == board[row + 3][col]) {
                        return player;
                    }
                    if (row + 3 < rows && col + 3 < cols &&
                            player == board[row + 1][col + 1] &&
                            player == board[row + 2][col + 2] &&
                            player == board[row + 3][col + 3]) {
                        return player;
                    }
                    if (row - 3 >= 0 && col + 3 < cols &&
                            player == board[row - 1][col + 1] &&
                            player == board[row - 2][col + 2] &&
                            player == board[row - 3][col + 3]) {
                        return player;
                    }
                }
            }
        }
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == 0) {
                return 0;
            }
        }
        return -1;
    }

    public int[][] getBoard() {
        return board;
    }
}
