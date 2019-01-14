package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetTotalNominalPowerOfARoomController;
import pt.ipp.isep.dei.project.model.House;

public class GetTotalNominalPowerOfARoom {
    private GetTotalNominalPowerOfARoomController mController;

    public GetTotalNominalPowerOfARoom(House house) {
        this.mController = new GetTotalNominalPowerOfARoomController(house);
    }

    public void run() {
        System.out.println("Please select a Room to see its total nominal power");
        mController.getListOfRooms();
        int roomListLength = mController.getRoomListLength();
        String label1 = "Please select a room to see its total nominal power: \n" + mController.getListOfRooms();
        int position = InputValidator.getIntRange(label1, 1, roomListLength);
        if (mController.checkIfDeviceListIsEmpty(position)) {
            System.out.println("There are no devices in the room. Please, add one first");
        } else {
            double nominalPower = mController.getNominalPower();
            System.out.println("The total nominal power of the selected room is " + nominalPower);
        }
    }


}
