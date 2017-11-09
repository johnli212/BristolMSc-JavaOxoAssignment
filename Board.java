// Board class stores the current state of the game, including the board and
// current player. Contains all methods for adding pieces to the board and 
//checking if the game is over.
class Board {
    
    private char[][] gameBoard;
    private State gameState;
    private char player = 'O';
    private int[][] winLine;
    
    // Main function for use only when testing Board by itself
    public static void main(String[] args) {
        Board testClass = new Board();
        
        boolean testing = false;
        assert(testing = true);
        if (testing) testClass.test();
    }
    
    // Run tests if assertions are enabled, initialise the board and game state
    public Board() {
        gameBoard = new char[3][3];
        setupBoard("         ");
        gameState = State.INPROGRESS;
    }
    
    // Get current player as a string
    public String getPlayer() {
        return "" + player;
    }
    
    public State getGameState() {
        return gameState;
    }
    
    // If the game is over, winLine contains the coordinates of the winning three tiles
    public int[][] getWinLine() {
        return winLine;
    }
    
    // Switch the current player
    public void switchPlayers() {
        if (player == 'O') player = 'X';
        else               player = 'O';
    }
    
    // Try the player move, based on the mouse click coordinates. If the move is valid then add
    // the piece to the board and check if the game is over. 
    public Boolean tryMove(Position pos) {
        int x = pos.getGridX();
        int y = pos.getGridY();
        
        if (x == -1 || y == -1 || spaceTaken(x, y) == true) {
            return false;
        }
        else {
            gameBoard[x][y] = player;
            checkBoardFull();
            if (checkWin() == true) gameState = State.WIN;
            return true;
        }
    }
    
    // Return true of the space in the array index x/y has an X or O piece in it already
    private Boolean spaceTaken(int x, int y) {
        if (gameBoard[x][y] == ' ') {
            return false;
        }
        else return true;
    }
    
    // Check all the rows, columns and diagonals to see if a player has won
    private Boolean checkWin() {
        // Check all 3 rows and all 3 columns
        for (int i = 0; i < 3; i++) {
            if (compareCells(gameBoard[i][0],gameBoard[i][1],gameBoard[i][2]) == true) {
                winLine = new int[][] {{i,0},{i,1},{i,2}};
                return true;
            }
            if (compareCells(gameBoard[0][i],gameBoard[1][i],gameBoard[2][i]) == true) {
                winLine = new int[][] {{0,i},{1,i},{2,i}};
                return true;
            } 
        }
        // Check both diagonals
        if (compareCells(gameBoard[0][0],gameBoard[1][1],gameBoard[2][2]) == true) {
            winLine = new int[][] {{0,0},{1,1},{2,2}};
            return true;
        }
        else if (compareCells(gameBoard[0][2],gameBoard[1][1],gameBoard[2][0]) == true) {
            winLine = new int[][] {{0,2},{1,1},{2,0}};
            return true;
        }
        
        return false;
    }
    
    // Compare three cells to check if they are all the same
    private Boolean compareCells(char cell1, char cell2, char cell3) {
        if (cell1 == ' ') return false;
        else if (cell1 == cell2 && cell1 == cell3) return true;
        else return false;
    }
    
    // If the board is full, set the game state to DRAW return true
    private Boolean checkBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == ' ') return false;
            }
        }
        gameState = State.DRAW;
        
        return true;
    }
    
    // Testing for all Board functions, run when assertions are enabled
    private void test() {
        /*gameBoard = new char[3][3];
        setupBoard("         ");*/
        
        // tests on switchPlayers()
        assert(player == 'O');
        switchPlayers();
        assert(player == 'X');
        switchPlayers();
        assert(player == 'O');
        // tests on tryMove()
        Position posTest;
        posTest = new Position(150, 95); assert(tryMove(posTest) == true);
        posTest = new Position(0, 200); assert(tryMove(posTest) == true);
        setupBoard("  X      "); assert(tryMove(posTest) == false);
        posTest = new Position(350, 350); assert(tryMove(posTest) == false);
        // tests on spaceTaken()
        setupBoard("         "); assert(spaceTaken(0, 0) == false);
        setupBoard("XOXOXOXOX"); assert(spaceTaken(2, 1) == true);
        setupBoard("    O    "); assert(spaceTaken(1, 1) == true);
        // tests on checkWin()
        setupBoard("         "); assert(checkWin() == false);
        setupBoard("OXXXOOOXX"); assert(checkWin() == false);
        setupBoard("X   OO X "); assert(checkWin() == false);
        setupBoard("O   O  O "); assert(checkWin() == false);
        setupBoard("OOO      "); assert(checkWin() == true);
        setupBoard("   XXX   "); assert(checkWin() == true);
        setupBoard("      OOO"); assert(checkWin() == true);
        setupBoard("X  X  X  "); assert(checkWin() == true);
        setupBoard(" O  O  O "); assert(checkWin() == true);
        setupBoard("  X  X  X"); assert(checkWin() == true);
        setupBoard("O   O   O"); assert(checkWin() == true);
        setupBoard("  X X X  "); assert(checkWin() == true);
        // tests on compareCells()
        assert(compareCells('X', 'X', 'X') == true);
        assert(compareCells('O', 'O', 'O') == true);
        assert(compareCells('X', 'O', ' ') == false);
        // tests on checkBoardFull()
        setupBoard("         "); assert(checkBoardFull() == false);
        setupBoard("OOOXXXOXO"); assert(checkBoardFull() == true);
        setupBoard("OXOXOXOX "); assert(checkBoardFull() == false);
    }
    
    // Takes a string of 9 characters and sets the game board up using those characters
    private void setupBoard(String boardPieces) {
        assert(boardPieces.length() == 9);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = boardPieces.charAt((i * 3) + j);
            }
        }
    }
}