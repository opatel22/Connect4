# Connect 4 Game (GUI Verson)

This program implements a graphical user interface (GUI) version of the classic game Connect 4 using Java Swing. Players take turns dropping colored discs into a grid, aiming to form a line of four discs horizontally, vertically, or diagonally.

## Instructions
- Run the program by executing the `Connect4GUI` class.
- Upon launch, a window titled "Connect 4" opens, displaying the game board.
- Players click on the columns to drop their colored discs.
- The game alternates between Player 1 (X, red) and Player 2 (O, yellow).
- The game checks for win conditions and declares a winner or a draw accordingly.
- Invalid moves are handled, and players are notified of errors.

## Features
- Graphical user interface using Java Swing.
- Dynamic board with interactive buttons for gameplay.
- Win condition checks for horizontal, vertical, and diagonal connections.
- Error handling for invalid moves and full columns.
- Reset functionality for starting a new game.

## How to Play
- Click on a column to drop your disc.
- Form a line of four discs of your color to win.
- The game ends in a draw if the board is full without a winner.

Enjoy playing Connect 4!

# ______________________________________________________________________________________________________________________________________

# Connect 4 Game (Non GUI Verson)

This program implements a text-based version of the classic game Connect 4 in Java.

## Overview
The `Connect4` class represents the game. It includes methods for initializing the board, printing the board, dropping a piece, checking for a win, checking if the board is full, and playing the game.

## Instructions
1. Run the program by executing the `Connect4` class.
2. The game starts with a 6x7 grid board displayed in the console.
3. Players take turns entering the column number (0-6) where they want to drop their piece.
4. The game alternates between Player 1 (X) and Player 2 (O).
5. The game ends when one player achieves four consecutive pieces horizontally, vertically, or diagonally, or when the board is full without a winner.

## Features
- Text-based interface for gameplay.
- Dynamic board displayed in the console.
- Win condition checks for horizontal, vertical, and diagonal connections.
- Error handling for invalid column numbers and full columns.
- Continues until a player wins or the board is full.

## How to Play
1. Enter the column number (0-6) where you want to drop your piece.
2. Aim to connect four of your pieces horizontally, vertically, or diagonally.
3. The game declares the winner when a player achieves four in a row or announces a draw when the board is full.

Enjoy playing Connect 4!
