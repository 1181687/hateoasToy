package sprint0.UI;

import sprint0.Model.ListaTiposAG;

import java.util.Scanner;

public class Main {

    public static void main(String [] args){
        ListaTiposAG lista = new ListaTiposAG();
        int opcao = -1;
        Scanner ler = new Scanner(System.in);
        while (opcao!=0){
            System.out.println("1-US1-Novo tipo de Area Geografica");
            System.out.println("2-US2-Apresentar lista de tipos de Area Geografica");
            System.out.println("0-Sair");
            opcao = ler.nextInt();
            switch (opcao){
                case 1:
                    US1UI ui = new US1UI(lista);
                    ui.run();
                    break;
            }
        }
    }
}
