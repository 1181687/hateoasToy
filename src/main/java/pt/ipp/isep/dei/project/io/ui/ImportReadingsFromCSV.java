package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.ImportReadingsFromCSVController;
import pt.ipp.isep.dei.project.model.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.utils.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
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
        String pathCSVFile = InputValidator.getString("Please specify the path of the CSV file to import.");
        File file = new File(pathCSVFile);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            List<String> line = CSVReader.parseLine((scanner.nextLine()));
            String sensorId = line.get(0);
            if (controller.checkIfSensorExistsById(sensorId)) {
                LocalDateTime readingDateTime = LocalDateTime.parse(line.get(1));
                Double readingValue = Double.parseDouble(line.get(2));
                readingDTO.setDateTime(readingDateTime);
                readingDTO.setValue(readingValue);
                controller.addReadingToSensor(readingDTO);
            }
            scanner.close();
        }
    }
}
