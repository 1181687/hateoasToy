package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.GetListGeoAreaTypesController;
import pt.ipp.isep.dei.project.model.GeographicalAreaTypeList;
/** US002 As an Administrator, I want to get a list of existing geographical area types. */

public class GetListGeoAreaTypes {
    private GetListGeoAreaTypesController ctrl2;

    public GetListGeoAreaTypes(GeographicalAreaTypeList lista) {
        this.ctrl2 = new GetListGeoAreaTypesController(lista);
    }


    public void run() {
        System.out.println("List of existing geographical areas:");

        for (int i = 0; i <ctrl2.getListaTiposDeAG().size() ; i++) {
            System.out.println(ctrl2.getListaTiposDeAG().get(i));
        }
        System.out.println();
    }
}
