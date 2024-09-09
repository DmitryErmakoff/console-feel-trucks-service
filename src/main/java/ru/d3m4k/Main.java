package ru.d3m4k;

import ru.d3m4k.entity.Box;
import ru.d3m4k.entity.Truck;
import ru.d3m4k.service.BoxPlacementService;
import ru.d3m4k.service.BoxService;
import ru.d3m4k.service.FileSelectorService;
import ru.d3m4k.service.TruckService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoxService boxService = new BoxService();
        TruckService truckService = new TruckService();
        BoxPlacementService boxPlacementService = new BoxPlacementService();
        File file = FileSelectorService.selectFileFromResources();
        if (file == null) {
            System.exit(1);
        }

        ArrayList<Box> boxList = boxService.loadBoxesFromFile(file.getPath());

        boxList = boxService.arrangeBoxesByVolume(boxList);
        List<Truck> truckList = boxPlacementService.fillTrucksWithBoxes(boxList);
        truckList.forEach(truckService::printTruck);
    }
}