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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.loose.fis.banking.CouldNotFindWalletException;
import org.loose.fis.banking.Wallet;
import org.loose.fis.banking.Walletservices;
import org.loose.fis.introduction.Main;
import org.loose.fis.introduction.services.FileSystemService;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private final Double snakeSize = 64.;
    private Rectangle snakeHead;
    private Rectangle snakeTail_1;
    double xPos;
    double yPos;

    Food food;
    ExtraFood extraFood;
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
    @FXML
    private ImageView Invincibility;
    @FXML
    public Button closeButton;
    private int Score=0;


    Timeline timeline;

    private boolean canChangeDirection;

    @FXML
    void start(MouseEvent event) {

        startButton.setOpacity(0); //dupa apasarea butonului acesta dispare
        Score=0;//scorul se reseteaza
        String txt;
        txt="Score: " + String.format("%d", Score);
        score.setText(txt);


        for (Rectangle snake : snakeBody) {
            anchorPane.getChildren().remove(snake);
        }

        gameTicks = 0;
        positions.clear();
        snakeBody.clear();
        snakeHead = new Rectangle(320, 320, snakeSize, snakeSize);
        snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize, snakeHead.getY(), snakeSize, snakeSize);
        xPos = snakeHead.getLayoutX();
        yPos = snakeHead.getLayoutY();
        direction = Direction.RIGHT;
        canChangeDirection = true;

        food.moveFood();
        extraFood.moveFood();

        snakeBody.add(snakeHead);

        Image img = new Image("/game/head.jpg"); //atribuire resursa
        snakeHead.setFill(new ImagePattern(img));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        Image img1 = new Image("/game/tail.jpg");
        snakeTail_1.setFill(new ImagePattern(img1));
        snakeBody.add(snakeTail_1);

        anchorPane.getChildren().addAll(snakeHead, snakeTail_1);

    }


    public void closeApp(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

     double speed= 0.1;

    double deltaW ;
    double deltaH ;
    Image img = new Image("/game/BoostedHead.jpg");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        timeline = new Timeline(new KeyFrame(Duration.seconds(speed), e -> {
            positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos));
            moveSnakeHead(snakeHead);
            for (int i = 1; i < snakeBody.size(); i++) {
                moveSnakeTail(snakeBody.get(i), i);
            }

            canChangeDirection = true;
            eatFood();
            eatExtraFood();
            gameTicks++;

            if(ExtraFoodHasBeenEaten)
            {
                String txt1;
                snakeHead.setFill(new ImagePattern(img));
                Invincibility.setVisible(true);

                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                Invincibility.setVisible(false);
                                ExtraFoodHasBeenEaten=false;
                                Image img = new Image("/game/head.jpg");
                                snakeHead.setFill(new ImagePattern(img));
                            }
                        },
                        5000
                );
            }
            else {
                deltaW = anchorPane.getWidth();
                deltaH = anchorPane.getHeight();


                if (checkIfGameIsOver(snakeHead))
                    timeline.stop();
            }


        }));
        food = new Food(-50,-50,anchorPane,snakeSize);
        extraFood=new ExtraFood(-50,-50,anchorPane,snakeSize);


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
    Image img1 = new Image("/game/tail.jpg");//daca declar img1 local in addSnakeTail apare un bug care la a 13/14 apelare a acestuia face jocul sa se opreasca
    private void addSnakeTail() {
        Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);
        Rectangle snakeTail = new Rectangle(
                snakeBody.get(1).getX() + xPos + snakeSize,
                snakeBody.get(1).getY() + yPos,
                snakeSize, snakeSize);

        snakeTail.setFill(new ImagePattern(img1));

        snakeBody.add(snakeTail);
        anchorPane.getChildren().add(snakeTail);
    }

    public void addFunds(){
        Wallet userWallet = Walletservices.getWallet(Main.getUsername());
        userWallet.setfunds(userWallet.getfunds() + (int) Math.floor(Score/10));
        Walletservices.updateWallet(userWallet);
    }


    public boolean checkIfGameIsOver(Rectangle snakeHead) {


        if (xPos > 1920-384 || xPos <= -375+28 ||yPos <= -320+28 || yPos > 1080-320)
        {
            startButton.setOpacity(100);
            startButton.setDisable(false);

           addFunds();

            return true;
        } else
            if(snakeHitItSelf())
        {
            startButton.setOpacity(100);
            startButton.setDisable(false);

            addFunds();

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
@FXML
Label money;
    private void eatFood(){
        if(xPos + snakeHead.getX() == food.getPosition().getXPos() && yPos + snakeHead.getY() == food.getPosition().getYPos()){
            Score=Score+1;
            String txt;
            txt="Score: "+Score;

            if(Score % 10==0)
            {money.setVisible(true);
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                money.setVisible(false);
                            }
                        },
                        5000
                );}
            score.setText(txt);
            foodCantSpawnInsideSnake();
            addSnakeTail();
        }
    }
    boolean ExtraFoodHasBeenEaten=false;
    private void eatExtraFood(){

        if(xPos + snakeHead.getX() == extraFood.getPosition().getXPos() && yPos + snakeHead.getY() == extraFood.getPosition().getYPos()){
            ExtraFoodHasBeenEaten=true;
            Score=Score+1;
            String txt;
            txt="Score: "+Score;
            score.setText(txt);
            addSnakeTail();
            extraFood.disable();
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {

                            extraFoodCantSpawnInsideSnake();extraFood.enable();
                        }
                    },
                    10000
            );



        }
    }
    private void foodCantSpawnInsideSnake(){
        food.moveFood();
        while (isFoodInsideSnake()){
            food.moveFood();
        }
    }

    private void extraFoodCantSpawnInsideSnake(){
        extraFood.moveFood();
        while (isExtraFoodInsideSnake()){
            extraFood.moveFood();
        }
    }

    private boolean isFoodInsideSnake(){
        int size = positions.size();
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(food.getPosition().getXPos() == (positions.get(i).getXPos())
                        && food.getPosition().getYPos() == (positions.get(i).getYPos())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isExtraFoodInsideSnake(){
        int size = positions.size();
        if(size > 2){
            for (int i = size - snakeBody.size(); i < size; i++) {
                if(extraFood.getPosition().getXPos() == (positions.get(i).getXPos())
                        && extraFood.getPosition().getYPos() == (positions.get(i).getYPos())){
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
    @FXML
    void enter(){
        instructions.setVisible(true);
        keyboard.setVisible(true);
    }

}