package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetCurrentAndMaxTempRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;
import java.util.Objects;


/** US605 As a Regular User, I want to get the current temperature in a room, in order to check
if it meets my personal comfort requirements.
US610 As a Regular User, I want to get the maximum temperature in a room in a given day,
in order to check if heating/cooling in that room was effective.*/

public class GetCurrentAndMaxTempRoom {

    private GetCurrentAndMaxTempRoomController mctrl;

    /**
     * constructor that receives a House and a SensorTypeList
     *
     * @param house          House
     * @param sensorType   SensorType
     */
    public GetCurrentAndMaxTempRoom(House house, SensorType sensorType) {
        this.mctrl = new GetCurrentAndMaxTempRoomController(house, sensorType);
    }

    /**
     * method that displays the rooms available to the user, so he can choose one
     * to get the current temperature.
     */
    public void run1() {

        System.out.println(mctrl.getDisplayRoomList());
        if (mctrl.getDisplayRoomList().isEmpty()) {
            System.out.println("There are no rooms available\n");
            return;
        }

        String label0 = "Choose the room you want to get the current temperature";
        int option = InputValidator.getIntRange(label0, 1, mctrl.lengthOfRoomList());
        String roomName = mctrl.getNameOfTheChosenRoomInSpecificPos(option - 1);
        Measurement temp = mctrl.getLatestMeasurementByRoomName(roomName);

        if (Objects.isNull(temp)) {
            System.out.println("There are no temperature values available");
            return;
        }

        this.displayResults(roomName, temp.getmValue(), temp.getmDateTime().toString());
    }

    /**
     * outputs the latest temperature with date, of the room
     * @param roomName given String room name
     * @param temp given double temperature
     * @param dateTime given string date and time
     */
    private void displayResults(String roomName, double temp, String dateTime) {
        StringBuilder content = new StringBuilder();
        content.append("The latest temperature of the room ");
        content.append(roomName);
        content.append(" is ");
        content.append(temp);
        content.append(" and was registered at ");
        content.append(dateTime);
        content.append(".\n");
        System.out.println(content.toString());
    }

    public void run2() {

        System.out.println(mctrl.getDisplayRoomList());
        if (mctrl.getDisplayRoomList().isEmpty()) {
            System.out.println("There are no rooms available\n");
            return;
        }

        String label0 = "Choose the room you want to get the maximum temperature";
        int option = InputValidator.getIntRange(label0, 1, mctrl.lengthOfRoomList());
        String roomName = mctrl.getNameOfTheChosenRoomInSpecificPos(option - 1);

        String label1 = "Please insert the date when you want to get the maximum temperature (valid numbers between 2000 and 2019 in the format yyyy-MM-dd):";
        LocalDate dateLD = InputValidator.getStringDate(label1);

        double temp = mctrl.getMaximumTemperatureOfARoomInAGivenDay(roomName, mctrl.getmType(), dateLD);
        if (Double.isNaN(temp)) {
            System.out.println("There are no temperature values available");
            return;
        }

        System.out.println("The maximum temperature is " + temp + "ºC");
    }
}