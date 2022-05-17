package nsu.feshchenko.snake.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarrierGenerator {
    private final List<Point> barriers = new ArrayList<>();
    private final int barriersOnField;

    public BarrierGenerator(int barriersOnField) {
        this.barriersOnField = barriersOnField;
    }

    public List<Point> getBarriers() {
        return barriers;
    }

    public void generateBarriers(int rows, int columns) {
        for (int i = 0;i < barriersOnField; ++i) {
            int x = ((int) (Math.random() * rows));
            int y = ((int) (Math.random() * columns));
            for (int j = 0; j < barriers.size(); j++) {
                if (x == barriers.get(j).getX() && barriers.get(j).getY() == y) {
                    x = ((int) (Math.random() * rows));
                    y = ((int) (Math.random() * columns));
                    while (x == barriers.get(j).getX() && y == barriers.get(j).getY()){
                        x = ((int) (Math.random() * rows));
                        y = ((int) (Math.random() * columns));
                    }
                }
            }
            barriers.add(new Point(x, y));
        }
    }
}
