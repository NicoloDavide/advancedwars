package com.example.advancedwars;

import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AWTitolo extends Pane {
    private Text text;

    public AWTitolo(String name) {
        String spread = ""; //spazi tra le lettere del titolo
        for (char c : name.toCharArray()){
            spread += c + "";
        }
        text = new Text(spread);
        text.setFill(Color.BLACK);
        text.setEffect(new DropShadow(30, Color.BLACK)); //effetto trovato per evidenziare quando passo con il mouse

        getChildren().addAll(text);

    }
    public double getTitlewidth(){
        return text.getLayoutBounds().getWidth();
    }
    public double getTitleHeight(){
        return text.getLayoutBounds().getHeight();
    }
}