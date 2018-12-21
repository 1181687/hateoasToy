package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US8Controller;
import pt.ipp.isep.dei.project.model.GeographicalArea;
import pt.ipp.isep.dei.project.model.ListaAG;

import java.util.Scanner;

public class US8UI {
    private US8Controller ctrl;

    public US8UI(ListaAG lista) {
        this.ctrl = new US8Controller(lista);
    }

    public void run() {
        System.out.println("Choose the geographical area to evaluate if it is inserted in another area (directly or indirectly).");
        System.out.println(ctrl.getConteudoLista(false));
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        GeographicalArea primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (!ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Choose the geographical area to check if it contains the previous one (directly or indirectly).");
            System.out.println(ctrl.getConteudoLista(false));
            int posicaoDaSegundaOpcao = ler.nextInt()- 1;
            if(posicaoDaPrimeiraOpcao != posicaoDaSegundaOpcao){
                if(ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(posicaoDaPrimeiraOpcao,posicaoDaSegundaOpcao)) {
                    System.out.println("Success!");
                }
                else{
                    System.out.println("Ohhh! The first choosen area is not included in the second area.");
                }
            }
            else{
                System.out.println("We know you are smarter than that.");
            }
        } else
            System.out.println("The geographical area you have chosen has no defined area. In option 7 of the previous menu you can define it.");
    }
}