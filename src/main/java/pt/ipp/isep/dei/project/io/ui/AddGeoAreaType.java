package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;


/**
 * US001 As an Administrator, I want to I want to add a new type of geographical area, in order to be able to create a
 * classification of geographical areas.
 */

public class AddGeoAreaType {

    private AddGeoAreaTypeController controller;

    public AddGeoAreaType(GeoAreaTypeService geoAreaTypeService) {
        this.controller = new AddGeoAreaTypeController(geoAreaTypeService);
    }

    public void run() {
        String label = "Introduce a new Geographical Area type.";
        String id = InputValidator.getString(label);

        if (this.controller.createGeoAreaType(id)) {
            System.out.println("Success!");
        } else {
            System.out.println("Try another Geographical Area type!");
        }
    }
}
