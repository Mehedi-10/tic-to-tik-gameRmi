import java.rmi.Naming;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            String url = "rmi://localhost/TicTacToeGame";
            TicTacToeGame game = (TicTacToeGame) Naming.lookup(url);
            Scanner scanner = new Scanner(System.in);

            String currentPlayer = "X";
            String winner = null;

            while (winner == null) {
                System.out.println("Current board:");
                System.out.println(game.getBoard());

                int row, col;
                do {
                    System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]):");
                    row = scanner.nextInt() - 1; // 1-based to 0-based indexing
                    col = scanner.nextInt() - 1;
                } while (!game.play(row, col, currentPlayer));

                winner = game.checkWinner();
                currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Switch player
            }

            System.out.println("Final board:");
            System.out.println(game.getBoard());

            if (winner.equals("Tie")) {
                System.out.println("The game is a tie!");
            } else {
                System.out.println("Player " + winner + " wins!");
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
