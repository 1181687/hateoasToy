package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

public class RoomOwner {
    House house;
    HouseService houseService;
    SensorsService sensorsService;
    RoomService roomService;


    public RoomOwner(HouseService houseService, RoomService roomService, SensorsService sensorsService) {
        this.houseService = houseService;
        this.roomService = roomService;
        this.sensorsService = sensorsService;
    }

    public void runRoomOwnerMenu() {
        int option = Menu.roomOwnerMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            if (option == 1) {
                GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(houseService);
                ui230.run();
            }
            if (option == 2) {
                try {
                    InstantsTempOutOfComfortLevel ui440and445 = new InstantsTempOutOfComfortLevel(houseService, sensorsService, roomService);
                    ui440and445.run();
                } catch (Exception e) {
                    System.out.println("Problems with house configuration.\n");
                }
            }
            option = Menu.roomOwnerMenu();
        }
    }
}
