package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DeleteDeviceFromRoomController;
import pt.ipp.isep.dei.project.model.House;

public class DeleteDeviceFromRoom {

    private DeleteDeviceFromRoomController mController;

    public DeleteDeviceFromRoom(House house) {
        this.mController = new DeleteDeviceFromRoomController(house);
    }

    public void run() {

        // LIST OF ROOMS
        StringBuilder content = new StringBuilder();

        if (this.mController.roomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag = true;
            while (flag) {
                String exit = "0 - Return to the previous menu";
                String label1 = "\n> Please select the room with the device you want to delete:\n" + mController.getRoomListContent() + exit;
                int roomListSize = mController.roomListSize();
                int position = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
                if (position == -1) {
                    return;
                }
                mController.getRoomByPosition(position);

                // LIST OF DEVICES
                if (this.mController.deviceListIsEmpty()) {
                    System.out.println("\n There are no devices in this room. \n");

                } else {

                    String label2 = "\n> Please select the device you want to delete. \n" + mController.getDeviceListToString() + exit;

                    int deviceListLength = mController.getDeviceListSize();
                    int position1 = InputValidator.getIntRange(label2, 0, deviceListLength) - 1;
                    if (position1 == -1) {
                        return;
                    }

                    // CONFIRM
                    String label3 = "Are you sure do you want to delete this device? (Y/N)";
                    String answer = InputValidator.confirmValidation(label3);
                    if ("y".equals(answer) || "Y".equals(answer)) {

                        String deletedDevice = mController.getDeviceNameByPosition(position1);
                        if (mController.deleteDevice(deletedDevice)) {
                            System.out.println("\n The device has been deleted. \n");
                        }
                    } else {
                        System.out.println("The device was not deleted.");
                        continue;
                    }
                }
            }
        }
        System.out.println(content.toString());
    }
}