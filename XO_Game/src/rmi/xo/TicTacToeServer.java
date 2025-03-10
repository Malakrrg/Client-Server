package rmi.xo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicTacToeServer extends UnicastRemoteObject implements TicTacToeInterface {
    private char[][] board = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
    private char winner = ' ';
    private boolean gameOver = false;

    protected TicTacToeServer() throws RemoteException {
        super();
    }

    public synchronized boolean play(int row, int col, char player) throws RemoteException {
        // Ensure the game is not over and the spot is empty
        if (gameOver || board[row][col] != ' ') return false;
        board[row][col] = player;
        checkWinner();
        return true;
    }

    
    public char[][] getBoard() throws RemoteException {
        return board;
    }

    public boolean isGameOver() throws RemoteException {
        return gameOver;
    }

    public char getWinner() throws RemoteException {
        return winner;
    }
    private void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                winner = board[i][0];
                gameOver = true;
                return;
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winner = board[0][i];
                gameOver = true;
                return;
            }
        }
        // Check diagonals for a winner
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            winner = board[0][0];
            gameOver = true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            winner = board[0][2];
            gameOver = true;
        }
    }
}