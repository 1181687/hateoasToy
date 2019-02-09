package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DeleteAndDeactivateDeviceFromRoomController;
import pt.ipp.isep.dei.project.model.House;

public class DeactivateDeviceFromRoom {

    private DeleteAndDeactivateDeviceFromRoomController mController;

    public DeactivateDeviceFromRoom(House house) {

        this.mController = new DeleteAndDeactivateDeviceFromRoomController(house);
    }

    public void run() {

        // LIST OF ROOMS
        StringBuilder content = new StringBuilder();

        if (this.mController.roomListEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag10 = true;
            while (flag10) {
                String exitMenu = "0 - Return to the previous menu";
                String label10 = "\n> Please select the room with the device you want to deactivate:\n" + mController.getRoomListContent() + exitMenu;
                int roomListSize = mController.roomListSize();
                int positionRoom = InputValidator.getIntRange(label10, 0, roomListSize) - 1;
                if (positionRoom == -1) {
                    return;
                }
                mController.getRoomPosition(positionRoom);

                // LIST OF DEVICES
                if (this.mController.deviceListEmpty()) {
                    System.out.println("\n There are no devices in this room. \n");

                } else {

                    String label20 = "\n> This is the list of activated devices. Please select the device you want to deativate: \n"
                            + mController.getActiveDeviceListToString() + exitMenu;
                    boolean flag20 = true;
                    while (flag20) {
                        int deviceListLength = mController.deviceListSize();
                        int position1 = InputValidator.getIntRange(label20, 0, deviceListLength) - 1;
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

                                String deativateDevice = mController.deviceNameByPosition(position1);
                                if (mController.deactivateDevice(deativateDevice)) {
                                    System.out.println("\n The device has been deativated. \n");
                                }
                            } else {
                                System.out.println("The device was not deactivated.");
                                continue;
                            }
                        }
                        flag20 = false;
                    }
                }
            }
            System.out.println(content.toString());
        }
    }
}