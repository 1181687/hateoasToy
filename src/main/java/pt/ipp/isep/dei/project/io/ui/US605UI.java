package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorTypeList;
import pt.ipp.isep.dei.project.utils.InputValidator;

public class US605UI {

    private US605Controller mctrl;

    public US605UI(House house, SensorTypeList listatiposSens) {
        this.mctrl = new US605Controller(house, listatiposSens.novoTipoSensor("temperatura"));
    }

    public void run() {

        mctrl.getDisplayRoomList();
        if (mctrl.getDisplayRoomList().isEmpty()) {
            System.out.println("There are no rooms available");
        } else {
            String label0 = "Choose the room you want to get the current temperature";
            int option = InputValidator.getIntRange(label0, 1, mctrl.lengthOfRoomList());
            String roomName = mctrl.getNameOfTheChosenRoomInSpecificPos(option - 1);

            //tenho que prever quarto sem sensores ou sem lista fazia?

            double temp = mctrl.getCurrentTemperatureRoom(roomName);
            if (Double.isNaN(temp)) {
                System.out.println("There are no temperature values available");
            } else {
                StringBuilder content = new StringBuilder();
                content.append("The current temperature of the room ");
                content.append(roomName);
                content.append(" is ");
                content.append(temp);
                content.append(".\n");
                System.out.println(content.toString());
            }
        }
    }


}
