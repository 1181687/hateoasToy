package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerOfASubsetOfRoomsAndOrDevicesConnectedToAGridController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerOfASubsetOfRoomsAndOrDevicesConnectedToAGrid {

    private GetNominalPowerOfASubsetOfRoomsAndOrDevicesConnectedToAGridController mController;

    public GetNominalPowerOfASubsetOfRoomsAndOrDevicesConnectedToAGrid(House house) {
        this.mController = new GetNominalPowerOfASubsetOfRoomsAndOrDevicesConnectedToAGridController(house);
    }

    public void run() {
        if (mController.checkIfGridListIsEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one.\n");
            return;
        }
        String label1 = "Please select a House Grid to check the total nominal power of a subset of rooms " +
                "and/or devices of your choosing connected to that grid: \n" + mController.listHouseGrids();
        int positionHG = InputValidator.getIntRange(label1, 1, mController.getHouseGridListLength());
        mController.getHouseGridbyPosition(positionHG - 1);
        if (mController.checkIfRoomListIfEmpty(positionHG-1)) {
            System.out.println("There are no rooms in this house grid. Please, add one.\n");
            return;
        }
        String label2 = "Please select a room to check its nominal power or the nominal power of a subset " +
                "of devices located in that room: \n" + mController.getRoomsInTheHouseGrid(positionHG-1);
        int positionRoom = InputValidator.getIntRange(label2, 1, mController.getSizeOfRoomListInGrid(positionHG-1));
        if (mController.checkIfDeviceListIsEmpty(positionRoom-1)) {
            System.out.println("There are no devices in this room. Please, add one.\n");
            return;
        }
        String label3 = "Please select a device to check its nominal power: \n"
                + mController.getContentOfDeviceListInRoomOfGrid(positionRoom-1) +
                mController.getSizeOfListOfDevicesInARoom(positionRoom-1)+1 + " - Total nominal power of room\n";
        int positionDevice = InputValidator.getIntRange(label3, 1, mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1);


            if (positionDevice == mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) {
                mController.addAMeasurableObject(mController.getChosenRoomInTheGrid(positionRoom));
                return;
            } else {
                mController.addAMeasurableObject(mController.getDeviceFromPositionInList(positionRoom, positionDevice));
                String label4 = "Would you like to add any other device of this room to the nominal power calculations? Please type 0 if you don't want to add; please type 1 if you want to add one more device";
                int addOtherDeviceOrNot = InputValidator.getIntRange(label4, 0, 1);
                if (addOtherDeviceOrNot == 0) {
                }
            }
    }
}