package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importroomsensorandreadings.ImportRoomSensorsAndReadingsController;
import pt.ipp.isep.dei.project.model.house.HouseService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportRoomSensors {

    private ImportRoomSensorsAndReadingsController controller;
    private String filePath;
    private File file;

    public ImportRoomSensors(HouseService houseService) {
        this.controller = new ImportRoomSensorsAndReadingsController(houseService);
    }

    public void run() throws FileNotFoundException {

        this.filePath = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json).");
        if (!controller.isValidFormat(this.filePath)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }

        this.file = new File(this.filePath);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }


        List<Object> sensors = controller.readFile(file, filePath);
        // Import confirmation
        if (sensors.isEmpty()) {
            System.out.println("\nSorry! The file is empty.\n");
            return;
        }
        String importConfirmation = InputValidator.confirmValidation("\nDo you really want to import the sensors? (Y/N)\n");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {

            try {
                if (controller.addSensorsToRooms()) {
                    int notImportedSensors = controller.getNumberOfNotImportedReadings();
                    if (notImportedSensors > 0) {
                        System.out.println("\nThe file was partially imported. There were " + notImportedSensors + " sensors that were not imported, due to invalid information.\n");
                        return;
                    }
                    System.out.println("\n The file was imported with success.\n");
                } else {
                    System.out.println("\nThe file was not imported. \n");
                }
            } catch (Exception e) {
                System.out.println("\nSorry! The file doesn't contain valid sensors. It was not possible to import them.\n");
            }
        }
    }

 /*   private void getFilePath() {
        this.filePath = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json).");
        if (!controller.isValidFormat(this.filePath)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }
    }

    private void fileDoesntExist() {
        this.file = new File(this.filePath);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
    }*/
}
