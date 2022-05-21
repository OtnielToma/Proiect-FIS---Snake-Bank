package org.loose.fis.game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller <speed> implements Initializable{

    private final Double snakeSize = 50.;
    private Rectangle snakeHead;
    private Rectangle snakeTail_1;
    double xPos;
    double yPos;

    Food food;
    private Direction direction;
    private final List<Position> positions = new ArrayList<>();
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    private int gameTicks;
    @FXML
    private ImageView keyboard;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;
    @FXML
    private Label instructions;
    @FXML
    private Label score;

    private int Score=0;


    Timeline timeline;

    private boolean canChangeDirection;

    @FXML
    void start(MouseEvent event) {
        startButton.setOpacity(0); //dupa apasarea butonului acesta dispare
        Score=0;//scorul se reseteaza
        String txt;
        txt="Score: "+Score;
        score.setText(txt);

        for (Rectangle snake : snakeBody) {
            anchorPane.getChildren().remove(snake);
        }

        gameTicks = 0;
        positions.clear();
        snakeBody.clear();
        snakeHead = new Rectangle(250, 250, snakeSize, snakeSize);
        snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
        xPos = snakeHead.getLayoutX();
        yPos = snakeHead.getLayoutY();
        direction = Direction.RIGHT;
        canChangeDirection = true;
        food.moveFood();

        snakeBody.add(snakeHead);

        Image img = new Image("/game/head.jpg"); //atribuire resursa
        snakeHead.setFill(new ImagePattern(img));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        snakeBody.add(snakeTail_1);

        anchorPane.getChildren().addAll(snakeHead, snakeTail_1);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos));
            moveSnakeHead(snakeHead);
            for (int i = 1; i < snakeBody.size(); i++) {
                moveSnakeTail(snakeBody.get(i), i);
            }
            canChangeDirection = true;
            eatFood();
            gameTicks++;
            if(checkIfGameIsOver(snakeHead)){
                timeline.stop();
            }
        }));
        food = new Food(-50,-50,anchorPane,snakeSize);

    }

    @FXML
    void moveSquareKeyPressed(KeyEvent event) {

        if(canChangeDirection){
            if (event.getCode().equals(KeyCode.W) && direction != Direction.DOWN) {
                direction = Direction.UP;
            } else if (event.getCode().equals(KeyCode.S) && direction != Direction.UP) {
                direction = Direction.DOWN;
            } else if (event.getCode().equals(KeyCode.A) && direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            } else if (event.getCode().equals(KeyCode.D) && direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            }
            canChangeDirection = false;
        }
    }


    private void moveSnakeHead(Rectangle snakeHead) {
        if (direction.equals(Direction.RIGHT)) {
            xPos = xPos + snakeSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT)) {
            xPos = xPos - snakeSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP)) {
            yPos = yPos - snakeSize;
            snakeHead.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN)) {
            yPos = yPos + snakeSize;
            snakeHead.setTranslateY(yPos);
        }
    }

    private void moveSnakeTail(Rectangle snakeTail, int tailNumber) {
        double yPos = positions.get(gameTicks - tailNumber + 1).getYPos() - snakeTail.getY();
        double xPos = positions.get(gameTicks - tailNumber + 1).getXPos() - snakeTail.getX();
        snakeTail.setTranslateX(xPos);
        snakeTail.setTranslateY(yPos);
    }

    private void addSnakeTail() {
        Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);
        Rectangle snakeTail = new Rectangle(
                snakeBody.get(1).getX() + xPos + snakeSize,
                snakeBody.get(1).getY() + yPos,
                snakeSize, snakeSize);
        snakeBody.add(snakeTail);
        anchorPane.getChildren().add(snakeTail);
    }

    public boolean checkIfGameIsOver(Rectangle snakeHead) {
        double deltaW = anchorPane.getWidth() ;
        double deltaH = anchorPane.getHeight() ;

        if (xPos > deltaW-318 || xPos < -250 ||yPos < -250 || yPos > deltaH-318)
        {
            startButton.setOpacity(100);
            startButton.setDisable(false);
            return true;
        } else
            if(snakeHitItSelf())
        {
            startButton.setOpacity(100);
            startButton.setDisable(false);
            return true;
        }

        return false;
    }

    public boolean snakeHitItSelf(){
        int size = positions.size() - 1;
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(positions.get(size).getXPos() == (positions.get(i).getXPos())
                        && positions.get(size).getYPos() == (positions.get(i).getYPos())){

                    return true;
                }
            }
        }
        return false;
    }

    private void eatFood(){
        if(xPos + snakeHead.getX() == food.getPosition().getXPos() && yPos + snakeHead.getY() == food.getPosition().getYPos()){
            Score=Score+1;
            String txt;
            txt="Score: "+Score;
            score.setText(txt);
            foodCantSpawnInsideSnake();
            addSnakeTail();
        }
    }

    private void foodCantSpawnInsideSnake(){
        food.moveFood();
        while (isFoodInsideSnake()){
            food.moveFood();
        }
    }

    private boolean isFoodInsideSnake(){
        int size = positions.size();
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(food.getPosition().getXPos() == (positions.get(i).getXPos())
                        && food.getPosition().getYPos() == (positions.get(i).getYPos())){
                    System.out.println("Inside");
                    return true;
                }
            }
        }
        return false;
    }



    public void backToMenuScene(ActionEvent event) throws IOException {

        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("introduction/menuScene.fxml")));
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

    @FXML
    void exit(){
        instructions.setVisible(false);
        keyboard.setVisible(false);
    }

}