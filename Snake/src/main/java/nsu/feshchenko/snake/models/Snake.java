package nsu.feshchenko.snake.models;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Snake {
    private Point head;
    private List<Point> body = new ArrayList<>();

    public Snake(){
    }

    public Snake(Point head, List<Point> body) {
        this.head = head;
        this.body = body;
    }

    public void initSnake(int rows, int columns) {
        head = new Point(columns / 2, rows / 2);
        body.add(head);
    }

    public void moveRight() {
        head.x++;
    }

    public void moveLeft() {
        head.x--;
    }

    public void moveUp() {
        head.y--;
    }

    public void moveDown() {
        head.y++;
    }

    public Point getHead() {
        return head;
    }

    public List<Point> getBody() {
        return body;
    }

    public void removeBody(List<Point> snakeBody){
        snakeBody.remove(snakeBody.size()-1);
    }

    public boolean isGameOver(int squareSize, int width, int height, List<Point> barriers) {
        if (head.x < 0 || head.y < 0 || head.x * squareSize >= width || head.y * squareSize >= height) {
            return true;
        }

        for (int i = 1; i < body.size(); i++) {
            if (head.x == body.get(i).getX() && head.getY() == body.get(i).getY()) {
                return true;
            }
        }

        for (int i = 0; i < barriers.size(); i++) {
            if (head.x == barriers.get(i).getX() && head.y == barriers.get(i).getY()) {
                return true;
            }
        }
        return false;
    }
}
