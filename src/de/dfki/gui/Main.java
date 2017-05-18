package de.dfki.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Avatar UI Control");
        primaryStage.setScene(new Scene(root, 300, 275, Color.AQUA));
        primaryStage.show();
        primaryStage.getScene().getStylesheets().add(String.valueOf(this.getClass().getResource("/css/style.css")));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
