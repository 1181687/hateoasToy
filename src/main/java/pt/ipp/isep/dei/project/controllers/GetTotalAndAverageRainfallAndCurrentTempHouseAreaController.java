package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class GetTotalAndAverageRainfallAndCurrentTempHouseAreaController {
    private House house;
    private SensorTypeId sensorTypeRainfall;
    private SensorTypeId sensorTypeTemperature;


    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeRainfall = new SensorTypeId("Rainfall");
        this.sensorTypeTemperature = new SensorTypeId("temperature");
    }

    public double getTotalRainfallInTheHouseAreaInTheSelectedDay(LocalDate day) {
        return house.getTotalDailyMeasurementInHouseArea(sensorTypeRainfall, day);
    }

    public double getAverageDailyRainfall(LocalDate date1, LocalDate date2) {
        return house.getAverageDailyMeasurementInHouseArea(sensorTypeRainfall, date1, date2);
    }

    public double getMostRecentAvailableMeasurement() {
        return house.getLastMeasurementByTypeInHouseArea(sensorTypeTemperature);
    }

    public String getTypeTemperature() {
        return sensorTypeTemperature.getSensorTypeId();
    }

    public LocalDateTime getDateOfLastMeasurement() {
        return house.getDateOfLastMeasurementByType(sensorTypeTemperature);
    }
}
