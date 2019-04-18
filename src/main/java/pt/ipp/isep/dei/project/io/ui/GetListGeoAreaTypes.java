package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.services.GeoAreaTypeService;

/**
 * US002 As an Administrator, I want to get a list of existing geographical area types.
 */

public class GetListGeoAreaTypes {
    private GetListGeoAreaTypesController controller;

    public GetListGeoAreaTypes(GeoAreaTypeService geoAreaTypeService) {
        this.controller = new GetListGeoAreaTypesController(geoAreaTypeService);
    }


    public void run() {
        if (controller.getListOfGeoAreaTypes().isEmpty()) {
            System.out.println("There are no geographical area types in the system.");
        }
        System.out.println("List of existing geographical areas types:\n");

        for (int i = 0; i < controller.getListOfGeoAreaTypes().size(); i++) {
            System.out.println(controller.getListOfGeoAreaTypes().get(i).getGeoAreaType() + "\n");
        }
    }
}
