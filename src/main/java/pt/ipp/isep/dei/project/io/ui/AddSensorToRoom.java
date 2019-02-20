package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.AddSensorToRoomController;
import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Scanner;

/** US253 As an Administrator, I want to add a new sensor to a room from the list of available
sensor types, in order to configure it. */

public class AddSensorToRoom {

    private AddSensorToRoomController controller;

    public AddSensorToRoom(House house, RoomList roomList, SensorTypeList listSensorsType) {
        controller = new AddSensorToRoomController(listSensorsType, roomList, house);
    }

    public void run() {

        Scanner read = new Scanner(System.in);

        if (controller.isRoomListEmpty()) {
            System.out.println("There are no rooms in the house. Please create a room");
        } else if (controller.isSensorTypeListEmpty()) {
            System.out.println("There are no sensor types created. Please create one.");
        } else {
            System.out.println("To which room do you want to add a sensor?");
            System.out.println(controller.getRoomListContent());
            int positionOfTheRoom = read.nextInt() - 1;
            read.nextLine();
            controller.getRoomByIndex(positionOfTheRoom);

            System.out.println("What's the name of the new sensor?");
            String nameOfSensor = read.nextLine();

            System.out.println("Please, select a sensor type");
            System.out.println(controller.displayListOfSensorsType());
            int positionOfTheSensorType = read.nextInt() - 1;
            read.nextLine();
            controller.getSensorTypeByIndex(positionOfTheSensorType);

            controller.getLocationOfTheHouse();

            if (controller.createAndAddSensorToTheList(nameOfSensor)) {
                System.out.println("A sensor was added to the room.");
            } else {
                System.out.println("Sensor was not created.");
            }

        }
    }
}
