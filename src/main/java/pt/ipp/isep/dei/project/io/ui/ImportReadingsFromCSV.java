package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.SensorRepository;
import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsFromCSVXMLJSONController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.io.File;
import java.util.logging.Logger;

public class ImportReadingsFromCSV {
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsFromCSV.class.getName());
    private ImportReadingsFromCSVXMLJSONController controller;
    private ReadingDTO readingDTO;

    /**
     * Constructor.
     *
     * @param geographicalAreaList
     * @param sensorRepository
     */
    public ImportReadingsFromCSV(GeographicalAreaList geographicalAreaList, SensorRepository sensorRepository) {
        controller = new ImportReadingsFromCSVXMLJSONController(geographicalAreaList);
        readingDTO = ReadingMapper.newReadingDTO();
    }

    /**
     * RUN!
     */
    public void run() {
        String pathCSVFile = InputValidator.getString("\nPlease specify the absolute path of the CSV file to import (including the \".csv\" part).");
        /*if (!controller.isCSVFile(pathCSVFile)) {
            System.out.println("\nERROR: That's not a CSV file.\n");
            return;
        }*/
        File file = new File(pathCSVFile);
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        /*String notImportedReadings = importReadings(scanner);
        if (Objects.isNull(notImportedReadings)) {
            System.out.println("\nThe file is empty, therefore nothing was imported.\n");
            return;
        }
        int numberOfFailedImports = notImportedReadings.split("\n").length;
        if (numberOfFailedImports > 0) {
            String answer = InputValidator.confirmValidation("\nSome lines weren't valid and, therefore, weren't imported " +
                    "(" + numberOfFailedImports + "). Do you want to see them? (Y/N)");
            if ("y".equalsIgnoreCase(answer)) {
                System.out.println(notImportedReadings);
                return;
            } else {
                System.out.println();
                return;
            }
        }*/
        System.out.println("\nAll readings were imported successfully.\n");
    }
}