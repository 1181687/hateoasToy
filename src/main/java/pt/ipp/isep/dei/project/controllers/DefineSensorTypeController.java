package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.SensorType;
import pt.ipp.isep.dei.project.model.SensorTypeList;

public class DefineSensorTypeController {
    private SensorTypeList sensorTypeList;


    public DefineSensorTypeController(SensorTypeList sensorTypeList) {
        this.sensorTypeList = sensorTypeList;
    }

    public boolean createAndAddSensorType(String sensorType){
        SensorType newSensorType = this.sensorTypeList.newSensorType(sensorType);
        return this.sensorTypeList.addSensorType(newSensorType);
    }
}
