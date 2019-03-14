package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importreadingsfromcsvcontroller.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ImportReadingsFromCSV {
    private ImportReadingsFromCSVController controller;
    private ReadingDTO readingDTO;

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
        List<Object> readingResults = readLinesAndImportReadings(scanner);
        int numberOfFailedImports = (int) readingResults.get(0);
        StringBuilder notImportedReadings = (StringBuilder) readingResults.get(1);
        if (numberOfFailedImports > 0) {
            String answer = InputValidator.confirmValidation("\nSome readings weren't imported "
                    + "(" + numberOfFailedImports + "). Do you want to see them? (Y/N)");
            if ("Y".equals(answer) || "y".equals(answer)) {
                System.out.println("\nThe following readings weren't imported:\n" + notImportedReadings);
                return;
            } else {
                return;
            }
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
        return zonedDateTime.toLocalDateTime();
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
     * @param line Line to be turned into a StringBuilder.
     * @return StringBuilder with information about the not imported reading.
     */
    public StringBuilder storeNotImportedReadings(List<String> line) {
        StringBuilder notImportedReadings = new StringBuilder();
        notImportedReadings.append("Id: " + line.get(0) + "  ");
        notImportedReadings.append("Timestamp/Date: " + line.get(1) + "  ");
        notImportedReadings.append("Value: " + line.get(2) + "\n");
        return notImportedReadings;
    }

    /**
     * Method that reads each line of the scanner, verifies the information (if it is possible to use that information
     * to create a readingDTO or not) and, subsequently, outputs the number of failed imports and a StringBuilder that
     * shows each failure.
     *
     * @param scanner Information to be analysed.
     * @return Integer corresponding to the number of failed imports and a StringBuilder with the information about
     * each failed import.
     */
    public List<Object> readLinesAndImportReadings(Scanner scanner) {
        List<Object> objectList = new ArrayList<>();
        StringBuilder notImportedReadings = new StringBuilder();
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
                    notImportedReadings.append(storeNotImportedReadings(line));
                    numberOfFailedImports += 1;
                    continue;
                }
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            } else {
                notImportedReadings.append(storeNotImportedReadings(line));
                numberOfFailedImports += 1;
            }
        }
        objectList.add(numberOfFailedImports);
        objectList.add(notImportedReadings);
        return objectList;
    }
}
