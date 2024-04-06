package com.example.hopverkefni;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class StrengirController implements Initializable {

    public TextArea adaltexti;
    public Button Leita;
    public TextArea Leitarord;
    public ListView listi;
    public Label TeljaOrdLabel;
    public Button TeljaOrd;
    public TextArea skiptiOrd;

    public FileChooser fileChooser = new FileChooser();

    private String texti;
    private String Ord;
    private String OrdSkipta;

    /**
     * Leitar að leitarorði í texta og skilar inn í list view
     *
     */
    public void ordiTexta(){
        if (!Ord.isEmpty() && !texti.isEmpty()){
            // Setur textan og leitar orð sem hástafi svo það sé hægt að finna öll
            // tilvik af leitarorði í textanum sama hvort það sé upprunalega með
            // stórum eða littlum staf í byrjun
            String text = texti.toUpperCase();
            String ord = Ord.toUpperCase();

            String[] texti = text.split(" |\\. |\\, |\\: |\\; ");
            listi.getItems().clear();

            // Fer í gegnum textan og ef orðið í textanum er eins og leitarorðið
            // setur hann það í listview sem hann skilar.
            for (int i = 0; i < texti.length ; i++ ) {
                if (texti[i].equals(ord)){
                    listi.getItems().add(i + 1);
                }
            }
        }

        else{
            System.out.println("error");
        }
    }

    /**
     *
     */
    public void skiptaUtOrdi(){
        if(!Ord.isEmpty() && !texti.isEmpty() && !OrdSkipta.isEmpty()){

        }
    }

    /**
     * Telur hversu mörg orð eru í textanum
     */
    public void teljaOrd(){
        if (this.texti.isEmpty()){

        }
        else{
            String[] texti = this.texti.split(" ");
            TeljaOrdLabel.setText(Integer.toString(texti.length));
        }
    }

    /**
     * Leifir okkur að sjá file explorer/finder til að velja txt file.
     */
    public void saveText(){
        File file = fileChooser.showSaveDialog(new Stage());
        if(file != null){
            saveSystem(file, adaltexti.getText());
        }
    }

    /**
     * Sér um að vista textan sem notandi hefur skrifað í txt skrá
     * @param file skjalið sem við ættlum að vista textan í
     * @param content textin sem við ættlum að vista
     */
    public void saveSystem(File file, String content) {
        try {
            PrintWriter printwriter = new PrintWriter(file);
            printwriter.write(content);
            printwriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("/Users/sturlaingason/Documents"));
    }

    /**
     *
     */
    public void getText(){
        File file = fileChooser.showOpenDialog(new Stage());

        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                adaltexti.appendText(scanner.nextLine() + "\n");
            }
            this.texti = adaltexti.getText();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void setTexti(){
        this.texti = adaltexti.getText();
    }

    public void setOrd(){
        this.Ord = Leitarord.getText();
    }

    public void setOrdSkipta(){
        this.OrdSkipta = skiptiOrd.getText();
    }
}
