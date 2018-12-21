package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US5Controller;
import pt.ipp.isep.dei.project.model.ListaTiposSensores;

import java.util.Scanner;

public class US5UI {

    private US5Controller mController;

    public US5UI(ListaTiposSensores mListaTiposSensores) {

        this.mController = new US5Controller(mListaTiposSensores);
    }

    public void run(){
        System.out.println("Introduce a new weather feature.");
        Scanner ler = new Scanner(System.in);
        String nome = ler.nextLine();
        if(this.mController.criarEAdicionarTipoDeSensor(nome)){
            System.out.println("Success!");
        }
        else{
            System.out.println("Try another weather feature!");
        }
    }
}
