package com.example.hopverkefni;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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

    public FileChooser fileChooser = new FileChooser();

    private String texti;
    private String Ord;

    @FXML
    private Label welcomeText;

    @FXML
    private VBox vvv;

    @FXML
    private MenuButton backgroundMenu;

    /**
     * Leitar að leitarorði í texta og skilar inn í list view
     */
    public void ordiTexta() {
        if (Ord.isEmpty()) {  // athughar hvort við séum með leitarorð
            listi.getItems().add("Vantar leitarord");
        } else if (texti.isEmpty()) { //Athugar hvort það sé texti til að leita í
            listi.getItems().add("Vantar Texta");
        } else {
            String text = texti.toUpperCase();
            String ord = Ord.toUpperCase();

            String[] texti = text.split(" |\\. |\\, ");
            listi.getItems().clear();

            int sum = 0;

            for (int i = 0; i < texti.length; i++) {
                if (texti[i].equals(ord)) {
                    listi.getItems().add(i + 1);
                    sum +=1;
                }
            }

            listi.getItems().add(0, (ord + " í texta : " + sum));
        }
    }

    /**
     * Telur hversu mörg orð eru í textanum
     */
    public void teljaOrd() {
        if (this.texti.isEmpty()) {

        } else {
            String[] texti = this.texti.split(" ");
            TeljaOrdLabel.setText(Integer.toString(texti.length));
        }
    }

    public void setTexti() {
        this.texti = adaltexti.getText();
    }

    public void setOrd() {
        this.Ord = Leitarord.getText();
    }

    public void getText() throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(new Stage());

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                adaltexti.appendText(scanner.nextLine() + "\n");
            }
            this.texti = adaltexti.getText();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            saveContent(file, adaltexti.getText());
        }
    }

    public void saveContent(File file, String content) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(content);
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(null);
    }

    @FXML
    protected void changeBackgroundColor(ActionEvent event) {
        MenuItem menuItem = (MenuItem) event.getSource();
        String selectedColor = menuItem.getText().toLowerCase();
        vvv.getStyleClass().clear();
        vvv.getStyleClass().add(selectedColor);
    }
}
