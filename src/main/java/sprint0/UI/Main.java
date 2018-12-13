package sprint0.UI;

import sprint0.Model.ListaAG;
import sprint0.Model.ListaTiposAG;
import sprint0.Model.ListaTiposSensores;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaTiposAG listaTiposAG = new ListaTiposAG();
        ListaAG listaAG = new ListaAG();
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        int opcao = -1;
        Scanner ler = new Scanner(System.in);
        while (opcao != 0) {
            System.out.println("1-US1-Novo tipo de Area Geografica");
            System.out.println("2-US2-Apresentar lista de tipos de Area Geografica");
            System.out.println("3-US3-Nova Area Geografica");
            System.out.println("5-US5-Nova característica meteorológica dos sensores");
            System.out.println("7-US7-Definir área geográfica inserida noutra área");
            System.out.println("0-Sair");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1:
                    US1UI ui = new US1UI(listaTiposAG);
                    ui.run();
                    break;
                case 2:
                    US2UI ui2 = new US2UI(listaTiposAG);
                    ui2.run();
                    break;
                case 3:
                    US3UI ui3 = new US3UI(listaAG);
                    ui3.run();
                    break;
                case 5:
                    US5UI ui5 = new US5UI(listaTiposSensores);
                    ui5.run();
                    break;
                case 7:
                    US7UI ui7 = new US7UI(listaAG);
                    ui7.run();
                    break;
            }
        }
    }
}