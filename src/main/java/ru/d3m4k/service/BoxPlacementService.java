package ru.d3m4k.service;

import ru.d3m4k.entity.Box;
import ru.d3m4k.entity.Truck;

import java.util.ArrayList;
import java.util.List;

public class BoxPlacementService {

    public List<Truck> fillTrucksWithBoxes(ArrayList<Box> boxes) {
        List<Truck> trucks = new ArrayList<>();

        while (!boxes.isEmpty()) {
            int currentHeight = 0;
            Truck truck = new Truck();
            List<Box> leftoverBoxes = new ArrayList<>();

            while (!boxes.isEmpty()) {
                Box box = boxes.remove(0);
                if (currentHeight + box.getLength() > Truck.getHeight()) {
                    leftoverBoxes.add(box);
                    continue;
                }

                truck.addBox(box, 0, currentHeight);
                int nextHeight = currentHeight;
                currentHeight += box.getLength();

                int currentWidth = box.getWidth();
                while (!boxes.isEmpty()) {
                    int availableHeight = Truck.getHeight() - nextHeight;
                    int availableWidth = Truck.getWidth() - currentWidth;
                    List<Box> fittingBoxes = boxes.stream()
                            .filter(b -> canFitBox(b, availableHeight, availableWidth))
                            .toList();

                    if (fittingBoxes.isEmpty()) break;

                    Box fittingBox = fittingBoxes.get(0);
                    boxes.remove(fittingBox);
                    truck.addBox(fittingBox, currentWidth, nextHeight);

                    if (availableWidth > fittingBox.getWidth()) {
                        currentWidth += fittingBox.getWidth();
                    } else {
                        nextHeight += fittingBox.getLength();
                        if (nextHeight >= currentHeight) break;
                    }
                }
            }
            boxes.addAll(leftoverBoxes);
            trucks.add(truck);
        }
        return trucks;
    }

    private boolean canFitBox(Box box, int availableHeight, int availableWidth) {
        return box.getVolume() <= (availableHeight * availableWidth)
                && box.getWidth() <= availableWidth
                && box.getLength() <= availableHeight;
    }
}

