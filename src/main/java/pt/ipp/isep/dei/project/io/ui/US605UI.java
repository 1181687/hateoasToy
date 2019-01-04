package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US605Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Measurement;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.DateTimeException;
import java.util.Objects;

public class US605UI {

    private US605Controller mctrl;

    /**
     * constructor that receives a House and a SensorTypeList
     *
     * @param house          House
     * @param sensorType   SensorType
     */
    public US605UI(House house, SensorType sensorType) {
        this.mctrl = new US605Controller(house, sensorType);
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

        int year = -1, month = -1, day = -1;

        boolean flag;
        do {
            try {
                String label3 = "Choose the date (YEAR) when you want to see the maximum temperature (valid numbers between 2000 and 2019)";
                year = InputValidator.getIntRange(label3, 2000, 2019);

                String label2 = "Choose the date (MONTH) when you want to see the maximum temperature (valid numbers between 1 and 12)";
                month = InputValidator.getIntRange(label2, 1, 12);

                String label1 = "Choose the date (DAY) when you want to see the maximum temperature (valid numbers between 1 and 31)";
                day = InputValidator.getIntRange(label1, 1, 31);

                flag = false;

                mctrl.createANewDate(year, month, day);
            } catch (DateTimeException e) {
                System.out.println(e.getMessage());
                flag = true;
            }
        } while (flag);

        double temp = mctrl.getMaximumTemperatureOfARoomInAGivenDay(roomName, mctrl.getmType(), mctrl.createANewDate(year, month, day));
        System.out.println("The maximum temperature is " + temp);
    }
}