package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.GeoAreaList;
import pt.ipp.isep.dei.project.model.GeographicalArea;

import java.util.Scanner;

/** US007 As an Administrator, I want to add an existing geographical area to another one
(e.g. add city of Porto to the district of Porto). */

public class AddGeoAreaToAnotherGeoArea {
    private AddGeoAreaToAnotherGeoAreaController ctrl;

    public AddGeoAreaToAnotherGeoArea(GeoAreaList lista) {
        this.ctrl = new AddGeoAreaToAnotherGeoAreaController(lista);
    }

    public void run() {
        System.out.println("Choose the number that corresponds to the geographical area you wish to include in another geographical area.");
        System.out.println(ctrl.getConteudoLista(true));
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        GeographicalArea primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Choose the number of the geographical area in which the previous geographical area is included.");
            ctrl.removerAGLista(primeiraAG);
            System.out.println(ctrl.getConteudoLista(true));
            int posicaoDaSegundaOpcao = ler.nextInt() - 1;
            GeographicalArea segundaAG = ctrl.getAGNaListaApresentada(posicaoDaSegundaOpcao);
            primeiraAG.setInsertedIn(segundaAG);
            System.out.println("Success!");
            ctrl.adicionarAGListaPosicaoEspecifica(posicaoDaPrimeiraOpcao, primeiraAG);
        } else
            System.out.println("The geographical area you have chosen is already included in another area. Try another geographical area.");
    }
}