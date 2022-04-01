package minesweeper.view;

import java.awt.Color;

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

        mainContainer.getChildren().addAll(top);

        Scene scene = new Scene(mainContainer);
        stage.setTitle("Minesweeper!");
        stage.setScene(scene);
        stage.show();
        
    }

    public static Button makeCoveredButton(){
        Button button = new Button("");
        button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        button.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(10), Insets.EMPTY);
        button.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));
        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
