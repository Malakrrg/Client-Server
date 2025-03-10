package rmi.xo;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class TicTacToeClient {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            TicTacToeInterface game = (TicTacToeInterface) registry.lookup("TicTacToe");

            Scanner scanner = new Scanner(System.in);
            char player = 'X';
            
            System.out.println(ANSI_CYAN + "\n  ████████╗██╗ ██████╗    ████████╗ █████╗  ██████╗    ████████╗ ██████╗ ███████╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "  ╚══██╔══╝██║██╔════╝    ╚══██╔══╝██╔══██╗██╔════╝    ╚══██╔══╝██╔═══██╗██╔════╝" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "     ██║   ██║██║            ██║   ███████║██║            ██║   ██║   ██║█████╗  " + ANSI_RESET);
            System.out.println(ANSI_CYAN + "     ██║   ██║██║            ██║   ██╔══██║██║            ██║   ██║   ██║██╔══╝  " + ANSI_RESET);
            System.out.println(ANSI_CYAN + "     ██║   ██║╚██████╗       ██║   ██║  ██║╚██████╗       ██║   ╚██████╔╝███████╗" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "     ╚═╝   ╚═╝ ╚═════╝       ╚═╝   ╚═╝  ╚═╝ ╚═════╝       ╚═╝    ╚═════╝ ╚══════╝" + ANSI_RESET);

            while (!game.isGameOver()) {
                printBoard(game.getBoard());
                
                System.out.println(ANSI_YELLOW + "\n\nJoueur " + colorizePlayer(player) + ANSI_YELLOW + ", c'est votre tour !" + ANSI_RESET);
                System.out.print("Entrez la ligne (0-2) et la colonne (0-2) séparées par un espace : ");

                try {
                    int row = scanner.nextInt();
                    int col = scanner.nextInt();

                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println(ANSI_RED + "Position invalide ! Utilisez des valeurs entre 0 et 2." + ANSI_RESET);
                        continue;
                    }

                    if (game.play(row, col, player)) {
                        player = (player == 'X') ? 'O' : 'X';
                    } else {
                        System.out.println(ANSI_RED + "Case déjà occupée ! Réessayez." + ANSI_RESET);
                    }
                } catch (Exception e) {
                    System.out.println(ANSI_RED + "Entrée invalide ! Utilisez des nombres entiers." + ANSI_RESET);
                    scanner.nextLine(); // Clear buffer
                }
            }

            printBoard(game.getBoard());
            displayWinner(game.getWinner());
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printBoard(char[][] board) {
        System.out.println(ANSI_BLUE + "\n\n    0   1   2" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "  ╔═══╦═══╦═══╗" + ANSI_RESET);
        for (int i = 0; i < 3; i++) {
            System.out.print(ANSI_BLUE + i + " " + ANSI_RESET);
            for (int j = 0; j < 3; j++) {
                System.out.print(ANSI_BLUE + "║ " + ANSI_RESET + colorizePlayer(board[i][j]) + ANSI_BLUE + " ");
            }
            System.out.println("║");
            if (i < 2) System.out.println(ANSI_BLUE + "  ╠═══╬═══╬═══╣" + ANSI_RESET);
        }
        System.out.println(ANSI_BLUE + "  ╚═══╩═══╩═══╝" + ANSI_RESET);
    }

    private static String colorizePlayer(char player) {
        if (player == 'X') return ANSI_RED + "X" + ANSI_RESET;
        if (player == 'O') return ANSI_BLUE + "O" + ANSI_RESET;
        return " ";
    }

    private static void displayWinner(char winner) {
        if (winner == ' ') {
            System.out.println(ANSI_YELLOW + "\n\nMatch nul ! Aucun gagnant." + ANSI_RESET);
        } else {
            System.out.println(ANSI_CYAN + "\n\n⭐⭐⭐ Félicitations Joueur " + 
                              colorizePlayer(winner) + 
                              ANSI_CYAN + " ! Vous avez gagné ! ⭐⭐⭐" + ANSI_RESET);
        }
    }
}