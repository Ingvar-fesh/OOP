package nsu.feshchenko.snake.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FoodGenerator {
    private final List<Point> foods = new ArrayList<>();
    private final int foodsOnField;
    private int currentFoods = 0;
    private final int goal;

    public FoodGenerator(int foodsOnField, int goal) {
        this.foodsOnField = foodsOnField;
        this.goal = goal;
    }

    public void generatorFoods(List<Point> body, int rows, int columns, List<Point> barriers) {
        while (currentFoods < goal) {
            for (int i = foods.size();i < foodsOnField; ++i) {
                int x = (int)(Math.random() * rows);
                int y = (int)(Math.random() * columns);
                for (int j = 0; j < foods.size(); j++) {
                    while (x == foods.get(j).getX() && foods.get(j).getY() == y) {
                        x = ((int) (Math.random() * rows));
                        y = ((int) (Math.random() * columns));
                    }
                }
                foods.add(new Point(x, y));
            }

            for (int i = 0; i < foodsOnField; i++) {
                for (int j = 0; j < barriers.size(); j++) {
                    if (foods.get(i).getX() == barriers.get(j).getX() && foods.get(i).getY() == barriers.get(j).getY()) {
                        int newX = ((int) (Math.random() * rows));
                        int newY = ((int) (Math.random() * columns));
                        while (newX == barriers.get(j).getX() && newY == barriers.get(j).getY()) {
                            newX = ((int) (Math.random() * rows));
                            newY = ((int) (Math.random() * columns));
                        }
                        foods.set(i, new Point(newX, newY));
                    }
                }
            }

            for (int i = 0; i < body.size(); i++) {
                Point snake = body.get(i);
                for (int j = 0; j < foodsOnField; j++) {
                    if (snake.getX() == foods.get(j).getX() && snake.getY() == foods.get(j).getY()) {
                        int newX = ((int) (Math.random() * rows));
                        int newY = ((int) (Math.random() * columns));
                        foods.set(j, new Point(newX, newY));
                    }
                }
            }
            currentFoods++;
            break;
        }
    }

    public int getFoodsOnField() {
        return foodsOnField;
    }

    public List<Point> getFoods() {
        return foods;
    }

    public void removeFromFoodList(int i) {
        foods.remove(i);
    }

    public int getCurrentFoods() {
        return currentFoods;
    }

    public int getGoal() {
        return goal;
    }
}
