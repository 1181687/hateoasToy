package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaTiposAG listaTiposAG = new ListaTiposAG();
        ListaAG listaAG = new ListaAG();
        RoomList roomList = new RoomList();
        HouseGridList gridList = new HouseGridList();
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        House house = new House(roomList);
        int opcao = -1;
        Scanner ler = new Scanner(System.in);
        while (opcao != 0) {
            do {
                System.out.println("Escolha uma das seguintes opções:");
                System.out.println();
                System.out.println("1-US1-Novo tipo de Area Geografica");
                System.out.println("2-US2-Apresentar lista de tipos de Area Geografica");
                System.out.println("3-US3-Nova Area Geografica");
                System.out.println("4-US4-Apresentar lista de àreas geográficas por tipo");
                System.out.println("5-US5-Nova característica meteorológica dos sensores");
                System.out.println("6-US6-Novo sensor");
                System.out.println("7-US7-Definir área geográfica inserida noutra área");
                System.out.println("8-US8-Verificar se área geográfica está inserida noutra área");
                System.out.println("10-US105-Add a room to the house");
                System.out.println("11-US108-Edit a room from a list of rooms");
                System.out.println("15-US147-Attach a room to a house grid");
                System.out.println("16-US149-Detach a room from a house grid");
                System.out.println("0-Sair");
                opcao = ler.nextInt();
            }
            while ((opcao < 1 && opcao != 0) || opcao > 17);
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
                    US3UI ui3 = new US3UI(listaAG, listaTiposAG);
                    ui3.run();
                    break;
                case 4:
                    US4UI ui4 = new US4UI(listaAG, listaTiposAG);
                    ui4.run();
                    break;
                case 5:
                    US5UI ui5 = new US5UI(listaTiposSensores);
                    ui5.run();
                    break;
                case 6:
                    US6UI ui6 = new US6UI(listaAG, listaTiposSensores);
                    ui6.run();
                    break;
                case 7:
                    US7UI ui7 = new US7UI(listaAG);
                    ui7.run();
                    break;
                case 8:
                    US8UI ui8 = new US8UI(listaAG);
                    ui8.run();
                    break;
                case 10:
                    US105UI ui105 = new US105UI(house);
                    ui105.run();
                    break;
                case 11:
                    US108UI ui108 = new US108UI(roomList);
                    ui108.run();
                    break;
                case 15:
                    US147UI ui147 = new US147UI(gridList, roomList);
                    ui147.run();
                    break;
                case 16:
                    US149UI ui149 = new US149UI(gridList, roomList);
                    ui149.run();
                    break;
            }
        }
    }
}
