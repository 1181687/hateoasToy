package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.EditConfigurationDeviceController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.utils.Utils;

public class EditConfigurationDevice {

    private EditConfigurationDeviceController controller;

    public EditConfigurationDevice(House house) {
        this.controller = new EditConfigurationDeviceController(house);
    }


    public void changeNameDevice() {
        boolean flag = true;
        String label4 = "What name do you want to assign to the device?";
        String newName;
        do {
            newName = InputValidator.getString(label4);
            try {
                if (controller.setDeviceName(newName)) {
                    System.out.println("The name was changed with success! Now, this device call " + newName + "!");
                }
                flag = false;
            } catch (Exception e) {
                System.out.println("Name already exists. Please write a new one.");
                flag = true;
            }

        }
        while (flag);
    }

    public void changeSpecsOfDevice() {
        String exitCase2 = "0 - Return to the previous menu  ";
        String listOfDevSpecs = controller.getSpecsToString();

        String label5 = "Choose the specification you want to change.\n" + listOfDevSpecs + "\n" + exitCase2;
        int attributePosition = InputValidator.getIntRange(label5, 0, controller.getNumberOfAttributesInDeviceSpecs());

        if (attributePosition == 0) {
            return;
        }

        String attributeName = controller.getSpecsList().get(attributePosition - 1);
        String label6 = "What is the new value?";
        Number value = InputValidator.getNumber(label6, controller.getAttributeType(attributeName));
        if (Utils.isSameNumber(value.doubleValue(), 0)
                || Utils.isSameNumber(value, controller.getAttributeValue(attributeName))) {
            System.out.println("This value is not valid.");
            return;
        } else {
            controller.setDeviceSpecs(attributeName, value);
            System.out.println("The value was changed with success!\nNow, the value is " + value + "! \n");
            return;
        }
    }

    public void changeLocationOfDevice() {
        String exitCase3 = "0 - Return to the previous menu ";
        StringBuilder content = new StringBuilder();
        String label7 = "To which room do you want to change the device?\n" + controller.getRoomListContent() + exitCase3;
        int roomListLength1 = controller.roomListSize();
        int positionRoom = InputValidator.getIntRange(label7, 0, roomListLength1) - 1;
        if (positionRoom == -1) {
            return;
        }
        controller.getNewRoom(positionRoom);
        controller.setLocation();
        content.append("The location of the device was changed with success!\nNow, the room is ");
        String room = controller.getRoomName(positionRoom);
        content.append(room);
        content.append("! \n");
    }

    public void cases() {
        String exitCases = "0 - Back to the previous menu";
        boolean flag2 = true;
        while (flag2) {

            String label3 = "What do you want to change? \n" + controller.getDeviceAttributesToString() + exitCases;
            int option = InputValidator.getIntRange(label3, 0, 3);
            if (option == 0) {
                flag2 = false;
                continue;
            }
            switch (option) {
                case 1:
                    this.changeNameDevice();
                    break;
                case 2:
                    this.changeSpecsOfDevice();
                    break;
                case 3:
                    this.changeLocationOfDevice();
                    break;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 3.");
            }
            flag2 = false;
        }
    }

    public void flag1() {
        String exitFlag = "0 - Back to the previous menu ";
        boolean flag1 = true;
        while (flag1) {
            String label2 = "Please select the device you want to edit:\n" + controller.getDevicesInTheRoom() + exitFlag;
            int deviceListSize = controller.getDeviceListSize();
            int position1 = InputValidator.getIntRange(label2, 0, deviceListSize) - 1;
            if (position1 == -1) {
                return;
            }
            controller.getDeviceByPosition(position1);

            this.cases();
        }
    }

    public void selectDevice() {
        if (!this.controller.deviceListIsEmpty()) {
            flag1();
        } else {
            System.out.println("There are no devices in this room.");
        }
    }

    public void run() {
        String exitMenu = "0 - Back to the previous menu  ";
        StringBuilder content = new StringBuilder();
        if (!this.controller.roomListIsEmpty()) {
            boolean flag = true;
            while (flag) {
                String label1 = "Please select the room with the device you want to edit:\n" + controller.getRoomListContent() + exitMenu;
                int roomListSize = controller.roomListSize();
                int position = InputValidator.getIntRange(label1, 0, roomListSize) - 1;
                if (position == -1) {
                    return;
                }
                controller.getRoomByPosition(position);
                selectDevice();
            }
        } else {
            System.out.println("There are no rooms in the house. Please create a room.");
        }
        System.out.println(content.toString());
    }
}
