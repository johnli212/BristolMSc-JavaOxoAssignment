class Board {
    private char[][] gameBoard;
    private Boolean gameOver;
    private char player = 'O';
    
    public Board() {
        gameBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = '-';
            }
        }
        gameOver = false;
    }

    public char[][] getBoard() {
        return gameBoard;
    }
    
    public Boolean getGameOver() {
        return gameOver;
    }
    
    public char getPlayer() {
        return player;
    }
    
    public void playerTurn(String move) {
        int[] moveXY;
        
        Position.checkString(move);
        moveXY = Position.returnXY(move);
        
        if (checkBoardSpace(moveXY) == true) return;
        
        boardMove(moveXY);
        if (checkWin() == true) {
            gameOver = true;
            return;
        }
        switchPlayers();
    }
    
    private Boolean checkBoardSpace(int[] moveXY) {
        if (gameBoard[moveXY[0]][moveXY[1]] == '-') {
            return false;
        }
        return true;
    }
    
    private void boardMove(int[] moveXY) {
        if (player == 'O') {
            gameBoard[moveXY[0]][moveXY[1]] = 'O';
        }
        else {
            gameBoard[moveXY[0]][moveXY[1]] = 'X';
        }
    }
    
    // Switch the active player token
    private void switchPlayers() {
        if (player == 'O') player = 'X';
        else               player = 'O';
    }
    
    // Check all the rows, columns and diagonals to see if a player has won
    private Boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (compareCells(gameBoard[i][0],gameBoard[i][1],gameBoard[i][2]) == true) return true;
            if (compareCells(gameBoard[0][i],gameBoard[1][i],gameBoard[2][i]) == true) return true;
        }
        if (compareCells(gameBoard[0][0],gameBoard[1][1],gameBoard[2][2]) == true) return true;
        if (compareCells(gameBoard[0][2],gameBoard[1][1],gameBoard[2][0]) == true) return true;
        
        return false;
    }
    
    // Compare three cells to check if they are all the same
    private Boolean compareCells(char cell1, char cell2, char cell3) {
        if (cell1 == '-') return false;
        else if (cell1 == cell2 && cell1 == cell3) return true;
        else return false;
    }
}