package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.US253Controller;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.ListaTiposSensores;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.Scanner;

public class US253UI {

    private US253Controller mUS253Controller;

    public US253UI(House house, RoomList roomList, ListaTiposSensores listSensorsType) {
        mUS253Controller = new US253Controller(listSensorsType, roomList, house);
    }

    public void run() {

        Scanner read = new Scanner(System.in);

        if (mUS253Controller.checkIfRoomListIsEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room");
        } else if (mUS253Controller.checkIfTheListOfSensorTypeIsEmpty()) {
            System.out.println("There are no sensor types created. Please create one.");
        } else {
            System.out.println("To which room do you want to add a sensor?");
            System.out.println(mUS253Controller.displayRoomsInTheHouse());
            int positionOfTheRoom = read.nextInt() - 1;
            read.nextLine();
            mUS253Controller.getRoomByIndex(positionOfTheRoom);

            System.out.println("What's the name of the new sensor?");
            String nameOfSensor = read.nextLine();

            System.out.println("Please, select a sensor type");
            System.out.println(mUS253Controller.displayListOfSensorsType());
            int positionOfTheSensorType = read.nextInt() - 1;
            read.nextLine();
            mUS253Controller.getSensorTypeByIndex(positionOfTheSensorType);

            mUS253Controller.getLocationOfTheHouse();

            if (mUS253Controller.createAndAddSensorToTheList(nameOfSensor)) {
                System.out.println("A sensor was added to the room.");
            } else {
                System.out.println("Sensor was not created.");
            }

        }
    }
}
