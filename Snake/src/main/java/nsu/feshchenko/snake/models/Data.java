package nsu.feshchenko.snake.models;

public class Data {

    private static Data INSTANCE;
    private int fieldSize;
    private int countFoods;
    private int countBarriers;
    private int goal;
    private int speed;

    private Data() {
        this.fieldSize = 20;
        this.countFoods = 5;
        this.countBarriers = 13;
        this.goal = 20;
        this.speed = 150;
    }

    public static Data getINSTANCE(){
        if (INSTANCE == null)
            INSTANCE = new Data();
        return INSTANCE;
    }

    public int getCountBarriers() {
        return countBarriers;
    }

    public int getCountFoods() {
        return countFoods;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getGoal() {
        return goal;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCountBarriers(int countBarriers) {
        this.countBarriers = countBarriers;
    }

    public void setCountFoods(int countFoods) {
        this.countFoods = countFoods;
    }

    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
