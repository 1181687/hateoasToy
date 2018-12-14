package sprint0.UI;

import sprint0.Controllers.US8Controller;
import sprint0.Model.AreaGeografica;
import sprint0.Model.ListaAG;

import java.util.Scanner;

public class US8UI {
    private US8Controller ctrl;

    public US8UI(ListaAG lista) {
        this.ctrl = new US8Controller(lista);
    }

    public void run() {
        System.out.println("Indique a área geografica a avaliar se está inserida noutra, direta ou indiretamente");
        for (int i = 1; i <= ctrl.obterTamanhoLista(); i++) {
                System.out.println(i + " - Nome: " + ctrl.nomeAGNaLista(i - 1)
                        + ", Tipo: " + ctrl.tipoAGNaLista(i - 1)
                        + ", Latitude: " + ctrl.latitudeAGNaLista(i - 1)
                        + ", Longitude: " + ctrl.longitudeAGNaLista(i - 1));
        }
        Scanner ler = new Scanner(System.in);
        int posicaoDaPrimeiraOpcao = ler.nextInt() - 1;
        AreaGeografica primeiraAG = ctrl.getAGNaListaApresentada(posicaoDaPrimeiraOpcao);
        if (!ctrl.verSeAGTemAreaInseridaVazia(primeiraAG)) {
            System.out.println("Indique a área geografica a verificar se contém a anterior, direta ou indiretamente");
            for (int i = 1; i <= ctrl.obterTamanhoLista(); i++) {
                System.out.println(i + " - Nome: " + ctrl.nomeAGNaLista(i - 1)
                        + ", Tipo: " + ctrl.tipoAGNaLista(i - 1)
                        + ", Latitude: " + ctrl.latitudeAGNaLista(i - 1)
                        + ", Longitude: " + ctrl.longitudeAGNaLista(i - 1));
            }
            int posicaoDaSegundaOpcao = ler.nextInt()- 1;
            if(posicaoDaPrimeiraOpcao != posicaoDaSegundaOpcao){
                if(ctrl.verificarSeAGEstaContidaDiretaOuIndiretamenteNoutraAG(posicaoDaPrimeiraOpcao,posicaoDaSegundaOpcao)) {
                    System.out.println("Sucesso! O/A "
                            + ctrl.tipoAGNaLista(posicaoDaPrimeiraOpcao) + " " + ctrl.nomeAGNaLista(posicaoDaPrimeiraOpcao)
                            + " está inserido/a em "
                            + ctrl.tipoAGNaLista(posicaoDaSegundaOpcao) + " " + ctrl.nomeAGNaLista(posicaoDaSegundaOpcao) + "!");
                }
                else{
                    System.out.println("Ohhh! A primeira área escolhida não está inserida na segunda área.");
                }
            }
            else{
                System.out.println("CÁ BURROOO! É obvio que "
                        + ctrl.tipoAGNaLista(posicaoDaPrimeiraOpcao) + " " + ctrl.nomeAGNaLista(posicaoDaPrimeiraOpcao)
                        + " está inserido/a em "
                        + ctrl.tipoAGNaLista(posicaoDaSegundaOpcao) + " " + ctrl.nomeAGNaLista(posicaoDaSegundaOpcao) + "!");
            }
        } else
            System.out.println("A área geográfica que escolheu não tem área inserida definida. Na opção 7 do menu anterior poderá defini-la.");
    }
}