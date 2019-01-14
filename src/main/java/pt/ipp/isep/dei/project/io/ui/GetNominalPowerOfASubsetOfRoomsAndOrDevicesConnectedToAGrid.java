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
            System.out.println("There are no house grids in the house. Please, add one");
            return;
        }
        String label1 = "Please select a House Grid to check the total nominal power of a subset of rooms " +
                "and/or devices of your choosing connected to that grid: \n" + mController.listHouseGrids();
        int gridListLength = mController.getHouseGridListLength();
        int positionHG = InputValidator.getIntRange(label1, 1, gridListLength);
        //mController.getHouseGridbyPosition(positionHG);
        if (mController.checkIfRoomListIfEmpty()) {
            System.out.println("There are no rooms in this house grid. Please, add one");
            return;
        }
        String label2 = "Please select a room to check its nominal power or the nominal power of a subset " +
                "of devices located in that room: \n" + mController.getRoomListToStringInAGrid();
        int roomListInTheGridLength = mController.getSizeOfRoomListConnectedToGrid();
        int positionRoom = InputValidator.getIntRange(label2, 1, roomListInTheGridLength);
        //mController.getChosenRoomInTheGrid(positionRoom);
        if (mController.checkIfDeviceListIsEmpty(positionRoom)) {
            System.out.println("There are no devices in this room. Please, add one");
            return;
        }
        String label3 = "Please select a device to check its nominal power: \n"
                + mController.getContentOfDeviceListInRoomOfGrid(positionRoom) + "\n" +
                (mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) + " - Total of room\n";
        int deviceListInTheRoomOfTheGridLength = mController.getSizeOfListOfDevicesInARoom(positionRoom);
        int positionDevice = InputValidator.getIntRange(label3, 1, deviceListInTheRoomOfTheGridLength + 1);

        /*if (positionDevice == deviceListInTheRoomOfTheGridLength + 1) {
            mController.addRoomOrDeviceToMeasurableList(mController.getChosenRoomInTheGrid(positionRoom));
        } else {
            mController.addRoomOrDeviceToMeasurableList(mController.getDeviceFromPositionInList(positionRoom, positionDevice));
        }*/
    }
}