package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.US2Controller;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;


public class GetListGeoAreaTypes {
       private US2Controller ctrl2;

    public GetListGeoAreaTypes(GeoAreaTypeList lista) {
              this.ctrl2 = new US2Controller(lista);
    }


    public void run() {
        System.out.println("List of existing geographical areas:");

        for (int i = 0; i <ctrl2.getListaTiposDeAG().size() ; i++) {
            System.out.println(ctrl2.getListaTiposDeAG().get(i));
        }
        System.out.println();
    }
}
