package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.services.GeoAreaService;

import java.util.List;

public class AddNewGeographicalArea {


    private AddNewGeographicalAreaController controller;

    private List<GeographicalAreaTypeDTO> geoAreaTypeDTO;

    /**
     * method constructor that receives a geoAreaService
     *
     * @param geoAreaService
     */
    public AddNewGeographicalArea(GeoAreaService geoAreaService) {
        this.controller = new AddNewGeographicalAreaController(geoAreaService);
        this.geoAreaTypeDTO = this.controller.getGeoAreaTypeList();
    }

    /**
     * method that creates and adds a new geographical area.
     */
    public void run() {

        String label1 = "Introduce the name of the new Geographical Area.";
        String id = InputValidator.getString(label1);

        String label2 = "Introduce the latitude of the Geographical Area (valid numbers between -90 and 90).";
        double latitude = InputValidator.getDoubleRange(label2, -90, 90);

        String label3 = "Introduce the longitude of the Geographical Area (valid numbers between -180 and 180).";
        double longitude = InputValidator.getDoubleRange(label3, -180, 180);

        String label4 = "Introduce the altitude of the Geographical Area.";
        double elevation = InputValidator.getDouble(label4);

        String label5 = "Please select the type of the Geographical Area you want to add: \n" + getGeoTypeListToString();
        int uiIDGeoAreatype = InputValidator.getIntRange(label5, 1, geoAreaTypeDTO.size()) - 1;

        String typeId = this.geoAreaTypeDTO.get(uiIDGeoAreatype).getGeoAreaType();


        while (controller.isGeoAreaExistant(id, latitude, longitude, elevation, typeId)) {
            System.out.println("Geographical Area already exists. Please write a new one.");
            id = InputValidator.getString(label1);
        }

        String label6 = "Introduce the description of the Geographical Area";
        String description = InputValidator.getString(label6);

        String label7 = "Introduce the width of the Geographical Area (valid numbers greater than 0).";
        double width = InputValidator.getDoublePos(label7);

        String label8 = "Introduce the length of the Geographical Area (valid numbers greater than 0).";
        double length = InputValidator.getDoublePos(label8);

        controller.addGeographicalArea(id, typeId, latitude, longitude, elevation, description, length, width);

        StringBuilder content = new StringBuilder();
        content.append("The new Geographical Area ");
        content.append(id);
        content.append(" was created with success!\n");
        System.out.println(content.toString());
    }

    /**
     * get method
     *
     * @return the content of the geo area type list
     */
    private String getGeoTypeListToString() {
        int number = 1;

        StringBuilder content = new StringBuilder();

        for (GeographicalAreaTypeDTO geoAreaType : this.geoAreaTypeDTO) {
            content.append(number);
            content.append(" - ");
            content.append(geoAreaType.getGeoAreaType());
            content.append("\n");
            number++;
        }
        return content.toString();
    }

}




