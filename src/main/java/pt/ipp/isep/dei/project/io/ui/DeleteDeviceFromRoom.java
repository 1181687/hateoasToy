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

        if (this.mController.checkIfRoomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag = true;
            while (flag) {
                String exit = "0 - Return to the previous menu";
                String label1 = "\n> Please select the room with the device you want to delete or deactivate:\n" + mController.getRoomListContent() + exit;
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
                        String label2 = "\n> Please select the device you want to delete or to deativate. \n" + mController.getDevicesInTheRoom() + exit;

                        int deviceListLength = mController.getDeviceListLength();
                        int position1 = InputValidator.getIntRange(label2, 0, deviceListLength) - 1;
                        if (position1 == -1) {
                            flag1 = false;
                            continue;
                        }
                        flag1 = false;


                        // DELETE OR DEATIVATE
                        boolean flag5 = true;
                        while (flag5) {
                            String cenas = "1 - Delete device.\n" + "2 - Deactivate device.\n";
                            String label3 = "Do you want to delete or to deativate the device? \n" + cenas + exit;
                            int option = InputValidator.getIntRange(label3, 0, 2);
                            if (option == -1) {
                                flag5 = false;
                                continue;
                            }

                            switch (option) {
                                case 1:

                                    // CONFIRM TO DELETE
                                    String label4 = "Are you sure do you want to delete this device? (Y/N)";
                                    String answer = InputValidator.confirmValidation(label4);
                                    if ("y".equals(answer) || "Y".equals(answer)) {

                                        String deletedDevice = mController.getDeviceNameByPosition(position1);
                                        if (mController.deleteDevice(deletedDevice)) {
                                            System.out.println("\n The device has been deleted. \n");
                                        }
                                    } else {
                                        continue;
                                    }
                                    while (flag) ;
                                    break;

                                case 2:

                                    // CONFIRM TO DEATIVATE
                                    String label5 = "Are you sure do you want to deativate this device? (Y/N)";
                                    String answer2 = InputValidator.confirmValidation(label5);
                                    if ("y".equals(answer2) || "Y".equals(answer2)) {

                                        String deativateDevice = mController.getDeviceNameByPosition(position1);
                                        if (mController.deativateDevice(deativateDevice)) {
                                            System.out.println("\n The device has been deativated. \n");
                                        }
                                    }
                            }
                            flag5 = false;
                        }
                    }
                }
                System.out.println(content.toString());
            }
        }
    }
}
