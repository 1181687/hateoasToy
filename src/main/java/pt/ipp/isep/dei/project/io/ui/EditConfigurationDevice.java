package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.House;

public class EditConfigurationDevice {

    private EditConfigurationDeviceController controller;

    public EditConfigurationDevice(House house) {
        this.controller = new EditConfigurationDeviceController(house);
    }

    public void run() {

        StringBuilder content = new StringBuilder();
        if (this.controller.checkIfRoomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room.");

        } else {
            String label1 = "Please select the room with the device you want to edit.";
            int roomListSize = controller.roomListSize();
            System.out.println(controller.getRoomListContent());
            int position = InputValidator.getIntRange(label1, 1, roomListSize)-1;
            controller.getRoomByPosition(position);

            if (this.controller.checkIfDeviceListIsEmpty()) {
                System.out.println("There are no devices in this room.");

            } else {
                String label2 = "Please select the device you want to edit. \n" + controller.getDevicesInTheRoom();
                int deviceListLength = controller.getDeviceListLength();
                int position1 = InputValidator.getIntRange(label2, 1, deviceListLength)-1;
                controller.getDeviceByPosition(position1);

                String label3 = "What do you want to change? \n"+ controller.getDeviceAttributesToString();
                int option = InputValidator.getIntRange(label3, 1, 3);

                switch (option) {
                    case 1:
                        String label4 = "What name do you want to assign to the device?";
                        String newName = InputValidator.getString(label4);
                        controller.setDeviceName(newName);
                        content.append("The name was changed with success! Now, this device call " + newName + "! \n");
                        break;

                    case 2:
                        String label5 = "Choose the specification you want to change. \n" + controller.getSpecsAttributesToString();
                        int attribute = InputValidator.getIntRange(label5,1,controller.getNumberOfAttributesInDeviceSpecs());
                        String label6 = "What is the new value?";
                        double value = InputValidator.getDouble(label6);
                        controller.setDeviceSpecs(attribute, value);
                        content.append("The value was changed with success! Now, the value is " + value + "! \n");
                        break;
                    case 3:
                        String label7 = "To which room do you want to change the device? \n" + controller.getRoomListContent();
                        int roomListLength1 = controller.roomListSize();
                        int positionRoom = InputValidator.getIntRange(label7, 1, roomListLength1)-1;
                        controller.getNewRoom(positionRoom);
                        controller.setLocation();
                        content.append("The location of the device was changed with success! Now, the room is ");
                        String room = controller.getRoomName(positionRoom);
                        content.append(room);
                        content.append("! \n");
                        break;
                }
            }
        }
        System.out.println(content.toString());
    }
}
