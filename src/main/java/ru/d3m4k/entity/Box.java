package ru.d3m4k.entity;

import java.util.ArrayList;

public class Box {
    private int volume;
    private int width;
    private int length;
    private ArrayList<ArrayList<Integer>> space;

    public Box(ArrayList<ArrayList<Integer>> space) {
        this.space = space;
        length = space.size();
        width = space.stream()
                .max((line1, line2) -> Integer.compare(line1.size(), line2.size()))
                .get()
                .size();
        volume = length * width;
    }

    public ArrayList<ArrayList<Integer>> getSpace() {
        return space;
    }

    public void setSpace(ArrayList<ArrayList<Integer>> space) {
        this.space = space;
        length = space.size();
        width = space.stream()
                .max((line1, line2) -> Integer.compare(line1.size(), line2.size()))
                .get()
                .size();
        volume = length * width;
    }

    public int getVolume() {
        return volume;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
        volume = length * width;
    }
}