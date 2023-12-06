import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TicTacToeGameImpl extends UnicastRemoteObject implements TicTacToeGame {

    private String[][] board = new String[3][3];

    protected TicTacToeGameImpl() throws RemoteException {
        super();
        // Initialize the game board with empty values
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = "-";
            }
        }
    }

    @Override
    public String getBoard() throws RemoteException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean play(int row, int col, String player) throws RemoteException {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col].equals("-")) {
            board[row][col] = player;
            return true;
        }
        return false;
    }

    @Override
    public String checkWinner() throws RemoteException {
        // Check rows, columns and diagonals
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("-") && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (!board[0][i].equals("-") && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
        }

        if (!board[0][0].equals("-") && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
            return board[0][0];
        }
        if (!board[0][2].equals("-") && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
            return board[0][2];
        }

        // Check for a tie
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals("-")) {
                    return null; // Game is still ongoing
                }
            }
        }

        return "Tie"; // No winner and no empty spaces
    }
}
