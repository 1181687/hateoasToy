package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DeleteAndDeactivateDeviceFromRoomController;
import pt.ipp.isep.dei.project.model.House;

public class DeleteDeviceFromRoom {

    private DeleteAndDeactivateDeviceFromRoomController controller;

    public DeleteDeviceFromRoom(House house) {
        this.controller = new DeleteAndDeactivateDeviceFromRoomController(house);
    }


    public void selectDevice() {
        String exitMenu = "0 - Return to the previous menu";
        String label2 = "\n> Please select the device you want to delete. \n" + controller.getDeviceListToString() + exitMenu;
        boolean flag20 = true;
        while (flag20) {
            int deviceListLength = controller.deviceListSize();
            int position1 = InputValidator.getIntRange(label2, 0, deviceListLength) - 1;
            if (position1 == -1) {
                return;
            }

            boolean flag30 = true;
            while (flag30) {
                // CONFIRM
                String label3 = "Are you sure do you want to delete this device? (Y/N)";
                String answer = InputValidator.confirmValidation(label3);
                if ("y".equals(answer) || "Y".equals(answer)) {

                    String deletedDevice = controller.deviceNameByPosition(position1);
                    controller.deleteDevice(deletedDevice);
                    System.out.println("\n The device has been deleted. \n");

                } else {
                    System.out.println("The device was not deleted.");
                    break;
                }
                flag30 = false;
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
        String exit = "0 - Return to the previous menu";
        // LIST OF ROOMS
        StringBuilder content = new StringBuilder();

        if (this.controller.roomListEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag = true;
            while (flag) {
                String label1 = "\n> Please select the room with the device you want to delete:\n" + controller.getRoomListContent() + exit;
                int roomListSize = controller.roomListSize();
                int position = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
                if (position == -1) {
                    return;
                }
                controller.getRoomPosition(position);

                // LIST OF DEVICES
                showListOfDevices();
            }
            System.out.println(content.toString());
        }
    }
}