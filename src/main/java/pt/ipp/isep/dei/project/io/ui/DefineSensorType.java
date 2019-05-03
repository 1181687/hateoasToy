package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.DefineSensorTypeController;
import pt.ipp.isep.dei.project.services.SensorTypeService;

/**
 * US005 As an Administrator, I want to define the sensor types.
 */

public class DefineSensorType {

    private DefineSensorTypeController controller;

    public DefineSensorType(SensorTypeService sensorTypeService) {

        this.controller = new DefineSensorTypeController(sensorTypeService);
    }

    public void run() {
        String typeName = InputValidator.getString("Introduce a new sensor type.");
        if (this.controller.createAndAddSensorType(typeName)) {
            System.out.println("Success!");
        } else{
            System.out.println("Try another sensor type!");
        }
    }
}
