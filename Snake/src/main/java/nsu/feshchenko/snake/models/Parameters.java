package nsu.feshchenko.snake.models;

public class Parameters{
    private final int fieldSize;
    private final int countFoods;
    private final int countBarriers;
    private final int goal;
    private final int speed;

    public Parameters(int fieldSize, int countFoods, int countBarriers, int goal, int speed) {
        this.fieldSize = fieldSize;
        this.countFoods = countFoods;
        this.countBarriers = countBarriers;
        this.goal = goal;
        this.speed = speed;
    }

    public Parameters() {
        this.fieldSize = 20;
        this.countFoods = 5;
        this.countBarriers = 13;
        this.goal = 20;
        this.speed = 150;
    }

    public int getSpeed() {
        return speed;
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
