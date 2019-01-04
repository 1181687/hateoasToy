package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

import java.util.Date;


public class US620Controller {
    private House mHouse;
    private SensorType mType;


    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public US620Controller(House house) {
        this.mHouse = house;
        this.mType= new SensorType("Rainfall");
    }

    public double getTotalRainfallInTheHouseAreaInTheSelectedDay (Date day){
        return mHouse.getTotalDailyMeasurementOfHouseArea(mType, day);
    }

    public double getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(Date date1, Date date2){
        return mHouse.getAverageDailyMeasurementOfHouseArea(mType, date1, date2);
    }

    public Date createANewDate (int year, int month, int day){
        return mHouse.createANewDate(year, month, day);
    }



}
