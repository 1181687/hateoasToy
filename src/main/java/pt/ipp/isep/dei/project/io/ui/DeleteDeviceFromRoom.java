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
        boolean flag = true;
        do {
            String exit = "0 - Exit";
            String label1 = "Please select the room where you want to remove the device: \n" +
                    mController.getRoomListContent() + exit;

            int roomListSize = mController.roomListSize();
            int positionRoom = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
            if (positionRoom == 0) {
                return;
            }
            mController.getRoomNameByPosition(positionRoom - 1);


            // LIST OF DEVICES
            boolean flag2 = true;
            do {
                String exit1 = "0 - Back to previous menu";
                if (mController.isDeviceListEmpty(positionRoom)) {
                    System.out.println("There are no devices in this room. Please, choose another room or add devices to the chosen room.\n");
                    continue;
                }

                String label2 = "Please select the device you want to delete: \n"
                        + mController.getDeviceListContent(positionRoom) + exit1;
                int positionDevice = InputValidator.getIntRange(label2, 0, mController.getDeviceListLength() + 1);
                if (positionDevice == 0) {
                    break;
                }

                String label3 = "Are you sure do you want to delete this device? (Y/N)";
                String answer = InputValidator.confirmValidation(label3);
                if ("y".equals(answer) || "Y".equals(answer)) {

                    String deletedDevice = mController.getDeviceNameByPosition(positionDevice);
                    if (positionDevice == mController.getDeviceListSize(positionDevice) + 1) {
                        mController.deleteDevice(deletedDevice, positionDevice);
                        System.out.println("\n The device has been deleted. \n");
                    }
                } else {
                    break;
                }
            } while (flag2);
        }
        while (flag);
    }
}
