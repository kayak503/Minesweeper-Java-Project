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
        moveCount.setText(minesweeper.getMovesCount() + "");
        Label mineCount = new Label();
        mineCount.setText(minesweeper.getTotalMines() + "");
        Button reset = new Button("Reset");
        Button hint = new Button("Hint");
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

        Scene scene = new Scene(mainContainer);
        stage.setTitle("Minesweeper!");
        stage.setScene(scene);
        stage.show();
        
    }

    private static Button makeButton(){
        Button button = new Button("");
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        button.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(5), Insets.EMPTY)));
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderStroke.THIN)));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}