import java.util.*;

class Display {
    
    private String p1Name;
    private String p2Name;
    private Scanner sc = new Scanner(System.in);
    private char[][] displayBoard = {
        {' ', '1','2','3'},
        {'A', '-','-','-'},
        {'B', '-','-','-'},
        {'C', '-','-','-'}
    };
    
    public void gameStart() {
        
        System.out.println("Welcome to Noughts & Crosses!");
        System.out.print("Enter the names of players 1 & 2: ");
        
        try {
            String input = sc.nextLine();
            String[] names = input.split(" ");
            p1Name = names[0];
            p2Name = names[1];
        }
        catch (Exception e) { throw new Error(e); }
        
        System.out.println("Player 1: " + p1Name + "\nPlayer 2: " + p2Name);
    }
    
    
    public void displayBoard(char[][] currBoard) {
        copyBoard(currBoard);
        printBoard();
    }
    
    private void copyBoard(char[][] currBoard) {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                displayBoard[i][j] = currBoard[i - 1][j - 1];
            }
        }
    }
    
    private void printBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" " + displayBoard[i][j]);
            }
            System.out.println("");
        }
    }
    
    public String getMove() {
        System.out.print("Player X choose your move: ");
        String move = sc.next();
        
        return move;
    }
    
    public void gameEnd(char player) {
        System.out.println("Player " + player + " has won the game!");
    }
    /*
    String scanToken() {
        String token
        Scanner sc = new Scanner(System.in);
        
        return sc.next();
    }*/
}