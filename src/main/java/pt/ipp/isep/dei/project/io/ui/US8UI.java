package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US8Controller;
import pt.ipp.isep.dei.project.model.AreaGeografica;
import pt.ipp.isep.dei.project.model.ListaAG;

import java.util.Scanner;

public class US8UI {
    private US8Controller ctrl;

    public US8UI(ListaAG lista) {
        this.ctrl = new US8Controller(lista);
    }

    public void run() {
        System.out.println("Indique a área geografica a avaliar se está inserida noutra, direta ou indiretamente");
        System.out.println(ctrl.getConteudoLista(false));
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        AreaGeografica primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (!ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Indique a área geografica a verificar se contém a anterior, direta ou indiretamente");
            System.out.println(ctrl.getConteudoLista(false));
            int posicaoDaSegundaOpcao = ler.nextInt()- 1;
            if(posicaoDaPrimeiraOpcao != posicaoDaSegundaOpcao){
                if(ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(posicaoDaPrimeiraOpcao,posicaoDaSegundaOpcao)) {
                    System.out.println("Sucesso!");
                }
                else{
                    System.out.println("Ohhh! A primeira área escolhida não está inserida na segunda área.");
                }
            }
            else{
                System.out.println("CÁ BURROOO!");
            }
        } else
            System.out.println("A área geográfica que escolheu não tem área inserida definida. Na opção 7 do menu anterior poderá defini-la.");
    }
}