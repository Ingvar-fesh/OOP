package nsu.feshchenko.snake.models;

import javafx.application.Application;

public class Parameters{
    private final int fieldSize;
    private final int countFoods;
    private final int countBarriers;
    private final int goal;

    public Parameters(int fieldSize, int countFoods, int countBarriers, int goal) {
        this.fieldSize = fieldSize;
        this.countFoods = countFoods;
        this.countBarriers = countBarriers;
        this.goal = goal;
    }

    public Parameters() {
        this.fieldSize = 20;
        this.countFoods = 5;
        this.countBarriers = 13;
        this.goal = 10;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getCountFoods() {
        return countFoods;
    }

    public int getBarriersAmount() {
        return countBarriers;
    }

    public int getGoal() {
        return goal;
    }

}
