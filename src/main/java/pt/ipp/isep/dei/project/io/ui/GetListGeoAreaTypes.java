package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaTypeList;

/**
 * US002 As an Administrator, I want to get a list of existing geographical area types.
 */

public class GetListGeoAreaTypes {
    private GetListGeoAreaTypesController controller;

    public GetListGeoAreaTypes(GeographicalAreaTypeList lista) {
        this.controller = new GetListGeoAreaTypesController(lista);
    }


    public void run() {
        System.out.println("List of existing geographical areas:");

        for (int i = 0; i < controller.getListaTiposDeAG().size(); i++) {
            System.out.println(controller.getListaTiposDeAG().get(i));
        }
        System.out.println();
    }
}
