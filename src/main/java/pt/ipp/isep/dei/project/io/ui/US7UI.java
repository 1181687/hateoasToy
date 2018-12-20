package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US7Controller;
import pt.ipp.isep.dei.project.model.AreaGeografica;
import pt.ipp.isep.dei.project.model.ListaAG;

import java.util.Scanner;

public class US7UI {
    private US7Controller ctrl;

    public US7UI(ListaAG lista) {
        this.ctrl = new US7Controller(lista);
    }

    public void run() {
        System.out.println("Indique o número correspondente à área geográfica que quer inserir noutra área geográfica");
        System.out.println(ctrl.getConteudoLista(true));
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        AreaGeografica primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Introduza o nome da área geográfica na qual está inserida a área geográfica anterior");
            ctrl.removerAGLista(primeiraAG);
            System.out.println(ctrl.getConteudoLista(true));
            int posicaoDaSegundaOpcao = ler.nextInt() - 1;
            AreaGeografica segundaAG = ctrl.getAGNaListaApresentada(posicaoDaSegundaOpcao);
            primeiraAG.setmAreaInseridaEm(segundaAG);
            System.out.println("Sucesso!");
            ctrl.adicionarAGListaPosicaoEspecifica(posicaoDaPrimeiraOpcao, primeiraAG);
        } else
            System.out.println("A área geográfica que escolheu já está inserida noutra área. Tente outra área geográfica");
    }
}