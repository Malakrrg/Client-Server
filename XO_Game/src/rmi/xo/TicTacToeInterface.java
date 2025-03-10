package rmi.xo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeInterface extends Remote {
    boolean play(int row, int col, char player) throws RemoteException;
    char[][] getBoard() throws RemoteException;
    boolean isGameOver() throws RemoteException;
    char getWinner() throws RemoteException;
}