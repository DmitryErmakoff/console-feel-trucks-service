package ru.d3m4k.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSelectorService {

    public static List<File> listTxtFiles(String directoryPath) {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        List<File> txtFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                txtFiles.add(file);
            }
        }
        return txtFiles;
    }

    public static File selectFileFromResources() {
        String resourceDirectory = "src/main/resources";  // Путь к папке resource
        List<File> txtFiles = listTxtFiles(resourceDirectory);

        if (txtFiles.isEmpty()) {
            System.out.println("Нет доступных .txt файлов в папке resources.");
            return null;
        }

        System.out.println("Доступные файлы для выбора:");
        for (int i = 0; i < txtFiles.size(); i++) {
            System.out.println(i + 1 + ": " + txtFiles.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите номер файла: ");
        int fileIndex = scanner.nextInt() - 1;

        if (fileIndex >= 0 && fileIndex < txtFiles.size()) {
            return txtFiles.get(fileIndex);
        } else {
            System.out.println("Некорректный выбор файла.");
            return null;
        }
    }
}


