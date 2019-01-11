package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaToAnotherGeoAreaController;
import pt.ipp.isep.dei.project.model.GeoAreaList;

import java.util.Scanner;

/**
 * US007 As an Administrator, I want to add an existing geographical area to another one
 * (e.g. add city of Porto to the district of Porto).
 */

public class AddGeoAreaToAnotherGeoArea {
    private AddGeoAreaToAnotherGeoAreaController ctrl;

    public AddGeoAreaToAnotherGeoArea(GeoAreaList lista) {
        this.ctrl = new AddGeoAreaToAnotherGeoAreaController(lista);
    }

    public void run() {
        String label1 = "Choose the number that corresponds to the geographical area you wish to include in another geographical area.";
        System.out.println(ctrl.getConteudoLista(true));
        int firstOption = InputValidator.getIntRange(label1, 1, ctrl.getSizeList());
        int positionOfFirstOption = firstOption - 1;
        if (ctrl.verSeAGTemAreaInseridaVazia(ctrl.getAGNaListaApresentada(positionOfFirstOption))) {
            System.out.println("Choose the number of the geographical area in which the previous geographical area is included.");
            ctrl.removerAGLista(ctrl.getAGNaListaApresentada(positionOfFirstOption));
            System.out.println(ctrl.getConteudoLista(true));
            int secondOption = InputValidator.getIntRange(label1, 1, ctrl.getSizeList());
            int positionOfSecondOption = secondOption - 1;
            ctrl.getAGNaListaApresentada(positionOfSecondOption).setInsertedIn(ctrl.getAGNaListaApresentada(positionOfSecondOption));
            System.out.println("Success!");
            ctrl.adicionarAGListaPosicaoEspecifica(positionOfSecondOption, ctrl.getAGNaListaApresentada(positionOfSecondOption));
        } else
            System.out.println("The geographical area you have chosen is already included in another area. Try another geographical area.");
    }
}