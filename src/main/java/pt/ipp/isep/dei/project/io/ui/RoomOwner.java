package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.services.HouseService;

public class RoomOwner {
    House house;
    HouseService houseService;

    public RoomOwner(HouseService houseService) {
        this.houseService = houseService;
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
            option = Menu.roomOwnerMenu();
        }
    }
}
