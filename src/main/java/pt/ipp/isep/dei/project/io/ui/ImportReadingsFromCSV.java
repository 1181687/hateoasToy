package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importReadingsFromCSVController.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
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

    public void run() throws FileNotFoundException {
        String pathCSVFile = InputValidator.getString("Please specify the name of the CSV file to import (including the \".csv\" part).");
        File file = new File(pathCSVFile);
        Scanner scanner = new Scanner(file);
        StringBuilder notImportedReadings = new StringBuilder();
        scanner.nextLine();
        while (scanner.hasNext()) {
            List<String> line = CSVReader.parseLine((scanner.nextLine()));
            String sensorId = line.get(0);
            String dateTime = line.get(1);
            String value = line.get(2);
            if (controller.checkIfSensorExistsById(sensorId)) {
                LocalDateTime readingDateTime = checkDateTimeAndStoreIt(dateTime);
                if (Objects.isNull(readingDateTime)) {
                    notImportedReadings.append(storeNotImportedReadings(line));
                    continue;
                }
                Double readingValue = checkValueAndStoreIt(value);
                if (Objects.isNull(readingValue)) {
                    notImportedReadings.append(storeNotImportedReadings(line));
                    continue;
                }
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            } else {
                notImportedReadings.append(storeNotImportedReadings(line));
            }
        }
        if (!Objects.isNull(notImportedReadings)) {
            System.out.println("\nThe following readings weren't imported:\n" + notImportedReadings);
            return;
        }
        System.out.println("\nAll readings were imported successfully.\n");
        if (!Objects.isNull(scanner)) {
            scanner.close();
        }
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
}
