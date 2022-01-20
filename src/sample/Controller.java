package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML private TextField playerName;
    public static String NAME;

    public void onpressingexit(ActionEvent event) {
        Platform.exit();

        System.exit(0);

    }

    public void onpressingnew(ActionEvent event) throws IOException {

        NAME=playerName.getText();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameplay.fxml"));
        Parent mainWindow = loader.load();


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle("HangMan");
        Scene XX = new Scene(mainWindow, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());


        window.setScene(XX);
        window.show();
        window.setMaximized(true);





    }

    public void onclickinghighscore(ActionEvent event) throws Exception{

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("highscore.fxml"));
        Parent mainWindow = loader.load();


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle("Main Frame!");
        Scene XX = new Scene(mainWindow, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        window.setScene(XX);
        window.show();
        window.setMaximized(true);

    }
}
