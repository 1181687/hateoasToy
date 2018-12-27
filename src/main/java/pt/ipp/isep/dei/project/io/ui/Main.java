package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.*;
import pt.ipp.isep.dei.project.utils.Menu;

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

        while (userOption != 0) {
            userOption = Menu.usersMenu();

            if (userOption == 1) {
                option = Menu.administratorMenu();
                while (option != 0) {

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
                        case 17:
                            US253UI ui253 = new US253UI(house, roomList, listaTiposSensores);
                            ui253.run();
                            break;
                    }
                    option = Menu.administratorMenu();
                }
            }
            if (userOption == 2) {
                option = Menu.regularUserMenu();

                while (option != 0) {

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
                            US605UI ui10 = new US605UI(house, listaTiposSensores);
                            ui10.run();
                            break;
                    }
                    option = Menu.administratorMenu();
                }
            }
        }
    }
}
