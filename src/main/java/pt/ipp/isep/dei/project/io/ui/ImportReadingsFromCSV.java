package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsFromCSVXMLJSONController;
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
    private static final Logger LOGGER = Logger.getLogger(ImportReadingsFromCSV.class.getName());
    private ImportReadingsFromCSVXMLJSONController controller;
    private ReadingDTO readingDTO;
    private CSVReader csvReader = new CSVReader();
    /**
     * Constructor.
     *
     * @param geographicalAreaList
     */
    public ImportReadingsFromCSV(GeographicalAreaList geographicalAreaList) {
        controller = new ImportReadingsFromCSVXMLJSONController(geographicalAreaList);
        readingDTO = ReadingMapper.newReadingDTO();
    }

    /**
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param pathCSVFile Path of the CSV file.
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */
    private static Scanner checkIfFileExistsAndCreateScanner(String pathCSVFile) {
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
     * Method that checks if a value is valid (in order to be used to create a reading) and store it.
     *
     * @param value Value to be analysed.
     * @return Double with the value.
     */
    private static Double checkValueAndStoreIt(String value) {
        Double readingValue;
        try {
            readingValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
        return readingValue;
    }

    /**
     * Method that checks if a datetime is valid (in order to be used to create a reading) and store it.
     *
     * @param dateTime Datetime to be analysed.
     * @return Double with the value.
     */
    private LocalDateTime checkDateTimeAndStoreIt(String dateTime) {
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
     * Method that stores the information of not imported readings (corresponding to each line in the file).
     *
     * @param line Line to be turned into a String.
     * @return String with information about the not imported reading.
     */
    private static String storeNotImportedReadings(List<String> line) {
        StringBuilder notImportedReadings = new StringBuilder();
        notImportedReadings.append("Id: " + line.get(0) + "  ");
        notImportedReadings.append("Timestamp/Date: " + line.get(1).trim() + "  ");
        notImportedReadings.append("Value: " + line.get(2) + "\n");
        return notImportedReadings.toString();
    }

    /**
     * Method that configures the log file, using a FileHandler object to send log information to the specified log file.
     * The last line is responsible for not letting the information show up in the console.
     */
    private static void configLogFile() {
        FileHandler fh;
        try {
            fh = new FileHandler("log/outputErrors.log");
        } catch (IOException e) {
            fh = null;
        }
        LOGGER.addHandler(fh);
        LOGGER.setUseParentHandlers(false);
    }

    /**
     * Method that reads each line of the scanner, verifies the information (if it is possible to use that information
     * to create a readingDTO or not) and, subsequently, outputs the number of failed imports.
     *
     * @param file Information to be analysed.
     * @return Integer corresponding to the number of failed imports.
     */
    @SuppressWarnings("unchecked")
    private String importReadings(File file) throws FileNotFoundException {

        configLogFile();
        StringBuilder notImportedReadings = new StringBuilder();
        List<Object> allLines = csvReader.readFile(file);
        if (allLines.isEmpty()) {
            return null;
        }
        for (Object line : allLines) {
            List<String> line2 = (List<String>) line;

            String sensorId = line2.get(0);
            String dateTime = line2.get(1);
            String value = line2.get(2);
            if (controller.checkIfSensorExistsById(sensorId)) {
                LocalDateTime readingDateTime = checkDateTimeAndStoreIt(dateTime);
                Double readingValue = checkValueAndStoreIt(value);
                if (Objects.isNull(readingDateTime) || Objects.isNull(readingValue)) {
                    notImportedReadings.append(storeNotImportedReadings(line2));
                    LOGGER.log(Level.WARNING, "Line not parsed due to invalid information: "
                            + storeNotImportedReadings(line2));
                    continue;
                }
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            } else {
                notImportedReadings.append(storeNotImportedReadings(line2));
                LOGGER.log(Level.WARNING, "There is no sensor in the system with the id " + sensorId + ".\n");
            }
        }
        return notImportedReadings.toString();
    }

    /**
     * RUN!
     */
    public void run() throws FileNotFoundException {
        String pathCSVFile = InputValidator.getString("\nPlease specify the absolute path of the CSV file to import (including the \".csv\" part).");
        if (!csvReader.isCSVFile(pathCSVFile)) {
            System.out.println("\nERROR: That's not a CSV file.\n");
            return;
        }
        Scanner scanner = checkIfFileExistsAndCreateScanner(pathCSVFile);
        File file = new File(pathCSVFile);

        if (Objects.isNull(scanner)) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        String notImportedReadings = importReadings(file);
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
        }
        System.out.println("\nAll readings were imported successfully.\n");
    }
}