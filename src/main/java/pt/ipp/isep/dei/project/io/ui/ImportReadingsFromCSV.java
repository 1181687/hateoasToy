package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Scanner;

public class ImportReadingsFromCSV {
    private ImportReadingsFromCSVController controller;
    private ReadingDTO readingDTO;

    public ImportReadingsFromCSV(GeographicalAreaList geographicalAreaList) {
        controller = new ImportReadingsFromCSVController(geographicalAreaList);
        readingDTO = new ReadingDTO();
    }

    public void run() throws FileNotFoundException {
        String pathCSVFile = InputValidator.getString("Please specify the name of the CSV file to import (including the \".csv\" part).");
        File file = new File(pathCSVFile);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            List<String> line = CSVReader.parseLine((scanner.nextLine()));
            String sensorId = line.get(0);
            if (controller.checkIfSensorExistsById(sensorId)) {
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(line.get(1));
                LocalDateTime readingDateTime = zonedDateTime.toLocalDateTime();
                Double readingValue = Double.parseDouble(line.get(2));
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            }
        }
        System.out.println();
    }
}
