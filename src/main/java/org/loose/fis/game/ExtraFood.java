package org.loose.fis.game;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


import java.util.Random;
public class ExtraFood {
    private Position position;
    private Rectangle rectangle;
    private AnchorPane pane;
    private Random random = new Random();
    private int size;

    public ExtraFood(double xPos, double yPos, AnchorPane pane, double size) {
        this.pane = pane;
        this.size = (int) size;

        position = new Position(xPos,yPos);
        rectangle = new Rectangle(position.getXPos(),position.getYPos(),size,size);

        Image img = new Image("/game/apple2.png");
        rectangle.setFill(new ImagePattern(img));

        pane.getChildren().add(rectangle);
    }

    public Position getPosition() {
        return position;
    }

    public void getRandomSpotForFood()
    {
        int positionX = random.nextInt(30);
        int positionY = (int) (random.nextInt((int) (16.875-3.793)) + 3.793);
        rectangle.setX(positionX * size);
        rectangle.setY(positionY * size);

        position.setXPos(positionX * size);
        position.setYPos(positionY * size);
    }
    public void remove(AnchorPane pane){
        pane.getChildren().remove(rectangle);
    }

    public void disable(){
        rectangle.setOpacity(0);
    }
    public void enable(){
        rectangle.setOpacity(100);
    }

    public void getBlindSpotForFood()
    {

        rectangle.setX(2500 * size);
        rectangle.setY(2500 * size);

        position.setXPos(2500 * size);
        position.setYPos(2500 * size);
    }

    public void moveFood()
    {
        getRandomSpotForFood();
    }

}
