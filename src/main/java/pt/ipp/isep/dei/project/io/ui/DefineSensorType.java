package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.services.SensorTypeService;

import java.util.Scanner;

/**
 * US005 As an Administrator, I want to define the sensor types.
 */

public class DefineSensorType {

    private DefineSensorTypeController controller;

    public DefineSensorType(SensorTypeService sensorTypeService) {
        this.controller = new DefineSensorTypeController(sensorTypeService);
    }

    public void run() {
        System.out.println("Introduce a new sensor type.");
        Scanner read = new Scanner(System.in);
        String newSensorTypeName = read.nextLine();
        if (this.controller.createAndAddSensorType(newSensorTypeName)) {
            System.out.println("Success!");
        } else {
            System.out.println("Try another sensor type!");
        }
    }
}
