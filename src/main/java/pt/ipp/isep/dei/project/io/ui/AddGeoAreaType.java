package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddGeoAreaTypeController;
import pt.ipp.isep.dei.project.model.GeographicalArea.GeographicalAreaTypeList;

import java.util.Scanner;

/** US001 As an Administrator, I want to add a new type of geographical area, in order to be
able to create a classification of geographical areas. */

public class AddGeoAreaType {
    private AddGeoAreaTypeController controller;

    public AddGeoAreaType(GeographicalAreaTypeList lista) {
        this.controller = new AddGeoAreaTypeController(lista);
    }

    public void run() {
        System.out.println("Introduce the name of the new type of Geographical Area.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if (controller.addTypeOfGeoAreaToTheList(nome)) {
            System.out.println("Success!");
        } else {
            System.out.println("Try another name!");
        }
    }
}
