package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImportReadingsFromCSV {
    private ImportReadingsFromCSVController controller;
    private ReadingDTO readingDTO;
    private final static Logger LOGGER = Logger.getLogger(ImportReadingsFromCSV.class.getName());

    public ImportReadingsFromCSV(GeographicalAreaList geographicalAreaList) {
        controller = new ImportReadingsFromCSVController(geographicalAreaList);
        readingDTO = ReadingMapper.newReadingDTO();
    }

    public void run() {
        String pathCSVFile = InputValidator.getString("\nPlease specify the name of the CSV file to import (including the \".csv\" part).");
        Scanner scanner = checkIfFileExistsAndCreateScanner(pathCSVFile);
        if (Objects.isNull(scanner)) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        scanner.nextLine();
        if (!scanner.hasNext()) {
            System.out.println("\nThe file is empty, therefore nothing was imported.\n");
            return;
        }
        int numberOfFailedImports = readLinesAndImportReadings(scanner);
        if (numberOfFailedImports > 0) {
            System.out.println("Some readings weren't imported.\n");
            return;
        }
        System.out.println("\nAll readings were imported successfully.\n");
    }

    /**
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param pathCSVFile Path of the CSV file.
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */
    public Scanner checkIfFileExistsAndCreateScanner(String pathCSVFile) {
        File file = new File(pathCSVFile);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            scanner = null;
        }
        return scanner;
    }

    /**
     * Method that checks if a datetime is valid (in order to be used to create a reading) and store it.
     *
     * @param dateTime Datetime to be analysed.
     * @return Double with the value.
     */
    public LocalDateTime checkDateTimeAndStoreIt(String dateTime) {
        try {
            ZonedDateTime.parse(dateTime);
        } catch (DateTimeParseException e) {
            return null;
        }
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        if (controller.isDateTimeBeforeSensorStartingDate(localDateTime)) {
            return null;
        }
        return localDateTime;
    }

    /**
     * Method that checks if a value is valid (in order to be used to create a reading) and store it.
     *
     * @param value Value to be analysed.
     * @return Double with the value.
     */
    public Double checkValueAndStoreIt(String value) {
        Double readingValue;
        try {
            readingValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return readingValue;
    }

    /**
     * Method that stores the information of not imported readings (corresponding to lines in the file).
     *
     * @param line Line to be turned into a String.
     * @return String with information about the not imported reading.
     */
    public String storeNotImportedReadings(List<String> line) {
        StringBuilder notImportedReadings = new StringBuilder();
        notImportedReadings.append("Id: " + line.get(0) + "  ");
        notImportedReadings.append("Timestamp/Date: " + line.get(1).trim() + "  ");
        notImportedReadings.append("Value: " + line.get(2) + "\n");
        return notImportedReadings.toString();
    }

    /**
     * Method that reads each line of the scanner, verifies the information (if it is possible to use that information
     * to create a readingDTO or not) and, subsequently, outputs the number of failed imports.
     *
     * @param scanner Information to be analysed.
     * @return Integer corresponding to the number of failed imports.
     */
    public int readLinesAndImportReadings(Scanner scanner) {
        FileHandler fh;
        try {
            fh = new FileHandler("log/outputErrors.log");
        } catch (IOException e) {
            fh = null;
        }
        LOGGER.addHandler(fh);
        int numberOfFailedImports = 0;
        while (scanner.hasNext()) {
            List<String> line = CSVReader.parseLine((scanner.nextLine()));
            String sensorId = line.get(0);
            String dateTime = line.get(1);
            String value = line.get(2);
            if (controller.checkIfSensorExistsById(sensorId)) {
                LocalDateTime readingDateTime = checkDateTimeAndStoreIt(dateTime);
                Double readingValue = checkValueAndStoreIt(value);
                if (Objects.isNull(readingDateTime) || Objects.isNull(readingValue)) {
                    LOGGER.log(Level.WARNING, "Line not parsed due to invalid information: "
                            + storeNotImportedReadings(line));
                    numberOfFailedImports += 1;
                    continue;
                }
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            } else {
                LOGGER.log(Level.WARNING, "There is no sensor in the system with the id " + sensorId + ".\n");
                numberOfFailedImports += 1;
            }
        }
        return numberOfFailedImports;
    }
}

