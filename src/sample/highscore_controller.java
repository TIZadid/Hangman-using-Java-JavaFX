package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;



public class highscore_controller implements Initializable
{
    @FXML
    private TextField one;
    @FXML private TextField two;
    @FXML private TextField three;
    @FXML private TextField four;
    @FXML private TextField five;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String txtfile;
        txtfile="HighScore.txt";
        String path = txtfile;
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line,line1,line2,line3,line4;
            String ln[]=bufferedReader.readLine().split("\\s");
            line = "1: "+ln[0]+" "+ln[1];
            ln=bufferedReader.readLine().split("\\s");
            line1 = "2: "+ln[0]+" "+ln[1];
            ln=bufferedReader.readLine().split("\\s");
            line2 = "3: "+ln[0]+" "+ln[1];
            ln=bufferedReader.readLine().split("\\s");
            line3 = "4: "+ln[0]+" "+ln[1];
            ln=bufferedReader.readLine().split("\\s");
            line4 = "5: "+ln[0]+" "+ln[1];
            one.setText(line);
            two.setText(line1);
            three.setText(line2);
            four.setText(line3);
            five.setText(line4);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button Back;


    public void onClickingBack(ActionEvent event) throws IOException {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent mainWindow = loader.load();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle("HangMan");
        Scene XX = new Scene(mainWindow, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        window.setScene(XX);
        window.show();
        window.setMaximized(true);
    }
}
