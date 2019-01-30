package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DeativateDeviceFromRoomController;
import pt.ipp.isep.dei.project.model.House;

public class DeativateDeviceFromRoom {

    private DeativateDeviceFromRoomController mController;

    public DeativateDeviceFromRoom(House house) {

        this.mController = new DeativateDeviceFromRoomController(house);
    }

    public void run() {

        // LIST OF ROOMS
        StringBuilder content = new StringBuilder();

        if (this.mController.checkIfRoomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag = true;
            while (flag) {
                String exit = "0 - Return to the previous menu";
                String label1 = "\n> Please select the room with the device you want to deativate:\n" + mController.getRoomListContent() + exit;
                int roomListSize = mController.roomListSize();
                int position = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
                if (position == -1) {
                    return;
                }
                mController.getRoomByPosition(position);

                // LIST OF DEVICES
                if (this.mController.checkIfDeviceListIsEmpty()) {
                    System.out.println("\n There are no devices in this room. \n");

                } else {
                    boolean flag1 = true;
                    while (flag1) {
                        String label2 = "\n> This is the list of activated devices. Please select the device you want to deativate: \n"
                                + mController.getActiveDeviceListToString() + exit;

                        int deviceListLength = mController.getDeviceListLength();
                        int position1 = InputValidator.getIntRange(label2, 0, deviceListLength) - 1;
                        if (position1 == -1) {
                            return;
                        }
                        if (!mController.getDevice(position1).getIsActive()) {
                            System.out.println("This device is already deactive. Please select an active device.");
                        } else {


                            // CONFIRM TO DEATIVATE
                            String label5 = "Are you sure do you want to deativate this device? (Y/N)";
                            String answer2 = InputValidator.confirmValidation(label5);
                            if ("y".equals(answer2) || "Y".equals(answer2)) {

                                String deativateDevice = mController.getDeviceNameByPosition(position1);
                                if (mController.deativateDevice(deativateDevice)) {
                                    System.out.println("\n The device has been deativated. \n");
                                }
                            } else {
                                continue;
                            }

                        }
                        flag1 = false;
                    }
                }
            }
            System.out.println(content.toString());
        }
    }
}