package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaTiposAG listaTiposAG = new ListaTiposAG();
        ListaAG listaAG = new ListaAG();
        RoomList roomList = new RoomList();
        HouseGridList gridList = new HouseGridList();
        Location location = new Location(0.0, 0.0, 0.0);
        Address address = new Address("0000", location);
        ListaTiposSensores listaTiposSensores = new ListaTiposSensores();
        House house = new House(roomList, gridList, address);
        PowerSourceType powerSourceType1 = new PowerSourceType("Battery");
        PowerSourceType powerSourceType2 = new PowerSourceType("Public electric grid");
        PowerSourceTypeList powerSourceTypeList = new PowerSourceTypeList();
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType1);
        powerSourceTypeList.addPowerSourceTypeToPowerSourceTypeList(powerSourceType2);
        int option = -1;
        int userOption = -1;
        int regularUserOption = -1;
        Scanner ler = new Scanner(System.in);
        while (userOption != 0) {
            do {
                System.out.println("Please choose your user role:");
                System.out.println();
                System.out.println("1-Administrator");
                System.out.println("2-Regular User");
                System.out.println("0-Exit");
                userOption = ler.nextInt();
            } while ((userOption < 1 && userOption != 0) || userOption > 2);
            if (userOption == 1) {
                ler.nextLine();
                while (option != 0) {
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
                        System.out.println("9-US101-Configure the location of the house");
                        System.out.println("10-US105-Add a room to the house");
                        System.out.println("11-US108-Edit a room from a list of rooms");
                        System.out.println("12-US130-Create a House Grid");
                        System.out.println("13-US135-Add a power source to a house grid");
                        System.out.println("15-US147-Attach a room to a house grid");
                        System.out.println("16-US149-Detach a room from a house grid");
                        System.out.println("0-Sair");
                        option = ler.nextInt();
                    }
                    while ((option < 1 && option != 0) || option > 17);
                    switch (option) {
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
                        case 9:
                            US101UI ui9 = new US101UI(house);
                            ui9.run();
                            break;
                        case 10:
                            US105UI ui105 = new US105UI(house);
                            ui105.run();
                            break;
                        case 12:
                            US130UI ui130 = new US130UI(gridList);
                            ui130.run();
                            break;
                        case 13:
                            US135UI ui135 = new US135UI(gridList, powerSourceTypeList);
                            ui135.run();
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
            if (userOption == 2) {
                while (regularUserOption != 0) {
                    do {
                        System.out.println("Choose one of the options:");
                        System.out.println();
                        System.out.println("1-US600");
                        System.out.println("2-US605");
                        System.out.println("3-US610");
                        System.out.println("4-US620");
                        System.out.println("5-US623");
                        System.out.println("0-Exit");
                        regularUserOption = ler.nextInt();
                    } while ((regularUserOption < 1 && regularUserOption != 0) || regularUserOption > 6);
                    switch (regularUserOption) {
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
                        case 9:
                            US101UI ui9 = new US101UI(house);
                            ui9.run();
                            break;
                    }
                }
            }
        }
    }
}
