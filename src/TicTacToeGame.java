import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TicTacToeGame extends Remote {
    String getBoard() throws RemoteException;
    boolean play(int row, int col, String player) throws RemoteException;
    String checkWinner() throws RemoteException;
}
