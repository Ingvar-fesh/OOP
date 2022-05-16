package nsu.feshchenko.snake.models;

public class Field {
    private final int WIDTH;
    private final int HEIGHT;
    private final int ROWS;
    private final int COLUMNS;
    private final int SQUARE_SIZE;

    public Field(int rows) {
        this.WIDTH = 600;
        this.HEIGHT = 600;
        this.ROWS = rows;
        this.COLUMNS = rows;
        this.SQUARE_SIZE = this.WIDTH / rows;
    }

    public Field() {
        this.WIDTH = 600;
        this.HEIGHT = 600;
        this.ROWS = 20;
        this.COLUMNS = ROWS;
        this.SQUARE_SIZE = this.WIDTH / this.ROWS;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getSQUARE_SIZE() {
        return SQUARE_SIZE;
    }
}
