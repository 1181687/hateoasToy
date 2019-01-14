package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetListOfTypeOfGeoAreaController;
import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.List;
import java.util.Scanner;

public class GetListOfTypeOfGeoArea {

    /** US004 As an Administrator, I want to get a list of existing geographical areas of a given type. */
    private GetListOfTypeOfGeoAreaController mCtrl;


    public GetListOfTypeOfGeoArea(GeoAreaList lista, GeoAreaTypeList listaTAG) {
        this.mCtrl = new GetListOfTypeOfGeoAreaController(lista, listaTAG);
    }

    public void run() {
        System.out.println("Choose the Geographical Area type you wish to see.");
        List<String> listaDeTiposDeAG = mCtrl.getListaDosTiposDeAG();
        Scanner ler = new Scanner(System.in);
        int opcaoEscolhida = -1;
        do {
            for (int i = 1; i <= listaDeTiposDeAG.size(); i++) {
                System.out.println(i + "-" + listaDeTiposDeAG.get(i - 1));
            }
            opcaoEscolhida = ler.nextInt();
        }
        while (opcaoEscolhida < 1 || opcaoEscolhida > listaDeTiposDeAG.size());

        String tipoEscolhido = listaDeTiposDeAG.get(opcaoEscolhida - 1);

        List<String> listaDeAGPorTipo = mCtrl.getListaAGPorTipo(tipoEscolhido);

        if (listaDeAGPorTipo.isEmpty()) {
            System.out.println("No Geographical Areas of the chosen type were found.");
        } else {
            if (listaDeAGPorTipo.size() == 1) {
                System.out.println("The Geographical Area of the type " + tipoEscolhido + " is:");
            }
            if (listaDeAGPorTipo.size() > 1) {
                System.out.println("The Geographical Areas of the type " + tipoEscolhido + " are:");
            }
            for (int i = 0; i < listaDeAGPorTipo.size(); i++) {
                System.out.println(listaDeAGPorTipo.get(i));
            }
            System.out.println();
        }
    }
}
