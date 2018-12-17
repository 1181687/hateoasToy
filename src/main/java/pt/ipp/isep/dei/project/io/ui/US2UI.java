package pt.ipp.isep.dei.project.io.ui;


import pt.ipp.isep.dei.project.controllers.US2Controller;
import pt.ipp.isep.dei.project.model.ListaTiposAG;


public class US2UI {
       private US2Controller ctrl2;

    public US2UI(ListaTiposAG lista) {
              this.ctrl2 = new US2Controller(lista);
    }


    public void run() {
        System.out.println("Lista de Areas Geográficas existentes:");

        for (int i = 0; i <ctrl2.getListaTiposDeAG().size() ; i++) {
            System.out.println(ctrl2.getListaTiposDeAG().get(i));
        }
        System.out.println("");
    }
}
