package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerRoomsDevices {

    private GetNominalPowerRoomsDevicesController mController;

    public GetNominalPowerRoomsDevices(House house) {
        this.mController = new GetNominalPowerRoomsDevicesController(house);
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
        if (mController.checkIfRoomListIsEmpty(positionHG - 1)) {
            System.out.println("There are no rooms in this house grid. Please, choose another grid or add rooms to the chosen grid.\n");
            return;
        }
        boolean flag = true;

        do {
            String exit = "\n0 - Exit";
            String label2 = "Please select a room to check its nominal power or the nominal power of a subset " +
                    "of devices located in that room: \n" + mController.getRoomsInTheHouseGrid(positionHG - 1) + exit;
            int positionRoom = InputValidator.getIntRange(label2, 0, mController.getSizeOfRoomListInGrid(positionHG - 1)) - 1;
            if (positionRoom == -1) {
                return;
            }
            if (mController.checkIfDeviceListIsEmpty(positionRoom)) {
                System.out.println("There are no devices in this room. Please, choose another room or add devices to the chosen room.\n");
                continue;
            }
            String label3 = "Please select a device to check its nominal power: \n"
                    + mController.getContentOfDeviceListInRoomOfGrid(positionRoom) +
                    (mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) + " - Total nominal power of room\n" + exit;
            int positionDevice = InputValidator.getIntRange(label3, 0, mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1);
            if (positionDevice == 0) {
                return;
            }
            if (positionDevice == mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) {
                mController.addAMeasurableObject(mController.getChosenRoomInTheGrid(positionRoom));
                continue;
            } else {
                if (mController.checkIfObjInList(mController.getDeviceFromPositionInList(positionRoom, positionDevice - 1))) {
                    System.out.println("That device was already chosen. Please choose another one (type the number of a device that wasn't chosen yet.)");
                }
                mController.addAMeasurableObject(mController.getDeviceFromPositionInList(positionRoom, (positionDevice - 1)));
                String label4 = "Would you like to add any other device of this room to the nominal power calculations? If yes," +
                        "please type the number of that device; if not, please type 0.";
                boolean flag2 = true;
                do {
                    int addOtherDeviceOrNot = InputValidator.getIntRange(label4, 0, mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1);
                    if (addOtherDeviceOrNot == 0) {
                        break;
                    }
                    if (addOtherDeviceOrNot == mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) {
                        System.out.println("You have already selected a device from this room. It is not possible to select all of the devices of the room. Please select another device.");
                        continue;
                    } else {
                        if (mController.checkIfObjInList(mController.getDeviceFromPositionInList(positionRoom, addOtherDeviceOrNot - 1))) {
                            label4 = "That device was already chosen. Please choose another one (type the number of a device that wasn't chosen yet.)";
                        } else {
                            mController.addAMeasurableObject(mController.getDeviceFromPositionInList(positionRoom, addOtherDeviceOrNot - 1));
                            label4 = "Would you like to add any other device of this room to the nominal power calculations? If yes," +
                                    "please type the number of that device; if not, please type 0.";
                        }
                    }
                } while (flag2);
                String label5 = "Do you want to add other rooms or devices of the other rooms? (Y/N)";
                String confirmation = InputValidator.confirmValidation(label5);
                if ("N".equals(confirmation) || "n".equals(confirmation)) {
                    flag = false;
                }
            }
        } while (flag);
        System.out.println("The total nominal power for the selected subset of rooms and/or devices is " + mController.getNominalPowerOfSelectedMeasurableObjects());
    }
}