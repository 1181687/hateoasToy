package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingscontroller.ImportGeoAreaReadingsController;
import pt.ipp.isep.dei.project.controllers.importreadingscontroller.ImportRoomReadingsController;
import pt.ipp.isep.dei.project.services.GeoAreaService;
import pt.ipp.isep.dei.project.services.RoomSensorService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ImportReadings {
    private ImportGeoAreaReadingsController geoAreaReadingsController;
    private ImportRoomReadingsController roomReadingsController;

    /**
     * Constructor.
     *
     * @param geographicalAreaService
     */
    public ImportReadings(GeoAreaService geographicalAreaService, RoomSensorService roomSensorService) {
        geoAreaReadingsController = new ImportGeoAreaReadingsController(geographicalAreaService);
        roomReadingsController = new ImportRoomReadingsController(roomSensorService);
    }


    public void run(int option) throws FileNotFoundException {
        String pathFile = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json, csv, xml).\n");
        if (!geoAreaReadingsController.isValidFormat(pathFile)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<Object> readings = new ArrayList<>();
        if (option==1){
            readings = geoAreaReadingsController.readFile(file, pathFile);
        }
        if (option==2){
            readings = roomReadingsController.readFile(file, pathFile);
        }

        // Import confirmation
        if (readings.isEmpty()) {
            System.out.println("\nSorry! The file is empty.\n");
            return;
        }
        String importConfirmation = InputValidator.confirmValidation("\nDo you really want to import the readings? (Y/N)\n");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            try {
                int notImportedReadings = 0;
                boolean chosenController = false;
                if (option == 1) {
                    chosenController = geoAreaReadingsController.addReadingToGeoAreaSensorById();
                    notImportedReadings = geoAreaReadingsController.getNumberOfNotImportedReadings();
                }
                if (option == 2) {
                    chosenController = roomReadingsController.addReadingToRoomSensorById();
                    notImportedReadings = roomReadingsController.getNumberOfNotImportedReadings();
                }
                if (chosenController) {
                    if (notImportedReadings > 0) {
                        System.out.println("\nThe file was partially imported. There were " + notImportedReadings + " readings that were not imported, due to invalid information.\n");
                        return;
                    }
                    System.out.println("\n The file was imported with success.\n");
                } else {
                    System.out.println("\nThe file was not imported. \n");
                }
            } catch (Exception e) {
                System.out.println("\nSorry! The file doesn't contain valid readings. It was not possible to import them.\n");
            }
        }
    }
}