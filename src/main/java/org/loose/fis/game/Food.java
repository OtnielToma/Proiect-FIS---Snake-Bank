package org.loose.fis.game;


import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Food {
    private Position position;
    private Rectangle rectangle;
    private AnchorPane pane;
    private Random random = new Random();
    private int size;

    public Food(double xPos, double yPos, AnchorPane pane, double size) {
        this.pane = pane;
        this.size = (int) size;

        position = new Position(xPos,yPos);
        rectangle = new Rectangle(position.getXPos(),position.getYPos(),size,size);

        Image img = new Image("/game/apple.png");
        rectangle.setFill(new ImagePattern(img));

        pane.getChildren().add(rectangle);
    }

    public Position getPosition() {
        return position;
    }

}