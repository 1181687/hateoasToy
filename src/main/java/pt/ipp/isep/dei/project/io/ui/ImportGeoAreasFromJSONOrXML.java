package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller.ImportGeoAreasFromJSONOrXMLController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

public class ImportGeoAreasFromJSONOrXML {

    private ImportGeoAreasFromJSONOrXMLController controller;


    public ImportGeoAreasFromJSONOrXML(GeographicalAreaService geoList) {
        this.controller = new ImportGeoAreasFromJSONOrXMLController(geoList);
    }

    public void jsonGeoAreaSensors() throws FileNotFoundException {

        // Write the path
        String path = InputValidator.getString("Please specify the name of the file to import.");
        File file = new File(path);
        if (!isValidFormat(path)) {
            System.out.println("\nERROR: Please insert a valid format.\n");
            return;
        }

        if (!file.exists()) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<Object> dtoList = controller.readFile(file, path);
        if (Objects.isNull(dtoList) || dtoList.isEmpty()) {
            System.out.println("\nThe information on the file is not valid to be imported.\n");
            return;
        }

        // Content of the choosen file
        String confirmOptions = "\n This is the content of the chosen file: \n";

        StringBuilder content = new StringBuilder();
        content.append(confirmOptions);
        content.append("\n");
        for (Object areaGeo : dtoList) {
            GeographicalAreaDTO geoDTO = (GeographicalAreaDTO) areaGeo;
            String id = geoDTO.getId();
            int numberOfSensors = geoDTO.getSensors().size();
            content.append(" > ");
            content.append(id);
            content.append("\n");
            content.append(" - Number of sensors: ");
            content.append(numberOfSensors);
            content.append("\n");
        }

        System.out.println(content);


        // Import confirmation
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these geographical areas and their sensors? (Y/N)");
        if ("Y".equalsIgnoreCase(importConfirmation)) {
            if (controller.importGeographicalAreaAndSensors()) {
                System.out.println("\n The file was imported with success.\n");
                return;
            } else {
                System.out.println("The file is already imported.\n");
                return;
            }
        } else {
            System.out.println("The file was not imported. \n");
            return;
        }
    }

    /**
     * Check if format is valid.
     *
     * @param fileName
     * @return
     */
    private boolean isValidFormat(String fileName) {
        return fileName.endsWith(".json") || fileName.endsWith(".xml");
    }
}