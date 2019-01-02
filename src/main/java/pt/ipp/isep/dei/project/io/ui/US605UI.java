package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorTypeList;
import pt.ipp.isep.dei.project.utils.InputValidator;

import java.util.Objects;

public class US605UI {

    private US605Controller mctrl;

    public US605UI(House house, SensorTypeList listatiposSens) {
        this.mctrl = new US605Controller(house, listatiposSens.novoTipoSensor("temperature"));
    }

    public void run() {

        System.out.println(mctrl.getDisplayRoomList());
        if (mctrl.getDisplayRoomList().isEmpty()) {
            System.out.println("There are no rooms available\n");
            return;
        }

        String label0 = "Choose the room you want to get the current temperature";
        int option = InputValidator.getIntRange(label0, 1, mctrl.lengthOfRoomList());
        String roomName = mctrl.getNameOfTheChosenRoomInSpecificPos(option - 1);
        Measurement temp = mctrl.getLatestTemperatureRoom(roomName);

        if (Objects.isNull(temp)) {
            System.out.println("There are no temperature values available");
            return;
        }

        this.displayResults(roomName, temp.getmValue(), temp.getmDateTime().toString());
    }

    private void displayResults(String roomName, double temp, String dateTime) {
        StringBuilder content = new StringBuilder();
        content.append("The latest temperature of the room ");
        content.append(roomName);
        content.append(" is ");
        content.append(temp);
        content.append(" and was regitered at ");
        content.append(dateTime);
        content.append(".\n");
        System.out.println(content.toString());
    }
}
