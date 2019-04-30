package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;
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
        System.out.println("List of existing geographical areas:");

        for (int i = 0; i < controller.getListaDosTiposDeAG().size(); i++) {
            System.out.println(controller.getListaDosTiposDeAG().get(i));
        }
        System.out.println();
    }
}
