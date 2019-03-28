package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class ImportReadings {
    private ImportReadingsController controller;

    /**
     * Constructor.
     *
     * @param geographicalAreaList
     */
    public ImportReadings(GeographicalAreaList geographicalAreaList) {
        controller = new ImportReadingsController(geographicalAreaList);
    }


    public void run() throws FileNotFoundException {
        String pathFile = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json, csv, xml).");
        if (!controller.isValidFormat(pathFile)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }
        File file = new File(pathFile);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<Object> readings = controller.readFile(file, pathFile);
        // Import confirmation
        if (readings.isEmpty()) {
            System.out.println("Sorry! The file is empty.");
            return;
        }
        String importConfirmation = InputValidator.confirmValidation("Do you really want to import the readings? (Y/N)\n");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            try {
                controller.addReadingToSensorById();
            } catch (Exception e) {
                System.out.println("Sorry! The file doesn't contain valid readings. It was not possible to import them.");
                return;
            }
            if (controller.addReadingToSensorById()) {
                System.out.println("\n The file was imported with success.\n");
                return;
            } else {
                System.out.println("The file is already imported.\n");
                return;
            }
        } else {
            System.out.println("The file was not imported. \n");
            return;
        }
    }
}
