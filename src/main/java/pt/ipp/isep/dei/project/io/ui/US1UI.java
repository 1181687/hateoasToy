package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US1Controller;
import pt.ipp.isep.dei.project.model.ListaTiposAG;

import java.util.Scanner;

public class US1UI {
    private US1Controller ctrl;

    public US1UI(ListaTiposAG lista) {
        this.ctrl = new US1Controller(lista);
    }

    public void run() {
        System.out.println("Introduza o nome do novo tipo de Area Geografica");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if (ctrl.novoTAG(nome)) {
            System.out.println("Sucesso!");
        } else {
            System.out.println("Tente outro nome!");
        }
    }
}
