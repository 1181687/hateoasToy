package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController.ImportReadingsFromJSONController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.utils.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class ImportReadingsFromJSON {

    private ImportReadingsFromJSONController controller;


    public ImportReadingsFromJSON(GeographicalAreaList geoList) {
        this.controller = new ImportReadingsFromJSONController(geoList);
    }

    public void run() {

        // Write the path
        String pathJSONFile = InputValidator.getString("Please specify the name of the JSON file to import.");
        FileReader reader = checkIfFileExistsAndCreateFileReader(pathJSONFile);
        if (Objects.isNull(reader)) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<GeographicalAreaDTO> dtoList = JSONReader.readJSONFileToList(reader);
        if (Objects.isNull(dtoList) || dtoList.isEmpty()) {
            System.out.println("\nThe information on the file is not valid to be imported.\n");
            return;
        }

        // Content of the choosen file
        String confirmOptions = "\n This is the content of the chosen file: \n";

        String areaGeo1 = " > " + dtoList.get(0).getId() + " - Number of sensors: " + dtoList.get(0).getSensors().size();
        String areaGeo2 = " > " + dtoList.get(1).getId() + " - Number of sensors: " + dtoList.get(0).getSensors().size();

        System.out.println(confirmOptions + "\n" + areaGeo1 + "\n" + areaGeo2 + "\n");


        // Import confirmation
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these geographic areas and their sensors? (Y/N)");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            controller.importGeographicalAreaAndSensors(dtoList);
            System.out.println("\n The JSON file was imported with success.\n");
            return;
        } else {
            System.out.println("The JSON file was not imported. \n");
            return;
        }


    }

    /**
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param pathCSVFile Path of the CSV file.
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */
    public FileReader checkIfFileExistsAndCreateFileReader(String pathCSVFile) {
        FileReader file;
        try {
            file = new FileReader(pathCSVFile);
        } catch (FileNotFoundException e) {
            file = null;
        }
        return file;
    }
}

