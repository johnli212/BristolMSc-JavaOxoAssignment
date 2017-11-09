import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

// Noughts & Crosses game, displayed as a javaFX application
public class Oxo extends Application {
    
    private Board board;
    private Scene scene;
    private Pane window;
    private Label[][] tiles;
    private Label currentPlayer;
    private Label cursorPiece;
    
    public static void main (String[] args) {
        launch();
    }
    
    // Set up the javaFX window and initialise the board and javaFX objects
    @Override
    public void start(Stage stage) {
        board = new Board();
        window = new Pane();
        scene = new Scene(window, 300, 370);
        scene.getStylesheets().add("/stylesheet.css");
        scene.setCursor(Cursor.CLOSED_HAND);
        stage.setTitle("Noughts & Crosses");
        stage.setResizable(false);
        stage.sizeToScene();
        
        drawAllElements();
        addMouseEvents();
        
        stage.setScene(scene);
        stage.show();
    }
    
    // draw all of the javaFX elements to the window
    private void drawAllElements() {
        drawUILabels();
        drawBoardTiles();
        drawGridLines();
        drawCursorPiece();
    }
    
    // Add the current player's piece (will follow the cursor)
    private void drawCursorPiece() {
        cursorPiece = new Label(board.getPlayer());
        cursorPiece.setId("board-tile");
        cursorPiece.setStyle("-fx-background-color: Transparent");
        window.getChildren().addAll(cursorPiece);
    }
    
    // Add the labels for the UI at the bottom of the window
    private void drawUILabels() {
        Label currentTurn = new Label("Current Turn:");
        currentPlayer = new Label("O");
        
        currentTurn.setLayoutX(40);
        currentTurn.setLayoutY(320);
        currentTurn.setId("ui-label");
        currentPlayer.setLayoutX(185);
        currentPlayer.setLayoutY(310);
        currentPlayer.setMinWidth(75);
        currentPlayer.setId("ui-player");
        
        window.getChildren().addAll(currentTurn, currentPlayer);
    }
   
    // Add the board tiles as javafx labels, stored in a 3x3 array
    private void drawBoardTiles() {
        GridPane gridpane = new GridPane();
        tiles = new Label[3][3];
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = new Label(" ");
                tiles[i][j].setPrefSize(100, 100);
                tiles[i][j].setId("board-tile");
                gridpane.add(tiles[i][j], i, j);
            }
        }
        window.getChildren().addAll(gridpane);
    }
    
    // Draw the gridlines for noughts & crosses board
    private void drawGridLines() {
        Line[][] gridLines = new Line[2][2];
        
        for (int i = 0; i < 2; i++) {
            float coords = 100 * (i + 1);
            gridLines[0][i] = new Line(coords, 0, coords, 300-2);
            gridLines[1][i] = new Line(0, coords, 300, coords);
            gridLines[0][i].setId("grid-line");
            gridLines[1][i].setId("grid-line");
            
            window.getChildren().addAll(gridLines[0][i], gridLines[1][i]);
        }
    }
    
    // Add the mouse events for the mouse click (player turn) and mouse move (player cursor)
    private void addMouseEvents() {
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (board.getGameState() == State.INPROGRESS) {
                    mouseClick(mouseEvent.getSceneX(), mouseEvent.getSceneY());
                }
            }
        });
        
        scene.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cursorPiece.setLayoutX(mouseEvent.getSceneX() - 19);
                cursorPiece.setLayoutY(mouseEvent.getSceneY() - 40);
            }
        });
    }
    
    // Function runs on mouse click event. If the click is on an empty game
    // tile then the player's piece is placed. the 
    private void mouseClick(double mouseX, double mouseY) {
        Position clickPos = new Position(mouseX, mouseY);
        int x = clickPos.getGridX();
        int y = clickPos.getGridY();
        
        if (board.tryMove(clickPos) == true){
            tiles[x][y].setText(board.getPlayer());
            
            if (board.getGameState() != State.INPROGRESS) {
                if (board.getGameState() == State.WIN) {
                    updateGameEnd(true);
                    gameEnd("Player " + board.getPlayer() + " wins the game!");
                }
                else {
                    updateGameEnd(false);
                    gameEnd("This game is a draw!");
                }
                scene.setCursor(Cursor.DEFAULT);
                return;
            }
            
            board.switchPlayers();
            currentPlayer.setText(board.getPlayer());
            cursorPiece.setText(board.getPlayer());
        }
    }
    
    // Update the colour of board tiles to show a green line in a player has 
    // won, or turn the board red if the game is a draw
    private void updateGameEnd(Boolean win) {
        int[][] winLine = board.getWinLine();
        
        for(int i = 0; i < 3; i++) {
            if (win == true) {
                tiles[winLine[i][0]][winLine[i][1]]
                    .setStyle("-fx-background-color: ForestGreen; -fx-text-fill: White;");
            }
            else {
                for(int j = 0; j < 3; j++) {
                    tiles[i][j]
                        .setStyle("-fx-background-color: DarkRed; -fx-text-fill: White;");
                }
            }
        }
        cursorPiece.setText("");
    }

    // Popup window to alert the player when game has finished
    private void gameEnd(String alertText) {
        Alert alert = new Alert(
            AlertType.NONE, 
            alertText,
            ButtonType.OK
        );
        alert.showAndWait();
    }
}