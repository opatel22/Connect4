
import java.util.Scanner;

public class Connect4 {
    private char[][] board;
    private boolean player1Turn;

    public Connect4() {
        board = new char[6][7]; // 6 rows, 7 columns
        initializeBoard();
        player1Turn = true;
    }

    public void initializeBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                board[row][col] = '-';
            }
        }
    }

    public void printBoard() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }

    public boolean dropPiece(int col) {
        if (col < 0 || col >= 7) {
            System.out.println("Invalid column number!");
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (board[row][col] == '-') {
                board[row][col] = (player1Turn) ? 'X' : 'O';
                return true;
            }
        }
        System.out.println(" /|-------------------------------|\\");
        System.out.println("< |<<<<<<< Column is full! >>>>>>>| >");
        System.out.println(" \\|-------------------------------|/");
        return false;
    }

    public boolean checkWin(char symbol) {
        // Check horizontal
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol && board[row][col+1] == symbol && board[row][col+2] == symbol && board[row][col+3] == symbol) {
                    return true;
                }
            }
        }

        // Check vertical
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == symbol && board[row+1][col] == symbol && board[row+2][col] == symbol && board[row+3][col] == symbol) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol && board[row+1][col+1] == symbol && board[row+2][col+2] == symbol && board[row+3][col+3] == symbol) {
                    return true;
                }
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (board[row][col] == symbol && board[row+1][col-1] == symbol && board[row+2][col-2] == symbol && board[row+3][col-3] == symbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoardFull() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;

        while (!gameEnd) {
            printBoard();

            System.out.print("Player " + (player1Turn ? "1 (X)" : "2 (O)") + "'s turn. Enter column (0-6): ");
            int col = scanner.nextInt();

            if (dropPiece(col)) {
                if (checkWin(player1Turn ? 'X' : 'O')) {
                    printBoard();
                    System.out.println("Player " + (player1Turn ? "1 (X)" : "2 (O)") + " wins!");
                    gameEnd = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    gameEnd = true;
                } else {
                    player1Turn = !player1Turn;
                }
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {

        System.out.println("|_______________________________|");
        System.out.println("|     The column number starts  |\n| \tat 0 and ends at 6\t|");
        System.out.println("| \t  - - - - - - -\t\t|");
        System.out.println("| \t  0 1 2 3 4 5 6\t\t|");
        System.out.println("| \tLets get started!\t|");
        System.out.println("|_______________________________|\n\n");
        Connect4 game = new Connect4();
        game.playGame();
    }
}
