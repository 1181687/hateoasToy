package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerRoomsDevices {

    private GetNominalPowerRoomsDevicesController mController;

    public GetNominalPowerRoomsDevices(House house) {
        this.mController = new GetNominalPowerRoomsDevicesController(house);
    }

    private boolean chooseRoomsAndOrDevicesInGrid() {
        boolean flag = true;
        do {
            String exit = "0 - Exit";
            String label1 = "Please select a House Grid to check the total nominal power of a subset of rooms " +
                    "and/or devices of your choosing connected to that grid: \n" + mController.listHouseGrids() + exit;
            int positionHG = InputValidator.getIntRange(label1, 0, mController.getHouseGridListLength());
            if (positionHG == 0) {
                return false;
            }
            mController.getHouseGridbyPosition(positionHG - 1);
            if (mController.checkIfRoomListIsEmpty()) {
                System.out.println("There are no rooms in this house grid. Please, choose another grid or add rooms to the chosen grid.\n");
                continue;
            } else flag = false;
            if (chooseRoom()) {
                return true;
            }
            while (!chooseRoom() && mController.checkIfMeasurableListIsEmpty()) {
                chooseRoomsAndOrDevicesInGrid();
            }
        } while (flag);
        return true;
    }

    private boolean chooseRoom() {
        String exit = "0 - Exit";
        boolean flag2 = true;
        do {
            String label2 = "Please select a room to check its nominal power or the nominal power of a subset " +
                    "of devices located in that room: \n" + mController.getRoomsInTheHouseGrid() + exit;
            int positionRoom = InputValidator.getIntRange(label2, 0, mController.getSizeOfRoomListInGrid()) - 1;
            if (positionRoom == -1 && !mController.checkIfMeasurableListIsEmpty()) {
                String label6 = "Are you ready to get the total nominal power of the selected rooms/devices? Y/N.";
                String confirmation = InputValidator.confirmValidation(label6);
                if ("N".equals(confirmation) || "n".equals(confirmation)) {
                    continue;
                } else {
                    return true;
                }
            }
            if (positionRoom == -1) {
                return false;
            }
            if (mController.checkIfDeviceListIsEmpty(positionRoom)) {
                System.out.println("There are no devices in this room. Please, choose another room or add devices to the chosen room.\n");
                continue;
            }
            if (mController.checkIfObjInList(mController.getChosenRoomInTheGrid(positionRoom))) {
                System.out.println("That room was already chosen.");
            } else {
                if (addFirstDevice(positionRoom)) {
                    addAnotherDeviceOrNot(positionRoom);
                }
            }
            String label5 = "Do you want to add other rooms or devices? (Y/N)";
            String confirmation = InputValidator.confirmValidation(label5);
            if ("N".equals(confirmation) || "n".equals(confirmation)) {
                flag2 = false;
            }
        } while (flag2);
        return true;
    }

    private boolean addFirstDevice(int positionRoom) {
        String exit = "0 - Exit";
        String label3 = "Please select a device to check its nominal power: \n"
                + mController.getContentOfDeviceListInRoomOfGrid(positionRoom) +
                (mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) + " - Total nominal power of room\n" + exit;
        int positionDevice = InputValidator.getIntRange(label3, 0, mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1);
        if (positionDevice == 0) {
            return false;
        }
        if (positionDevice == mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) {
            mController.addAMeasurableObject(mController.getChosenRoomInTheGrid(positionRoom));
            return false;
            //continue;
        } else {
            if (mController.checkIfObjInList(mController.getDeviceFromPositionInList(positionRoom, positionDevice - 1))) {
                System.out.println("That device was already chosen.");
            }
            mController.addAMeasurableObject(mController.getDeviceFromPositionInList(positionRoom, (positionDevice - 1)));
        }
        return true;
    }


    private void addAnotherDeviceOrNot(int positionRoom) {
        String label4 = "Would you like to add any other device of this room to the nominal power calculations? If yes," +
                "please type the number of that device; if not, please type 0.";
        boolean flag3 = true;
        do {
            int addOtherDeviceOrNot = InputValidator.getIntRange(label4, 0, mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1);
            if (!(addOtherDeviceOrNot == 0)) {
                //flag3 = false;
                if (addOtherDeviceOrNot == mController.getSizeOfListOfDevicesInARoom(positionRoom) + 1) {
                    label4 = "You have already selected at least a device from this room. It is not possible to add the total nominal power of this room to calculations.\nPlease type the number of another device.";
                } else {
                    if (mController.checkIfObjInList(mController.getDeviceFromPositionInList(positionRoom, addOtherDeviceOrNot - 1))) {
                        label4 = "That device was already chosen.\nWould you like to add any other device of this room to the nominal power calculations? If yes please type the number of that device; if not, please type 0.";
                    } else {
                        mController.addAMeasurableObject(mController.getDeviceFromPositionInList(positionRoom, addOtherDeviceOrNot - 1));
                        label4 = "Would you like to add any other device of this room to the nominal power calculations? If yes," +
                                "please type the number of that device; if not, please type 0.";
                        continue;
                    }
                }
            } else {
                flag3 = false;
            }
        } while (flag3);
    }


    public void run() {
        if (mController.checkIfGridListIsEmpty()) {
            System.out.println("There are no house grids in the house. Please, add one.\n");
            return;
        }
        chooseRoomsAndOrDevicesInGrid();
        if (!mController.checkIfMeasurableListIsEmpty()) {
            System.out.println("The total nominal power for the selected subset of rooms and/or devices is " +
                    mController.getNominalPowerOfSelectedMeasurableObjects() + "\n");
        }
    }
}
