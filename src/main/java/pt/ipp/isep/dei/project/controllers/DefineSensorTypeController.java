package pt.ipp.isep.dei.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.services.SensorTypeService;

public class DefineSensorTypeController {

    @Autowired
    private SensorTypeService sensorTypeService;


    public DefineSensorTypeController() {
    }

    /**
     * Creates a new sensor type and adds it to the list of available sensor types.
     *
     * @param sensorType
     * @return true or false
     */
    public boolean createAndAddSensorType(String sensorType) {
        return sensorTypeService.createAndAddSensorType(sensorType);
    }
}
