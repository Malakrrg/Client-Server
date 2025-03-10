package rmi.xo;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            TicTacToeServer server = new TicTacToeServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("TicTacToe", server);
            System.out.println("Serveur Tic-Tac-Toe prêt !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}