package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.model.house.House;

public class RoomOwner {
    House house;

    public RoomOwner(House house) {
        this.house = house;
    }

    public void runRoomOwnerMenu() {
        int option = Menu.roomOwnerMenu();
        if (option == 0) {
            return;
        }
        while (option != 0) {
            switch (option) {
                case 1:
                    GetNominalPowerOfARoom ui230 = new GetNominalPowerOfARoom(house);
                    ui230.run();
                    break;
            }
            option = Menu.roomOwnerMenu();
        }
    }
}
