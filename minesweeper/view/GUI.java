package minesweeper.view;


import javafx.scene.paint.Color;
import javafx.application.Application;
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
import minesweeper.model.Minesweeper;

public class GUI extends Application{
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int MINES = 2;

    static final Image MINE  = new Image("file:media/images/mine24.png");

    private Minesweeper minesweeper;

    public GUI(){
        this.minesweeper = new Minesweeper(ROWS, COLS, MINES);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //The vbox that will hold everything
        VBox mainContainer = new VBox();


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
        Button reset = new Button("Reset");
        reset.setMinHeight(55);
        reset.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        reset.setTextFill(Color.GOLD);
        Button hint = new Button("Hint");
        hint.setMinHeight(55);
        hint.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        hint.setTextFill(Color.GOLD);
        HBox top = new HBox();
        top.getChildren().addAll(moveCount, mineCount, reset, hint);


        GridPane gridPane = new GridPane();
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                //make observer thing
                Button button = makeButton();
                gridPane.add(button, j, i);
            }
        }

        mainContainer.setPadding(new Insets(15));
        mainContainer.getChildren().addAll(top, gridPane);

        //Create a status lable
        Label status = new Label("Keep sweeping!");
        status.setBackground(new Background(new BackgroundFill(Color.GOLD, CornerRadii.EMPTY, Insets.EMPTY)));
        status.setTextFill(Color.RED);
        mainContainer.getChildren().add(status);

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