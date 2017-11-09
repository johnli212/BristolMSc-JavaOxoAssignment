class Position {
    
    public static Boolean checkString(String move) {
        String[] test = move.split(" ");
        
        if (test.length != 1) {
            return false;
        }
        else if (move.length() > 2) {
            return false;
        }
        return true;
    }
    
    public static int[] returnXY(String move) {
        int[] coords = {0, 0};
        move = move.toUpperCase();
        coords[0] = move.charAt(0) - 'A';
        coords[1] = Character.getNumericValue(move.charAt(1)) - 1;
        
        return coords;
    }

}