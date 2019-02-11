package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

public class EditConfigurationDevice {

    private EditConfigurationDeviceController controller;

    public EditConfigurationDevice(House house) {
        this.controller = new EditConfigurationDeviceController(house);
    }

    public void run() {
        StringBuilder content = new StringBuilder();
        if (this.controller.roomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            boolean flag = true;
            while (flag) {
                String exit = "0 - Return to the previous menu";
                String label1 = "Please select the room with the device you want to edit.";
                int roomListSize = controller.roomListSize();
                System.out.println(controller.getRoomListContent() + exit);
                int position = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
                if (position == -1) {
                    return;
                }
                controller.getRoomByPosition(position);

                if (this.controller.deviceListIsEmpty()) {
                    System.out.println("There are no devices in this room.");

                } else {
                    boolean flag1 = true;
                    while (flag1) {
                        String label2 = "Please select the device you want to edit. \n" + controller.getDevicesInTheRoom() + exit;
                        int deviceListLength = controller.getDeviceListSize();
                        int position1 = InputValidator.getIntRange(label2, 0, deviceListLength) - 1;
                        if (position1 == -1) {
                            flag1 = false;
                            continue;
                        }
                        controller.getDeviceByPosition(position1);

                        boolean flag2 = true;
                        while (flag2) {
                            String label3 = "What do you want to change? \n" + controller.getDeviceAttributesToString() + exit;
                            int option = InputValidator.getIntRange(label3, 0, 3);
                            if (option == 0) {
                                flag2 = false;
                                continue;
                            }
                            switch (option) {
                                case 1:
                                    controller.case1();
                                    break;

                                case 2:
                                    String label5 = "Choose the specification you want to change.\n" + controller.getDevSpecsAttributesToString() + exit;
                                    int attributePosition = InputValidator.getIntRange(label5, 0, controller.getNumberOfAttributesInDeviceSpecs());
                                    if (attributePosition == 0) {
                                        continue;
                                    }
                                    String label6 = "What is the new value?";
                                    double value = InputValidator.getDouble(label6);
                                    if (Utils.isSameDouble(value, 0)) {
                                        System.out.println("This value is not valid.");
                                        continue;
                                    } else {
                                        controller.setDeviceSpecs(attributePosition, value);
                                        System.out.println("The value was changed with success!\nNow, the value is " + value + "! \n");
                                        break;
                                    }
                                case 3:
                                    String label7 = "To which room do you want to change the device?\n" + controller.getRoomListContent() + exit;
                                    int roomListLength1 = controller.roomListSize();
                                    int positionRoom = InputValidator.getIntRange(label7, 0, roomListLength1) - 1;
                                    if (positionRoom == -1) {
                                        continue;
                                    }
                                    controller.getNewRoom(positionRoom);
                                    controller.setLocation();
                                    content.append("The location of the device was changed with success!\nNow, the room is ");
                                    String room = controller.getRoomName(positionRoom);
                                    content.append(room);
                                    content.append("! \n");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please choose a number between 1 and 3.");
                            }
                            flag2 = false;
                        }
                    }
                }
            }
        }
        System.out.println(content.toString());
    }
}
