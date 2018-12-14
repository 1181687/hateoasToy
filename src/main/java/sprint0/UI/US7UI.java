package sprint0.UI;

import sprint0.Controllers.US7Controller;
import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;

import java.util.Scanner;

public class US7UI {
    private US7Controller ctrl;

    public US7UI(ListaAG lista) {
        this.ctrl = new US7Controller(lista);
    }

    public void run() {
        System.out.println("Indique o número correspondente à área geográfica que quer inserir noutra área geográfica");
        for (int i = 1; i <= ctrl.obterTamanhoLista(); i++) {
            if (ctrl.verSeAGTemAreaInseridaVazia(ctrl.getAGNaListaApresentada(i - 1))) {
                System.out.println(i + " - Nome: " + ctrl.nomeAGNaLista(i - 1)
                        + ", Tipo: " + ctrl.tipoAGNaLista(i - 1)
                        + ", Latitude: " + ctrl.latitudeAGNaLista(i - 1)
                        + ", Longitude: " + ctrl.longitudeAGNaLista(i - 1));
            } else {
                System.out.println(i + " - Nome: " + ctrl.nomeAGNaLista(i - 1)
                        + ", Tipo: " + ctrl.tipoAGNaLista(i - 1)
                        + ", Latitude: " + ctrl.latitudeAGNaLista(i - 1)
                        + ", Longitude: " + ctrl.longitudeAGNaLista(i - 1)
                        + ", Inserido Em: " + ctrl.tipoAreaInseridaEmAGNaLista(i - 1)
                        + " " + ctrl.nomeAreaInseridaEmAGNaLista(i - 1));
            }
        }
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        AreaGeografica primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Introduza o nome da área geográfica na qual está inserida a área geográfica anterior");
            ctrl.removerAGLista(primeiraAG);
            for (int i = 1; i <= ctrl.obterTamanhoLista(); i++) {
                System.out.println(i + " - Nome: " + ctrl.nomeAGNaLista(i - 1)
                        + ", Tipo: " + ctrl.tipoAGNaLista(i - 1)
                        + ", Latitude: " + ctrl.latitudeAGNaLista(i - 1)
                        + ", Longitude: " + ctrl.longitudeAGNaLista(i - 1));
            }
            int posicaoDaSegundaOpcao = ler.nextInt() - 1;
            AreaGeografica segundaAG = ctrl.getAGNaListaApresentada(posicaoDaSegundaOpcao);
            primeiraAG.setmAreaInseridaEm(segundaAG);
            System.out.println("Sucesso!");
            ctrl.adicionarAGLista(posicaoDaPrimeiraOpcao, primeiraAG);
        } else
            System.out.println("A área geográfica que escolheu já está inserida noutra área. Tente outra área geográfica");
    }
}