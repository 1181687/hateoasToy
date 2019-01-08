package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

import java.util.Date;


public class US620Controller {
    private House mHouse;
    private SensorType mTypeRainfall;
    private SensorType mTypeTemperature;


    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public US620Controller(House house) {
        this.mHouse = house;
        this.mTypeRainfall = new SensorType("Rainfall");
        this.mTypeTemperature = new SensorType("temperature");
    }

    public double getTotalRainfallInTheHouseAreaInTheSelectedDay (Date day){
        return mHouse.getTotalDailyMeasurementOfHouseArea(mTypeRainfall, day);
    }

    public double getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(Date date1, Date date2){
        return mHouse.getAverageDailyMeasurementOfHouseArea(mTypeRainfall, date1, date2);
    }

    public Date createANewDate (int year, int month, int day){
        return mHouse.createANewDate(year, month, day);
    }

    public double getMostRecentAvailableMeasurementInTheHouseArea() {
        return mHouse.getLastMeasurementOfTheHouseArea(mTypeTemperature);
    }

    public String getmTypeTemperature() {
        return mTypeTemperature.getmType();
    }
}
