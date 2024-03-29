package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeDTO;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import java.util.List;

public class AddNewGeographicalArea {


    private AddNewGeographicalAreaController controller;

    private List<GeographicalAreaTypeDTO> geoAreaTypeDTO;

    /**
     * method constructor that receives a geoAreaService
     *
     * @param geographicalAreaService
     */
    public AddNewGeographicalArea(GeographicalAreaService geographicalAreaService, GeoAreaTypeService geoAreaTypeService) {
        this.controller = new AddNewGeographicalAreaController(geographicalAreaService, geoAreaTypeService);
        this.geoAreaTypeDTO = this.controller.getGeoAreaTypeList();
    }

    /**
     * method that creates and adds a new geographical area.
     */
    public void run() {

        GeographicalAreaDTO geoAreaDTO = new GeographicalAreaDTO();


        do {


            String label1 = "Introduce the name of the new Geographical Area.";
            String id = InputValidator.getString(label1);
            geoAreaDTO.setId(id);

            String label2 = "Introduce the latitude of the Geographical Area (valid numbers between -90 and 90).";
            double latitude = InputValidator.getDoubleRange(label2, -90, 90);
            geoAreaDTO.setLatitude(latitude);

            String label3 = "Introduce the longitude of the Geographical Area (valid numbers between -180 and 180).";
            double longitude = InputValidator.getDoubleRange(label3, -180, 180);
            geoAreaDTO.setLongitude(longitude);

            String label4 = "Introduce the altitude of the Geographical Area.";
            double elevation = InputValidator.getDouble(label4);
            geoAreaDTO.setElevation(elevation);

            String label5 = "Please select the type of the Geographical Area you want to add: \n" + getGeoTypeListToString();
            int uiIDGeoAreatype = InputValidator.getIntRange(label5, 1, geoAreaTypeDTO.size()) - 1;

            String typeId = this.geoAreaTypeDTO.get(uiIDGeoAreatype).getGeoAreaType();
            geoAreaDTO.setType(typeId);

            if (controller.isGeoAreaExistant(geoAreaDTO.getId(), geoAreaDTO.getLatitude(), geoAreaDTO.getLongitude(),
                    geoAreaDTO.getElevation(), geoAreaDTO.getType())) {
                System.out.println("Geographical Area already exists. Please write a new one.");
            }


        } while (controller.isGeoAreaExistant(geoAreaDTO.getId(), geoAreaDTO.getLatitude(), geoAreaDTO.getLongitude(),
                geoAreaDTO.getElevation(), geoAreaDTO.getType()));


        String label6 = "Introduce the description of the Geographical Area";
        String description = InputValidator.getString(label6);
        geoAreaDTO.setDescription(description);

        String label7 = "Introduce the width of the Geographical Area (valid numbers greater than 0).";
        double width = InputValidator.getDoublePos(label7);
        geoAreaDTO.setWidth(width);

        String label8 = "Introduce the length of the Geographical Area (valid numbers greater than 0).";
        double length = InputValidator.getDoublePos(label8);
        geoAreaDTO.setLength(length);

        controller.addGeographicalArea(geoAreaDTO);

        StringBuilder content = new StringBuilder();
        content.append("The new Geographical Area ");
        content.append(geoAreaDTO.getId());
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




