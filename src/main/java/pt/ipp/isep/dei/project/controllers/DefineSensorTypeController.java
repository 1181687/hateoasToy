package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeList;

public class DefineSensorTypeController {
    private SensorTypeList sensorTypeList;


    public DefineSensorTypeController(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    /**
     * Creates a new sensor type and adds it to the list of available sensor types.
     *
     * @param sensorType
     * @return true or false
     */
    public boolean createAndAddSensorType(String sensorType) {
        SensorType newSensorType = this.sensorTypeList.newSensorType(new SensorTypeId(sensorType));
        return this.sensorTypeList.addSensorType(newSensorType);
    }
}
