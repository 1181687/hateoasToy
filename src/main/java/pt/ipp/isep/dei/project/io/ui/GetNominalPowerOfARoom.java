package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfARoomController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfARoom {
    private GetNominalPowerOfARoomController mController;

    public GetNominalPowerOfARoom(House house) {
        this.mController = new GetNominalPowerOfARoomController(house);
    }

    public void run() {
        String exit = "0- Exit";
        mController.getListOfRooms();
        int roomListLength = mController.getRoomListSize();
        String label1 = "Please select a room to see its total nominal power: \n" + mController.getListOfRooms() + exit;
        int position = InputValidator.getIntRange(label1, 0, roomListLength);
        if (position == 0) {
            return;
        }
        mController.getRoom(position - 1);
        if (mController.isDeviceListEmpty(position-1)) {
            System.out.println("There are no devices in the room. Please, add one first");
        } else {
            double nominalPower = mController.getNominalPower();
            System.out.println("The total nominal power of the selected room is " + nominalPower + " kW");
        }
    }


}
