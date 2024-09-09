package ru.d3m4k.entity;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Truck {
    private static final int HEIGHT = 6;
    private static final int WIDTH = 6;
    private static final char TRUCK_WALL = '+';

    private final Integer[][] truckCargoSpace = new Integer[HEIGHT][WIDTH];

    public static int getHeight() {
        return HEIGHT;
    }

    public static int getWidth() {
        return WIDTH;
    }

    public void addBox(Box box, int x, int y) {
        y = HEIGHT - y - 1;
        var truckBoxSpace = box.getSpace();

        for (int line = truckBoxSpace.size() - 1; line >= 0; line--) {
            int xIter = x;
            for (int column = 0; column < truckBoxSpace.get(line).size(); column++) {
                if (truckCargoSpace[y][xIter] != null)
                    throw new RuntimeException();
                truckCargoSpace[y][xIter++] = truckBoxSpace.get(line).get(column);
            }
            y--;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(truckCargoSpace).forEach(line -> {
            stringBuilder.append(TRUCK_WALL);
            stringBuilder.append(Arrays.stream(line)
                    .map(column -> column == null ? " " : column.toString())
                    .collect(Collectors.joining()));
            stringBuilder.append(TRUCK_WALL).append("\n");
        });

        stringBuilder.append(String.valueOf(TRUCK_WALL).repeat(WIDTH + 2));

        return stringBuilder.toString();
    }
}
