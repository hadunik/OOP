package com.example.normalsnake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Design extends Application {
    int speed;
    Direction direction = Direction.LEFT;
    int width = 20;
    int height = 20;
    int cornerSize = 25;
    List<Corner> food = new ArrayList<>();
    Snake snakePosition = new Snake(width, height, 15, 15, this);
    Canvas c = new Canvas(width * cornerSize, height * cornerSize);
    GraphicsContext gc = c.getGraphicsContext2D();
    Random rand = new Random();
    AnimationTimer at;


    @Override
    public void start(Stage stage) throws Exception {
        speed = 5;
        food.add(new Corner(rand.nextInt(width), rand.nextInt(height)));
        food.add(new Corner(rand.nextInt(width), rand.nextInt(height)));
        food.add(new Corner(rand.nextInt(width), rand.nextInt(height)));
        List<Corner> snake = snakePosition.tick(food, direction);
        VBox root = new VBox();
        root.getChildren().add(c);
        Scene scene = new Scene(root, width * cornerSize, height * cornerSize);

        at = new AnimationTimer() {
            long lastTick = 0;

            public void handle(long now) {
                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    try {
                        if (speed - 5 >= 10) {
                            gc.setFill(Color.RED);
                            gc.setFont(new Font("", 50));
                            gc.fillText("YOU WIN!!!", 100, 250);
                            restartGame(scene, stage);
                            return;
                        }
                        forField();
                    } catch (Exception e) {
                        gc.setFill(Color.RED);
                        gc.setFont(new Font("", 50));
                        gc.fillText("Game Over", 100, 250);
                        restartGame(scene, stage);
                    }
                }

            }
        };
        at.start();




//        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
//            if (key.getCode() == KeyCode.SPACE) {
//                try {
//                    restart(stage);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        //control
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (snake.size() == 1) {
                if (key.getCode() == KeyCode.W) {
                    direction = Direction.UP;
                }
                if (key.getCode() == KeyCode.A) {
                    direction = Direction.LEFT;
                }
                if (key.getCode() == KeyCode.S) {
                    direction = Direction.DOWN;
                }
                if (key.getCode() == KeyCode.D) {
                    direction = Direction.RIGHT;
                }
            } else {
                if (key.getCode() == KeyCode.W && !(direction == Direction.DOWN)) {
                    direction = Direction.UP;
                }
                if (key.getCode() == KeyCode.A && !(direction == Direction.RIGHT)) {
                    direction = Direction.LEFT;
                }
                if (key.getCode() == KeyCode.S && !(direction == Direction.UP)) {
                    direction = Direction.DOWN;
                }
                if (key.getCode() == KeyCode.D && !(direction == Direction.LEFT)) {
                    direction = Direction.RIGHT;
                }
            }

        });

        stage.setTitle("Ssssssnake");

        InputStream icon = getClass().getResourceAsStream("cropped-zachistka.png");
        assert icon != null;
        Image image = new Image(icon);
        stage.getIcons().add(image);
        setField();
        stage.setScene(scene);
        stage.show();
    }

    public void setField() {
        //fill
        //background
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(0, 0, width * cornerSize, height * cornerSize);

        //score
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("", 30));
        gc.fillText("Score " + (speed - 5), 10, 30);

    }

    public void forField() throws Exception {
        setField();
        forSnake();
        drawFood();
    }

    public void forSnake() throws Exception {
        var snake = snakePosition.tick(food, direction);
        drawSnake(snake);
    }

    public void drawSnake(List<Corner> snake) {
        for (Corner c : snakePosition.snake) {
            gc.setFill(Color.GRAY);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 1, cornerSize - 1);
            gc.setFill(Color.BLACK);
            gc.fillRect(c.x * cornerSize, c.y * cornerSize, cornerSize - 2, cornerSize - 2);
        }
    }

    public void drawFood() {
        gc.setFill(Color.RED);
        for (Corner corner : food) {
            gc.fillOval(corner.x * cornerSize, corner.y * cornerSize, cornerSize, cornerSize);
        }
    }

    void cleanup() {
        food.clear();
        snakePosition = new Snake(width, height, 15, 15, this);
        speed = 5;
        direction = Direction.LEFT;
        at.stop();
    }

    void restart(Stage stage) throws Exception {
        cleanup();
        start(stage);
    }

    void restartGame(Scene scene, Stage stage){
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.SPACE) {
                try {
                    restart(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
