
// Main class for running the Noughts and crosses game
class Oxo {
    
    private Board board;
    private Display display;
    
    public static void main (String[] args) {
        Oxo game = new Oxo();
        game.initialise();
        game.run();
    }
    
    // Initialise the board & display objects
    private void initialise() {
        board = new Board();
        display = new Display();
    }
    
    // Repeat the main game loop turn by turn until the game is finished
    private void run() {
        String move;
        display.gameStart();
        
        while (board.getGameOver() == false) {
            display.displayBoard(board.getBoard());
            move = display.getMove();
            board.playerTurn(move);
        }
        
        display.displayBoard(board.getBoard());
        display.gameEnd(board.getPlayer());
    }
    
}