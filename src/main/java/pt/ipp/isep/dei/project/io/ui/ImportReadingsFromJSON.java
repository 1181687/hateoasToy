package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.importReadingsFromJSONController.ImportReadingsFromJSONController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaMapping;
import pt.ipp.isep.dei.project.model.sensor.SensorDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ImportReadingsFromJSON {

    private ImportReadingsFromJSONController controller;
    private GeographicalAreaDTO geographicalAreaDTO;
    private SensorDTO sensorDTO;
    private List<GeographicalAreaDTO> geographicalAreaDTOS;

    public ImportReadingsFromJSON(GeographicalAreaList geoList) {
        this.controller = new ImportReadingsFromJSONController(geoList);
        geographicalAreaDTO = GeographicalAreaMapping.newGeoAreaDTO();
    }

    public void run() throws FileNotFoundException {
        String pathJSONFile = InputValidator.getString("Please specify the name of the JSON file to import.");
        File file = new File(pathJSONFile);
        Scanner scanner = new Scanner(file);
        if (Objects.isNull(scanner)) {
            scanner.close();
        } else {
            controller.importGeographicalAreaAndSensors(geographicalAreaDTOS);
        }

    }
}
