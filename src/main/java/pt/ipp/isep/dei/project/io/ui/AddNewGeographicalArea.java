package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddNewGeographicalAreaController;
import pt.ipp.isep.dei.project.services.GeoAreaService;

public class AddNewGeographicalArea {


    private AddNewGeographicalAreaController controller;

    /**
     * method constructor that receives a geoAreaService
     *
     * @param geoAreaService
     */
    public AddNewGeographicalArea(GeoAreaService geoAreaService) {
        this.controller = new AddNewGeographicalAreaController(geoAreaService);
    }

    /**
     * method that creates and adds a new geographical. First asks the user to give the valid parameters necessary
     * to create the new room and then invokes the controller methods newroom and addRoom. Finally if the
     * addRoom returns true, a informative message of the sucess of it, is apresented to the user.
     */
    public void run() {

        String label1 = "What is the name of the Geographical Area you want to add?";
        String id = InputValidator.getString(label1);

        String label2 = "What is the latitude of the Geographical Area you want to add?";
        double latitude = InputValidator.getDoubleRange(label2, -90, 90);

        String label3 = "What is the longitude of the Geographical Area you want to add?";
        double longitude = InputValidator.getDoubleRange(label3, -180, 180);

        String label4 = "What is the altitude of the Geographical Area you want to add?";
        double elevation = InputValidator.getDouble(label4);

        String label5 = "What is the type of the Geographical Area you want to add?";
        String type = InputValidator.getString(label5);

        while (controller.isGeoAreaExistant(id, latitude, longitude, elevation, type)) {
            System.out.println("Geographical Area already exists. Please write a new one.");
            id = InputValidator.getString(label1);
        }
        String label6 = "What is the description of the Geographical Area you want to add?";
        String description = InputValidator.getString(label6);

        String label7 = "What is the width of the Geographical Area you want to add?";
        double width = InputValidator.getDoublePos(label7);

        String label8 = "What is the length of the Geographical Area you want to add?";
        double length = InputValidator.getDoublePos(label8);

        controller.addGeographicalArea(id, type, latitude, longitude, elevation, description, length, width);

        StringBuilder content = new StringBuilder();
        content.append("The new Geographical Area ");
        content.append(id);
        content.append(" was created with success!\n");
        System.out.println(content.toString());
    }
}

