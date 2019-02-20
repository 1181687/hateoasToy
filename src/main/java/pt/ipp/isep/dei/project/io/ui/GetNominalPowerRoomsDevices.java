package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetNominalPowerRoomsDevicesController;
import pt.ipp.isep.dei.project.model.House;

public class GetNominalPowerRoomsDevices {

    private GetNominalPowerRoomsDevicesController mController;
    private static final String EXIT_OPTION = "0 - Exit";

    public GetNominalPowerRoomsDevices(House house) {
        this.mController = new GetNominalPowerRoomsDevicesController(house);
    }

    public void chooseSubsetOfRoomsDevices() {
        if (!mController.isGridListEmpty()) {
            String label1 = "Please select a House Grid to check the total nominal power of a subset of rooms " +
                    "and/or devices of your choosing connected to that grid: \n" + mController.getHouseGridsListToString() + EXIT_OPTION;
            int positionHG = InputValidator.getIntRange(label1, 0, mController.getHouseGridListSize());
            if (positionHG == 0) {
                return;
            }
            mController.getHouseGridByPosition(positionHG - 1);
            afterChoosingHouseGrid();
        } else {
            System.out.println("There are no house grids in the house. Please, add one.\n");
            return;
        }
        System.out.println("You have selected the following rooms/devices:\n" + mController.getListToString() + "The total nominal power for the selected subset of rooms and/or devices is " + mController.getNominalPowerOfMeasurableObjects() + " kW\n");
    }

    public void afterChoosingHouseGrid() {
        boolean flag = true;
        do {
            if (!mController.roomListOfHouseGridIsEmpty()) {
                selectDevicesAndRooms();
                flag = false;
            } else {
                System.out.println("There are no rooms in this house grid. Please, choose another grid or add rooms to the chosen grid.\n");
                continue;
            }
        } while (flag);
    }


    public void selectDevicesAndRooms() {
        boolean flag = true;
        do {
            String label2 = "Please select a room to check its nominal power or the nominal power of a subset " +
                    "of devices located in that room: \n" + mController.getRoomListInHouseGridToString() + EXIT_OPTION;
            int positionRoom = InputValidator.getIntRange(label2, 0, mController.getRoomListInHouseGridSize()) - 1;

            if (positionRoom == -1) {
                return;
            }
            if (mController.deviceListIsEmpty(positionRoom)) {
                System.out.println("There are no devices in this room. Please, choose another room or add devices to the chosen room.\n");
                return;
            }
            if (!mController.isMeasurableInList(mController.getRoomOfHouseGridByPosition(positionRoom))) {
                selectDevice(positionRoom);
            } else {
                System.out.println("That room was already chosen.");
            }
            String label5 = "Do you want to add other rooms or devices? (Y/N)";
            String confirmation = InputValidator.confirmValidation(label5);
            if ("N".equals(confirmation) || "n".equals(confirmation)) {
                flag = false;
            }
        } while (flag);
    }

    public void selectDevice(int positionRoom) {
        String label3 = "Please select a device to check its nominal power: \n"
                + mController.getDeviceListToString(positionRoom) +
                (mController.getDeviceListSize(positionRoom) + 1) + " - Total nominal power of room\n" + EXIT_OPTION;
        int positionDevice = InputValidator.getIntRange(label3, 0, mController.getDeviceListSize(positionRoom) + 1);
        if (positionDevice == 0) {
            return;
        }
        if (positionDevice == mController.getDeviceListSize(positionRoom) + 1) {
            mController.addMeasurable(mController.getRoomOfHouseGridByPosition(positionRoom));
            return;
        } else {
            if (mController.isMeasurableInList(mController.getDeviceListByPosition(positionRoom, positionDevice - 1))) {
                System.out.println("That device was already chosen.");
            }
            mController.addMeasurable(mController.getDeviceListByPosition(positionRoom, (positionDevice - 1)));
        }
        addAnotherDeviceOrNot(positionRoom);
    }

    public void addAnotherDeviceOrNot(int positionRoom) {
        String label4 = "Would you like to add any other device of this room to the nominal power calculations? \nIf yes," +
                "please type the number of that device; if not, please type 0.";
        boolean flag3 = true;
        do {
            int addOtherDeviceOrNot = InputValidator.getIntRange(label4, 0, mController.getDeviceListSize(positionRoom) + 1);
            if (addOtherDeviceOrNot == 0) {
                return;
            }
            if (addOtherDeviceOrNot != mController.getDeviceListSize(positionRoom) + 1) {

                if (mController.isMeasurableInList(mController.getDeviceListByPosition(positionRoom, addOtherDeviceOrNot - 1))) {
                    label4 = "That device was already chosen.\nWould you like to add any other device of this room to the nominal power calculations? If yes please type the number of that device; if not, please type 0.";
                } else {
                    mController.addMeasurable(mController.getDeviceListByPosition(positionRoom, addOtherDeviceOrNot - 1));
                    label4 = "Would you like to add any other device of this room to the nominal power calculations? \nIf yes," +
                            "please type the number of that device; if not, please type 0.";
                    continue;
                }
            } else {
                label4 = "You have already selected at least a device from this room. It is not possible to add the total nominal power of this room to calculations.\nPlease type the number of another device.";
            }
        } while (flag3);
    }

    public void run() {
        chooseSubsetOfRoomsDevices();
    }
}
