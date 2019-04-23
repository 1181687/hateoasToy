package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.services.SensorTypeService;

public class DefineSensorTypeController {

    private SensorTypeService sensorTypeService;

    public DefineSensorTypeController(SensorTypeService sensorTypeService) {
        this.sensorTypeService = sensorTypeService;
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