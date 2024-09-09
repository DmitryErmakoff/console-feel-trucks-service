package ru.d3m4k.service;

import ru.d3m4k.entity.Box;
import ru.d3m4k.entity.Truck;

import javax.naming.directory.InvalidAttributesException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BoxService {
    public ArrayList<Box> loadBoxesFromFile(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            Scanner fileScanner = new Scanner(fileReader);
            ArrayList<Box> boxes = new ArrayList<>();
            ArrayList<ArrayList<Integer>> boxDimensions = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                if (line.isBlank()) {
                    boxDimensions = createBox(boxDimensions, boxes);
                    continue;
                }
                try {
                    ArrayList<Integer> dimensionList = parseLineToDimensions(line);
                    boxDimensions.add(dimensionList);
                    validateBoxDimensions(dimensionList, boxDimensions);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: строка содержит нечисловые данные.");
                }
            }
            createBox(boxDimensions, boxes);
            return boxes;
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден.");
            return new ArrayList<>();
        } catch (InvalidAttributesException e) {
            System.out.println("Ошибка: неверные атрибуты коробки.");
            return new ArrayList<>();
        }
    }

    private ArrayList<Integer> parseLineToDimensions(String line) {
        try {
            return (ArrayList<Integer>) Arrays.stream(line.strip().split(""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Ошибка: строка содержит нечисловые данные.");
        }
    }

    private void validateBoxDimensions(ArrayList<Integer> dimensionList, ArrayList<ArrayList<Integer>> boxDimensions) throws InvalidAttributesException {
        if (dimensionList.size() > Truck.getWidth() || boxDimensions.size() > Truck.getHeight()) {
            throw new InvalidAttributesException("Ошибка: размеры коробки превышают допустимые значения.");
        }
    }

    private ArrayList<ArrayList<Integer>> createBox(ArrayList<ArrayList<Integer>> boxDimensions, ArrayList<Box> boxes) {
        if (!boxDimensions.isEmpty()) {
            try {
                boxes.add(new Box(boxDimensions));
            } catch (Exception e) {
                System.out.println("Ошибка: не удалось создать коробку.");
            }
            return new ArrayList<>();
        }
        return boxDimensions;
    }

    public ArrayList<Box> arrangeBoxesByVolume(ArrayList<Box> boxes) {
        try {
            return (ArrayList<Box>) boxes.stream()
                    .sorted((box1, box2) -> Integer.compare(box2.getVolume(), box1.getVolume()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Ошибка: не удалось отсортировать коробки.");
            return new ArrayList<>();
        }
    }
}