package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.utils.CSVReader;
import pt.ipp.isep.dei.project.utils.JSONReaderReadings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportReadingsFromJSONCSVXML {
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsFromCSV.class.getName());
    private ImportReadingsFromCSVController controller;
    private ReadingDTO readingDTO;
    private CSVReader csvReader = new CSVReader();

    /**
     * Constructor.
     *
     * @param geographicalAreaList
     */
    public ImportReadingsFromJSONCSVXML(GeographicalAreaList geographicalAreaList) {
        controller = new ImportReadingsFromCSVController(geographicalAreaList);
        readingDTO = ReadingMapper.newReadingDTO();
    }


    /**
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param fileJSON Path of the JSON file.
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */

    public FileReader checkIfFileExistsAndCreateJsonFileReader(File fileJSON) {
        FileReader file;
        try {
            file = new FileReader(fileJSON);
        } catch (FileNotFoundException e) {
            file = null;
        }
        return file;
    }

    public void run() {
        String pathFile = InputValidator.getString("Please specify the name of the file you would like to import (extensions accepted: json, csv, xml).");
        File file = new File(pathFile);
        FileReader reader = checkIfFileExistsAndCreateJsonFileReader(file);
        if (Objects.isNull(reader)) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<ReadingDTO> objList = JSONReaderReadings.readJSONReadingFileToList(reader);
        if (Objects.isNull(objList) || objList.isEmpty()) {
            LOGGER.log(Level.WARNING, "Line not parsed due to invalid information.\n");
            return;
        }
        // Import confirmation
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these readings? (Y/N)\nThere are " + objList.size() + " readings.\n");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            if (controller.addReadingToSensorById(objList)) {
                System.out.println("\n The JSON file was imported with success.\n");
                return;
            } else {
                System.out.println("The file is already imported.\n");
                return;
            }
        } else {
            System.out.println("The JSON file was not imported. \n");
            return;
        }
    }
}
