package minesweeper.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.model.Minesweeper;

public class GUI extends Application{
    static final Image MINE  = new Image("file:media/images/mine24.png");

    private Minesweeper minesweeper;

    public GUI(){
        this.minesweeper = new Minesweeper(4, 4, 2);
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

    public static void main(String[] args) {
        launch(args);
    }


}
