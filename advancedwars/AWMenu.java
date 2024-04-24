package com.example.advancedwars;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class AWMenu extends Application {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {
            }),       //Bisogna aggiungere le azioni che ti portano al gioco
            new Pair<String, Runnable>("Settings", () -> {
            }),     // Vedi come aggiungere le impostazioni
            new Pair<String, Runnable>("Credits", () -> {
            }),
            new Pair<String, Runnable>("Quit Game", Platform::exit)
    );
    private Pane root = new Pane();
    private VBox menubox = new VBox(-5);
    private Line line;

    private Parent createContent() {
        //addBackground();
        addTitle();
        double lineX = WIDTH /2 -100 ;
        double lineY = HEIGHT /3+50;
        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5);
        startAnimation();
        return root;
    }
    /*private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("com.example.advancedwars/AWsfondo.png").toExternalForm()));
        imageView.setFitHeight(HEIGHT);
        imageView.setFitWidth(WIDTH);
        root.getChildren().add(imageView);
    }*/
    private void addTitle() {
        AWTitolo title = new AWTitolo("ADVANCED WARS");
        title.setTranslateX(WIDTH / 2 - title.getTitlewidth() / 2);
        title.setTranslateY(HEIGHT / 3);
        root.getChildren().add(title);

    }
    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 300);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);
        root.getChildren().add(line);
    }
    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {
            for (int i = 0; i < menubox.getChildren().size(); i++) ;
            {
                int i = 0;
                Node n = menubox.getChildren().get(i);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menubox.setTranslateX(x);
        menubox.setTranslateY(y);
        menuData.forEach(data -> {
            AWIndice item = new AWIndice(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);
            Rectangle clip = new Rectangle(300, 30);
           // clip.translateXProperty.bind(item.translateXProperty().negate());
            //item.setClip(clip);
            menubox.getChildren().addAll(item);

        });
        root.getChildren().add(menubox);
    }



    public static void main(String[] args)  {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception  {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Advanced Wars");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
