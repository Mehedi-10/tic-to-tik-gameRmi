import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // RMI registry on port 1099
            TicTacToeGame game = new TicTacToeGameImpl();
            Naming.rebind("TicTacToeGame", game);
            System.out.println("Tic-Tac-Toe Game Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
