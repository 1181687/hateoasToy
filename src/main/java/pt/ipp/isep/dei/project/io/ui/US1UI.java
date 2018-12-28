package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US1Controller;
import pt.ipp.isep.dei.project.model.GeoAreaTypeList;

import java.util.Scanner;

public class US1UI {
    private US1Controller ctrl;

    public US1UI(GeoAreaTypeList lista) {
        this.ctrl = new US1Controller(lista);
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
