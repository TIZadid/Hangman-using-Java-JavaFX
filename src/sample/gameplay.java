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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.jar.Attributes;

import static sample.Controller.NAME;


public class gameplay implements Initializable {
    @FXML
    Button shuffle;
    @FXML
    Button exit;
    @FXML
    TextField scorebox;
    @FXML
    Button cheat;
    @FXML
    Button nextWord;
    @FXML
    TextField question;
    @FXML
    TextField gameOver;
    @FXML
    TextField gameWon;
    @FXML
    Line hand,hand1,body,leg,leg1;
    @FXML
    Circle head;


    @FXML
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26;
    @FXML
    GridPane keyBoard;

    public static int score =0;
    public static WordList WORDS;
    public static String currentState;
    public static String word;
    public static int error=6;
    public static int hint=2;



    public void newWord(){
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
        b5.setDisable(false);
        b6.setDisable(false);
        b7.setDisable(false);
        b8.setDisable(false);
        b9.setDisable(false);
        b10.setDisable(false);
        b11.setDisable(false);
        b12.setDisable(false);
        b13.setDisable(false);
        b14.setDisable(false);
        b15.setDisable(false);
        b16.setDisable(false);
        b17.setDisable(false);
        b18.setDisable(false);
        b19.setDisable(false);
        b20.setDisable(false);
        b21.setDisable(false);
        b22.setDisable(false);
        b23.setDisable(false);
        b24.setDisable(false);
        b25.setDisable(false);
        b26.setDisable(false);
        word=WORDS.removeRandomWord();
        currentState = new String(new char[word.length()]).replace("\0", "_");
        String tmpState="";
        hint=2;
        cheat.setDisable(false);
        cheat.setText("Cheat(2)");
        for (int i = 0; i < word.length(); i++){
            tmpState+=currentState.charAt(i)+" ";
        }
        question.setText(tmpState);
        System.out.println(word);
        error=6;
        drawHangman(error);
    }

    public void newLetter(String letter){
        String newState="";
        System.out.println(word);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter.charAt(0)) {
                newState += letter.charAt(0);
            } else if (currentState.charAt(i) != '_') {
                newState += word.charAt(i);
            } else {
                newState += "_";
            }
        }

        if (currentState.equals(newState)) {
            error--;
            drawHangman(error);
            if(score>0)
                score--;
        } else {
            currentState = newState;
            score++;
        }
        if (currentState.equals(word)) {
            System.out.println("Correct! You win! The word was " + word);
            score+=word.length();
            scorebox.setText("Score: "+score);

            gameWon.setVisible(true);
            keyBoard.setVisible(false);
            shuffle.setVisible(false);
            cheat.setVisible(false);
            nextWord.setVisible(true);
        }
        String tmpState="";
        for (int i = 0; i < word.length(); i++){
            tmpState+=currentState.charAt(i)+" ";
        }
        question.setText(tmpState);
        scorebox.setText("Score: "+score);
        if(error==0){
            tmpState="";
            for (int i = 0; i < word.length(); i++){
                tmpState+=word.charAt(i)+" ";
            }
            question.setText(tmpState);
            gameOver.setVisible(true);
            keyBoard.setVisible(false);
            shuffle.setVisible(false);
            cheat.setVisible(false);
        }
    }

    public void onpressingNext(ActionEvent event) {
        nextWord.setVisible(false);
        gameWon.setVisible(false);
        keyBoard.setVisible(true);
        shuffle.setVisible(true);
        cheat.setVisible(true);
        newWord();
        }


    public void onpressingkey(ActionEvent event) {
        Button x = (Button) event.getSource();
        x.setDisable(true);
        String l= x.getText().toLowerCase();
        newLetter(l);
        }


    public void onpressingcheat(ActionEvent event) {
        hint--;
        cheat.setText("cheat ("+hint+")");
        if(hint==0)
            cheat.setDisable(true);
        if(score>1)
            score-=3;
        else score=-1;
        String c="";
        for(int i =0;i<word.length();i++)
                if(word.charAt(i)!=currentState.charAt(i))
                    c+=word.charAt(i);
        newLetter(c);
    }


    public void onpressingshuffle(ActionEvent event) {
        if(score>1)
            score-=2;
        else score=0;
        scorebox.setText("Score: "+score);
        newWord();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyBoard.setVisible(true);
        nextWord.setVisible(false);
        gameOver.setVisible(false);
        shuffle.setVisible(true);
        gameWon.setVisible(false);
        cheat.setVisible(true);
        drawHangman(0);
        WORDS = new WordList();

        word=WORDS.removeRandomWord();
        score=0;
        newWord();
    }

    public void highScore() throws IOException {
        String txtfile;

        int scoreList[]=new int[6];
        String scorerList[] = new String[6];

        txtfile="HighScore.txt";
        String path = txtfile;
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ln[];
            for(int i=0;i<5;i++) {
                ln = bufferedReader.readLine().split("\\s");
                scorerList[i]=ln[0];
                scoreList[i]=Integer.parseInt(ln[1]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scorerList[5]= NAME;
        scoreList[5]=score;

        int i, j,tmp;
        String temp;
        for (i = 0; i < 6-1; i++)
            for (j = 0; j < 6-i-1; j++)
                if (scoreList[j] < scoreList[j+1]) {
                    tmp = scoreList[j];
                    scoreList[j] = scoreList[j + 1];
                    scoreList[j + 1] = tmp;
                    temp = scorerList[j];
                    scorerList[j] = scorerList[j + 1];
                    scorerList[j + 1] = temp;
                }
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (i=0;i<5;i++) {
            bufferedWriter.write(scorerList[i]);
            bufferedWriter.write(" " + scoreList[i]);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
    public void drawHangman(int err){
        err=6-err;
        if(err==1)
            head.setVisible(true);
        else if(err==2)
            body.setVisible(true);
        else if(err==3)
            hand.setVisible(true);
        else if(err==4)
            hand1.setVisible(true);
        else if(err==5)
            leg.setVisible(true);
        else if(err==6)
            leg1.setVisible(true);
        else if(err==0){
            head.setVisible(false);
            body.setVisible(false);
            hand.setVisible(false);
            hand1.setVisible(false);
            leg.setVisible(false);
            leg1.setVisible(false);
        }
    }

    public void onpressingexit(ActionEvent event) throws Exception{
        highScore();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("highscore.fxml"));
        Parent mainWindow = loader.load();


        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setTitle("High Scores");
        Scene XX = new Scene(mainWindow, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        window.setScene(XX);
        window.show();
        window.setMaximized(true);
    }
}
