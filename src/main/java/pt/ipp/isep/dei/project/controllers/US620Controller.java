package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;


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

    public double getTotalRainfallInTheHouseAreaInTheSelectedDay(LocalDate day) {
        return mHouse.getTotalDailyMeasurement(mTypeRainfall, day);
    }

    public double getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(LocalDate date1, LocalDate date2) {
        return mHouse.getAverageDailyMeasurement(mTypeRainfall, date1, date2);
    }

    public LocalDate createANewDate(int year, int month, int day) {
        return mHouse.createANewDate(year, month, day);
    }

    public double getMostRecentAvailableMeasurementInTheHouseArea() {
        return mHouse.getLastMeasurement(mTypeTemperature);
    }

    public String getmTypeTemperature() {
        return mTypeTemperature.getmType();
    }
}
