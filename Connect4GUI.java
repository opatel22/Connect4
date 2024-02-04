
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connect4GUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char[][] board;
    private boolean player1Turn;

    public Connect4GUI() {
        setTitle("Connect 4");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 7));

        buttons = new JButton[6][7];
        board = new char[6][7];
        player1Turn = true;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
                board[row][col] = '-';
            }
        }

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (e.getSource() == buttons[row][col]) {
                    if (dropPiece(col)) {
                        if (checkWin(player1Turn ? 'X' : 'O')) {
                            JOptionPane.showMessageDialog(this, "Player " + (player1Turn ? "1 (X)" : "2 (O)") + " wins!");
                            resetGame();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "It's a draw!");
                            resetGame();
                        } else {
                            player1Turn = !player1Turn;
                        }
                    }
                }
            }
        }
    }

    public boolean dropPiece(int col) {
        if (col < 0 || col >= 7) {
            JOptionPane.showMessageDialog(this, "Invalid column number!");
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (board[row][col] == '-') {
                board[row][col] = (player1Turn) ? 'X' : 'O';
                buttons[row][col].setBackground((player1Turn) ? Color.RED : Color.YELLOW);
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Column is full!");
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

    public void resetGame() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col].setBackground(Color.WHITE);
                board[row][col] = '-';
            }
        }
        player1Turn = true;
    }

    public static void main(String[] args) {
        new Connect4GUI();
    }
}

// GUI version option playing against the computer.
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connect4GUI extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private char[][] board;
    private boolean player1Turn;
    private boolean vsComputer;

    public Connect4GUI() {
        setTitle("Connect 4");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 7));

        buttons = new JButton[6][7];
        board = new char[6][7];
        player1Turn = true;
        vsComputer = false;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].addActionListener(this);
                panel.add(buttons[row][col]);
                board[row][col] = '-';
            }
        }

        add(panel);
        setVisible(true);

        // Dialog to choose opponent
        int choice = JOptionPane.showConfirmDialog(this, "Play against computer?", "Opponent", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            vsComputer = true;
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                if (e.getSource() == buttons[row][col]) {
                    if (dropPiece(col)) {
                        if (checkWin(player1Turn ? 'X' : 'O')) {
                            JOptionPane.showMessageDialog(this, "Player " + (player1Turn ? "1 (X)" : "2 (O)") + " wins!");
                            resetGame();
                        } else if (isBoardFull()) {
                            JOptionPane.showMessageDialog(this, "It's a draw!");
                            resetGame();
                        } else {
                            if (vsComputer && !player1Turn) {
                                computerMove();
                            } else {
                                player1Turn = !player1Turn;
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean dropPiece(int col) {
        if (col < 0 || col >= 7) {
            JOptionPane.showMessageDialog(this, "Invalid column number!");
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (board[row][col] == '-') {
                board[row][col] = (player1Turn) ? 'X' : 'O';
                buttons[row][col].setBackground((player1Turn) ? Color.RED : Color.YELLOW);
                return true;
            }
        }
        JOptionPane.showMessageDialog(this, "Column is full!");
        return false;
    }

    public boolean checkWin(char symbol) {
        // Check horizontal
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol && board[row][col + 1] == symbol &&
                    board[row][col + 2] == symbol && board[row][col + 3] == symbol) {
                    return true;
                }
            }
        }
    
        // Check vertical
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 7; col++) {
                if (board[row][col] == symbol && board[row + 1][col] == symbol &&
                    board[row + 2][col] == symbol && board[row + 3][col] == symbol) {
                    return true;
                }
            }
        }
    
        // Check diagonals
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == symbol && board[row + 1][col + 1] == symbol &&
                    board[row + 2][col + 2] == symbol && board[row + 3][col + 3] == symbol) {
                    return true;
                }
            }
        }
    
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 7; col++) {
                if (board[row][col] == symbol && board[row + 1][col - 1] == symbol &&
                    board[row + 2][col - 2] == symbol && board[row + 3][col - 3] == symbol) {
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
    
    public void resetGame() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col].setBackground(Color.WHITE);
                board[row][col] = '-';
            }
        }
        player1Turn = true;
    }
    
    public void computerMove() {
        int col;
        do {
            col = (int) (Math.random() * 7); // Random column choice
        } while (!isValidMove(col)); // Keep trying until a valid move is made
    
        dropPiece(col); // Drop the piece into the valid column
    }

    public boolean isValidMove(int col) {
        for (int row = 5; row >= 0; row--) {
            if (board[row][col] == '-') {
                return true; // Valid move
            }
        }
        return false; // Column is full
    }
    
    

    public static void main(String[] args) {
        new Connect4GUI();
    }
}
 */