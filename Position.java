// Converts mouse X and Y values into board array values (0-2 x, 0-2 y).
// The board array values can be retrieved using the getters gridX() & gridY().
class Position {
    private final int gridX;
    private final int gridY;
    
    // Main function for use only when testing Position by itself
    public static void main(String[] args) {
        Position testClass = new Position(0, 0);
        
        boolean testing = false;
        assert(testing = true);
        if (testing) testClass.test();
    }
    
    Position(double mouseX, double mouseY) {
        boolean testing = false;
        assert(testing = true);
        if (testing) test();
        
        gridX = calcBoardTile(mouseX);
        gridY = calcBoardTile(mouseY);
    }
    
    public int getGridX() {
        return gridX;
    }
    
    public int getGridY() {
        return gridY;
    }
    
    // Given a mouse x or y coordinate, return the array index of the
    // corresponding board tile (-1 if invalid)
    private int calcBoardTile(double coord) {
        if (coord < 100) {
            return 0;
        }
        else if (coord < 200) {
            return 1;
        }
        else if (coord < 300) {
            return 2;
        }
        else return -1;
    }
    
    private void test() {
        // tests on calcBoardTile()
        assert(calcBoardTile(0.0) == 0);
        assert(calcBoardTile(99.0) == 0);
        assert(calcBoardTile(100.0) == 1);
        assert(calcBoardTile(199.0) == 1);
        assert(calcBoardTile(200.0) == 2);
        assert(calcBoardTile(299.0) == 2);
        assert(calcBoardTile(300.0) == -1);
        assert(calcBoardTile(500.0) == -1);
    }
}