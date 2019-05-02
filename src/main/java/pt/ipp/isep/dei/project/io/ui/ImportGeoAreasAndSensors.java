package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller.ImportGeoAreasAndSensorsController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class ImportGeoAreasAndSensors {
    private ImportGeoAreasAndSensorsController controller;
    private List<Object> geoAreaDTOs;

    /**
     * Constructor.
     *
     * @param geoAreaService       Service to be used.
     * @param geoAreaSensorService Service to be used.
     */
    public ImportGeoAreasAndSensors(GeographicalAreaService geoAreaService, GeoAreaSensorService geoAreaSensorService) {
        this.controller = new ImportGeoAreasAndSensorsController(geoAreaService, geoAreaSensorService);
    }

    /**
     * RUN!
     */
    public void run() throws FileNotFoundException {
        String path = InputValidator.getString("\nPlease specify the name of the file to import.");
        File file = new File(path);
        if (!this.isValidFormat(path)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }
        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        this.geoAreaDTOs = controller.readFile(file, path);
        if (Objects.isNull(geoAreaDTOs) || geoAreaDTOs.isEmpty()) {
            System.out.println("\nThe information on the file is not valid to be imported.\n");
            return;
        }
        showInformation();
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these geographical areas and their sensors? (Y/N)");
        if ("Y".equalsIgnoreCase(importConfirmation)) {
            if (controller.importGeoAreasAndSensors()) {
                System.out.println("\nThe file was imported with success.\n");
            } else {
                System.out.println("The file is already imported.\n");
            }
        } else {
            System.out.println("The file was not imported. \n");
        }
    }

    /**
     * Method that checks if the file is in a valid format (JSON or XML).
     *
     * @param path Path of the file.
     * @return True or false.
     */
    private boolean isValidFormat(String path) {
        return path.endsWith(".json") || path.endsWith(".xml");
    }

    /**
     * Method that shows a summary of the information that is available to be imported.
     */
    private void showInformation() {
        String confirmOptions = "\nThis is the content of the chosen file: \n";
        StringBuilder content = new StringBuilder();
        content.append(confirmOptions);
        content.append("\n");
        for (Object areaGeo : geoAreaDTOs) {
            GeographicalAreaDTO geoDTO = (GeographicalAreaDTO) areaGeo;
            String id = geoDTO.getId();
            int numberOfSensors = geoDTO.getSensors().size();
            content.append(" > ");
            content.append(id);
            content.append("\n");
            content.append(" - Number of sensors: ");
            content.append(numberOfSensors);
            content.append("\n");
            content.append("\n");
        }
        content.append("\r");
        System.out.println(content);
    }
}