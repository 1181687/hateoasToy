package sprint0.UI;

import sprint0.Controllers.US4Controller;
import sprint0.Model.ListaAG;

import java.util.Scanner;

public class US4UI {

    private ListaAG mListaAG;
    private US4Controller mCtrl;

    public US4UI(ListaAG lista) {
        this.mListaAG = lista;
        this.mCtrl = new US4Controller(lista);
    }

    public void run() {

        System.out.println("Introduza o nome do tipo de àreas geograficas que pretende vizualizar");
        Scanner ler = new Scanner(System.in);
        String tipo = ler.nextLine();
        if (mCtrl.getListaAGPorTipo(tipo).isEmpty()) {
            System.out.println("Não foram encontradas àreas geográficas desse tipo");
        } else {
            System.out.println(mCtrl.getListaAGPorTipo(tipo));
        }
    }

}
