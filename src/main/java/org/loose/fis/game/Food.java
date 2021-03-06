package org.loose.fis.game;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Random;

public class Food {
    private Position position;
    private Rectangle rectangle;
    private AnchorPane pane;
    private Random random = new Random();
    private int size;
    private Image img = new Image("/game/apple.png");
    private Color color=Color.green;

    public Food(double xPos, double yPos, AnchorPane pane, double size) {
        this.pane = pane;
        this.size = (int) size;

        position = new Position(xPos,yPos);
        rectangle = new Rectangle(position.getXPos(),position.getYPos(),size,size);


        rectangle.setFill(new ImagePattern(img));

        pane.getChildren().add(rectangle);
    }

    public Position getPosition() {
        return position;
    }
    public void moveFood()
    {
        getRandomSpotForFood();
    }

    public void getRandomSpotForFood()
    {

        int positionX = random.nextInt(30);
        int positionY = (int) (random.nextInt((int) (16.875-3.793)) + 3.793);

        //System.out.println("food pos "+positionX*size+" "+positionY*size);
        rectangle.setX(positionX * size);
        rectangle.setY(positionY * size);

        position.setXPos(positionX * size);
        position.setYPos(positionY * size);
    }



}