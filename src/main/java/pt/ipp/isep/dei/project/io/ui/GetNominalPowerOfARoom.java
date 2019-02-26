package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfARoom {
    private GetNominalPowerController controller;

    public GetNominalPowerOfARoom(House house) {
        this.controller = new GetNominalPowerController(house);
    }

    public void run() {
        String exit = "0- Exit";
        controller.getListOfRooms();
        int roomListLength = controller.getRoomListSize();
        String label1 = "Please select a room to see its total nominal power: \n" + controller.getListOfRooms() + exit;
        int position = InputValidator.getIntRange(label1, 0, roomListLength);
        if (position == 0) {
            return;
        }
        controller.getRoom(position - 1);
        if (controller.isDeviceListEmpty(position - 1)) {
            System.out.println("There are no devices in the room. Please, add one first");
        } else {
            double nominalPower = controller.getNominalPower();
            System.out.println("The total nominal power of the selected room is " + nominalPower + " kW");
        }
    }


}
