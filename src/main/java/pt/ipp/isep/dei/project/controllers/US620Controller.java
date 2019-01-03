package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;
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

    public double getTotalRainfallInTheHouseAreaInTheSelectedDay (int year, int month, int day){
        Date day1 = mHouse.createANewDate(year, month, day);
        return mHouse.getTotalDailyMeasurementOfHouseArea(mType, day1);
    }

    public double getAverageDailyRainfallInTheHouseAreaInTheSelectedPeriod(int year1, int month1, int day1, int year2, int month2, int day2){
        Date date1 = mHouse.createANewDate(year1, month1, day1);
        Date date2 = mHouse.createANewDate(year2, month2, day2);
        return mHouse.getAverageDailyMeasurementOfHouseArea(mType, date1, date2);
    }



}
