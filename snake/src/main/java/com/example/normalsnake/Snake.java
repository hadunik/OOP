package com.example.normalsnake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    List<Corner> snake = new ArrayList<>();
    int width;
    int height;
    Random rand = new Random();
    Design design;

    public Snake(int width, int height, int snakeX, int snakeY, Design design) {
        snake.clear();
        this.design = design;
        this.height = height;
        this.width = width;
        snake.add(new Corner(snakeX, snakeY));
//        for (int i = 0; i < 3; i++) {
//            snake.add(new Corner(snakeX + i, snakeY));
//        }
    }

    public List<Corner> tick(List<Corner> food, Direction direction) throws Exception{

        switch (direction) {
            case UP -> {
                snake.add(0, new Corner(snake.get(0).x, snake.get(0).y - 1));
                if (snake.get(0).y < 0) {
                    throw new Exception("destroy");
                }
            }
            case DOWN -> {
                snake.add(0, new Corner(snake.get(0).x, snake.get(0).y + 1));
                if (snake.get(0).y > height) {
                    throw new Exception("destroy");
                }
            }
            case LEFT -> {
                snake.add(0, new Corner(snake.get(0).x - 1, snake.get(0).y));
                if (snake.get(0).x < 0) {
                    throw new Exception("destroy");
                }
            }
            case RIGHT -> {
                snake.add(0, new Corner(snake.get(0).x + 1, snake.get(0).y));
                if (snake.get(0).x > width) {
                    throw new Exception("destroy");
                }
            }
        }

        //self destroy
        for (int i = 1; i < snake.size(); i++) {
            if (snake.get(0).x == snake.get(i).x && snake.get(0).y == snake.get(i).y) {
                throw new Exception("destroy");
            }
        }

        //eat food
        boolean eatFood = false;
        Corner forDel = null;
        for (Corner corner : food) {
            if (corner.x == snake.get(0).x && corner.y == snake.get(0).y) {
                design.speed++;
                eatFood = true;
                forDel = corner;
            }
        }
        if (!eatFood) {
            snake.remove(snake.size() - 1);
        }
        else{
            food.remove(forDel);
            food.add(new Corner(rand.nextInt(width), rand.nextInt(height)));
        }
        return snake;
    }
}
