package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importroomsensors.ImportRoomSensorsController;
import pt.ipp.isep.dei.project.services.RoomSensorService;
import pt.ipp.isep.dei.project.services.RoomService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportRoomSensors {
    private ImportRoomSensorsController controller;
    private List<Object> sensorDTOs;

    /**
     * Constructor.
     *
     * @param roomService       Service to be used.
     * @param roomSensorService Service to be used.
     */
    public ImportRoomSensors(RoomService roomService, RoomSensorService roomSensorService) {
        this.controller = new ImportRoomSensorsController(roomService, roomSensorService);
    }

    /**
     * RUN!
     */
    public void run() throws FileNotFoundException {
        String filePath = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json).");
        if (!isValidFormat(filePath)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        sensorDTOs = controller.readFile(file, filePath);
        if (sensorDTOs.isEmpty()) {
            System.out.println("\nSorry! The file is empty.\n");
            return;
        }
        showInformation();
        String importConfirmation = InputValidator.confirmValidation("\nDo you really want to import the sensors? (Y/N)\n");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            try {
                if (controller.importSensors()) {
                    int notImportedSensors = controller.getNumberOfNotImportedReadings();
                    if (notImportedSensors > 0) {
                        System.out.println("\nThe file was partially imported. There were " + notImportedSensors + " sensors that were not imported, due to invalid information.\n");
                        return;
                    }
                    System.out.println("\n The file was imported with success.\n");
                } else {
                    System.out.println("\n The file doesn't contain valid sensors. \n");
                }
            } catch (Exception e) {
                System.out.println("\nSorry! The file doesn't contain valid sensors. It was not possible to import them.\n");
            }
        }
    }

    /**
     * Method that checks if the file is in a valid format (JSON).
     *
     * @param path Path of the file.
     * @return True or false.
     */
    private boolean isValidFormat(String path) {
        return path.endsWith(".json");
    }

    /**
     * Method that shows a summary of the information that is available to be imported.
     */
    private void showInformation() {
        int numberOfSensors = this.sensorDTOs.size();
        String header = "\n The chosen file contains: \n";
        StringBuilder content = new StringBuilder();
        content.append(header);
        content.append("\n");
        content.append(" > ");
        content.append(numberOfSensors);
        content.append(" sensors to import to the house.");
        content.append("\n");
        System.out.println(content.toString());
    }
}
