package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

public class US600Controller {

    private House mHouse;
    private SensorType mType;

    public US600Controller(House house, SensorType sensorType){
        this.mHouse = house;
        this.mType = sensorType;
    }

    public double getMostRecentAvailableMeasurementInTheHouseArea() {
        return mHouse.getLastMeasurementOfTheHouseArea(mType);
    }

    public String getSensorType() {
        return mType.getmTipo();
    }
}
