package sprint0.UI;


import sprint0.Controllers.US2Controller;
import sprint0.Model.ListaTiposAG;

import java.util.Scanner;

public class US2UI {
    private ListaTiposAG lista;
    private US2Controller ctrl2;

    public US2UI(ListaTiposAG lista) {
        this.lista = lista;
        this.ctrl2 = new US2Controller(lista);
    }


    public void run() {
        System.out.println("Lista de Areas Geográficas existentes:");

        for (int i = 0; i <ctrl2.getListaDosTiposDeAG().size() ; i++) {
            System.out.println(ctrl2.getListaDosTiposDeAG().get(i));
        }
        System.out.println("");
    }
}
