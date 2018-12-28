package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;
import pt.ipp.isep.dei.project.model.SensorType;
import pt.ipp.isep.dei.project.model.SensorTypeList;

import java.util.Date;

public class US610Controller {

    private RoomList mRoomList;
    private SensorTypeList mSensorTypeList;

    public US610Controller(RoomList roomList, SensorTypeList sensorTypeList) {
        this.mRoomList = roomList;
        this.mSensorTypeList = sensorTypeList;
    }

    public String getDisplayRoomList() {
        return this.mRoomList.getDisplayRoomList();
    }

    public String displayListOfSensorsType() {
        return this.mSensorTypeList.displaySensorTypeList();
    }

    public double getMaximumTemperatureRoomInAGivenDay(String name, Date date, SensorType sensorType) {
        return this.mRoomList.getRoomByName(name).getMaximumMeasurementInAGivenDay(sensorType, date);
    }
}