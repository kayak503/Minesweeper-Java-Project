package minesweeper.view;


import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.model.ButtonClick;
import minesweeper.model.Hint;
import minesweeper.model.Location;
import minesweeper.model.Minesweeper;
import minesweeper.model.MinesweeperObserverImp;
import minesweeper.model.Solve;

public class GUI extends Application{
    private static final int ROWS = 8;
    private static final int COLS = 8;
    private static final int MINES = 10;

    static final Image MINE  = new Image("file:media/images/mine24.png");

    private Minesweeper minesweeper;

    public GUI(){
        this.minesweeper = new Minesweeper(ROWS, COLS, MINES);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //The vbox that will hold everything
        VBox mainContainer = new VBox();
        //Button 2d array
        Button[][] buttonGrid = new Button[ROWS][COLS];


        //The contents of the top portion of the VBox
        Label moveCount = new Label();
        moveCount.setText("MOVES \n" + minesweeper.getMovesCount());
        moveCount.setTextFill(Color.RED);
        moveCount.setPadding(new Insets(20));
        moveCount.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        Label mineCount = new Label();
        mineCount.setText("MINES \n" + minesweeper.getTotalMines());
        mineCount.setTextFill(Color.RED);
        mineCount.setPadding(new Insets(20));
        mineCount.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        //Create the reset button and stlye it
        Button reset = new Button("Reset");
        reset.setMinHeight(55);
        reset.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        reset.setTextFill(Color.GOLD);

        //Crea the hint button and style it
        Button hint = new Button("Hint");
        hint.setMinHeight(55);
        hint.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        hint.setTextFill(Color.GOLD);

        //Create the solve button, style it, and add the event handler
        Button solve = new Button("Solve");
        solve.setMinHeight(55);
        solve.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        solve.setTextFill(Color.GOLD);
        solve.setOnAction(new Solve(minesweeper));


        //Create the HBOX that will hold all this stuff and fill it
        HBox top = new HBox();
        top.getChildren().addAll(moveCount, mineCount, reset, hint, solve);

        // set up button grid 
        GridPane gridPane = new GridPane();
        for(int row = 0; row < ROWS; row++){
            for(int col = 0; col < COLS; col++){
                //make observer thing
                Button button = makeButton();
                buttonGrid[row][col] = button;
                button.setOnAction(new ButtonClick(minesweeper, new Location(row, col)));
                gridPane.add(button, col, row);
            }
        }

        hint.setOnAction(new Hint(this.minesweeper, buttonGrid));

        mainContainer.setPadding(new Insets(15));
        mainContainer.getChildren().addAll(top, gridPane);

        //Create a status label
        Label status = new Label("New Game!");
        status.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
        status.setTextFill(Color.RED);
        mainContainer.getChildren().add(status);

        // Create and register MinesweeperObserver
        MinesweeperObserverImp observer = new MinesweeperObserverImp(buttonGrid, minesweeper, status, moveCount);
        minesweeper.register(observer);

        // setup reset button logic
        reset.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                // when the reset button is clicked we should get a new game. this sets that up
                
                // create a new minesweeper game with the same parameters (size and mine count) 
                Minesweeper minesweeperReset = new Minesweeper(minesweeper,null);
                
                // set current instance to new instance
                minesweeper = minesweeperReset;

                // creates na anew hint helper for the new game
                hint.setOnAction(new Hint(minesweeper, buttonGrid));
                // resets moves counter
                moveCount.setText("MOVES \n" + 0);
            }
        });


        mainContainer.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY,Insets.EMPTY)));
        Scene scene = new Scene(mainContainer);
        stage.setTitle("Minesweeper!");
        stage.setScene(scene);
        stage.show();
        
    }

    private static Button makeButton(){
        Button button = new Button("");
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        button.setMinHeight(40);
        button.setMinWidth(40);
        button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderStroke.THIN)));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}