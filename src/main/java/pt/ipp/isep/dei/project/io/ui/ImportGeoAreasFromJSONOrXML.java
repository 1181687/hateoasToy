package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller.ImportGeoAreasFromJSONOrXMLController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;

public class ImportGeoAreasFromJSONOrXML {

    private ImportGeoAreasFromJSONOrXMLController controller;

    public ImportGeoAreasFromJSONOrXML(GeographicalAreaList geoList) {
        this.controller = new ImportGeoAreasFromJSONOrXMLController(geoList);
    }

    public void run() throws FileNotFoundException {

        // Write the path
        String path = InputValidator.getString("Please specify the name of the file to import.");
        File file = new File(path);
        FileReader reader = checkIfFileExistsAndCreateFileReader(file);


        if (Objects.isNull(reader)) {
            System.out.println("\nERROR: There's no such file with that name.\n");
            return;
        }
        List<Object> dtoList = controller.readFile(file);
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
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these geographic areas and their sensors? (Y/N)");
        if ("Y".equals(importConfirmation) || "y".equals(importConfirmation)) {
            if (controller.importGeographicalAreaAndSensors(file)) {
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
     * Method that checks if a file is valid (if it exists) and creates a scanner based on it.
     *
     * @param file Path of the file
     * @return Null scanner if there's no such file with the specified name; or a valid scanner if the file exists.
     */
    public FileReader checkIfFileExistsAndCreateFileReader(File file) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            fileReader = null;
        }
        return fileReader;
    }


}

