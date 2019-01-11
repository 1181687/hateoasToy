package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.Scanner;

/** US001 As an Administrator, I want to add a new type of geographical area, in order to be
able to create a classification of geographical areas. */

public class AddGeoAreaType {
    private AddGeoAreaTypeController ctrl;

    public AddGeoAreaType(GeoAreaTypeList lista) {
        this.ctrl = new AddGeoAreaTypeController(lista);
    }

    public void run() {
        System.out.println("Introduce the name of the new type of Geographical Area.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if (ctrl.adicionaNovoTipoAreaGeografica(nome)) {
            System.out.println("Success!");
        } else {
            System.out.println("Try another name!");
        }
    }
}
