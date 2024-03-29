package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DeleteAndDeactivateDeviceFromRoomController;
import pt.ipp.isep.dei.project.model.house.House;

public class DeactivateDeviceFromRoom {

    private DeleteAndDeactivateDeviceFromRoomController controller;

    public DeactivateDeviceFromRoom(House house) {

        this.controller = new DeleteAndDeactivateDeviceFromRoomController(house);
    }


    public void selectDevice() {
        String exitMenu = "0 - Return to the previous menu";
        String label20 = "\n> This is the list of activated devices. Please select the device you want to deativate: \n"
                + controller.getActiveDeviceListToString() + exitMenu;
        boolean flag20 = true;
        while (flag20) {
            int deviceListLength = controller.deviceListSize();
            int position1 = InputValidator.getIntRange(label20, 0, deviceListLength) - 1;
            if (position1 == -1) {
                return;
            }
            if (controller.getDevice(position1).getIsActive()) {
                // CONFIRM TO DEATIVATE
                String label5 = "Are you sure do you want to deativate this device? (Y/N)";
                String answer2 = InputValidator.confirmValidation(label5);
                if ("y".equals(answer2) || "Y".equals(answer2)) {

                    String deativateDevice = controller.deviceNameByPosition(position1);
                    controller.deactivateDevice(deativateDevice);
                    System.out.println("\n The device has been deativated. \n");

                } else {
                    System.out.println("The device was not deactivated.");
                    continue;
                }
            } else {
                System.out.println("This device is already deactive. Please select an active device.");
            }
            flag20 = false;
        }
    }

    public void showListOfDevices() {

        if (this.controller.deviceListEmpty()) {
            System.out.println("\n There are no devices in this room. \n");

        } else {
            selectDevice();
        }
    }

    public void run() {

        // LIST OF ROOMS
        String exit = "0 - Return to the previous menu";
        StringBuilder content = new StringBuilder();
        if (!this.controller.roomListEmpty()) {
            boolean flag10 = true;
            while (flag10) {
                String label10 = "\n> Please select the room with the device you want to deactivate:\n" + controller.getRoomListContent() + exit;
                int roomListSize = controller.roomListSize();
                int positionRoom = InputValidator.getIntRange(label10, 0, roomListSize) - 1;
                if (positionRoom == -1) {
                    return;
                }
                controller.getRoomPosition(positionRoom);

                // LIST OF DEVICES
                showListOfDevices();
            }
            System.out.println(content.toString());
        } else {
            System.out.println("There are no rooms in the housegrid. Please create a room.");
        }
    }
}