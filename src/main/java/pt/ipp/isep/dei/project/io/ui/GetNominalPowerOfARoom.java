package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfARoomController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfARoom {
    private GetNominalPowerOfARoomController mController;

    public GetNominalPowerOfARoom(House house) {
        this.mController = new GetNominalPowerOfARoomController(house);
    }

    public void run() {
        mController.getListOfRooms();
        int roomListLength = mController.getRoomListLength();
        String label1 = "Please select a room to see its total nominal power: \n" + mController.getListOfRooms();
        int position = InputValidator.getIntRange(label1, 1, roomListLength);
        mController.getRoom(position - 1);
        if (mController.checkIfDeviceListIsEmpty(position-1)) {
            System.out.println("There are no devices in the room. Please, add one first");
        } else {
            double nominalPower = mController.getNominalPower();
            System.out.println("The total nominal power of the selected room is " + nominalPower + " kW");
        }
    }


}
