package pt.ipp.isep.dei.project.io.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.GeoAreaRepository;
import pt.ipp.isep.dei.project.GeoAreaService;
import pt.ipp.isep.dei.project.SensorRepository;
import pt.ipp.isep.dei.project.controllers.importgeoareasfromjsonorxmlcontroller.ImportGeoAreasFromJSONOrXMLController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Objects;

@Service

public class ImportGeoAreasFromJSONOrXML {

    private ImportGeoAreasFromJSONOrXMLController controller;

    @Autowired
    GeoAreaRepository geoAreaRepository = GeoAreaService.getInstance().getGeoAreaRepository();


    public ImportGeoAreasFromJSONOrXML(GeographicalAreaList geoList, SensorRepository sensorRepository, GeoAreaRepository geoAreaRepository) {
        this.controller = new ImportGeoAreasFromJSONOrXMLController(geoList, sensorRepository, geoAreaRepository);
    }

    public void jsonGeoAreaSensors() throws FileNotFoundException {

        // Write the path
        String path = InputValidator.getString("Please specify the name of the file to import.");
        File file = new File(path);


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

        /*String areaGeo1 = " > " + dtoList.get(0).getId() + " - Number of sensors: " + dtoList.get(0).getSensors().size();
        String areaGeo2 = " > " + dtoList.get(1).getId() + " - Number of sensors: " + dtoList.get(0).getSensors().size();

        System.out.println(confirmOptions + "\n" + areaGeo1 + "\n" + areaGeo2 + "\n");*/

        // Import confirmation
        String importConfirmation = InputValidator.confirmValidation("Do you want to import these geographic areas and their sensors? (Y/N)");
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

}