package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Sensor.SensorType;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class GetTotalAndAverageRainfallAndCurrentTempHouseAreaController {
    private House house;
    private SensorType sensorTypeRainfall;
    private SensorType sensorTypeTemperature;


    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeRainfall = new SensorType("Rainfall");
        this.sensorTypeTemperature = new SensorType("temperature");
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
        return sensorTypeTemperature.getType();
    }

    public LocalDateTime getDateOfLastMeasurement() {
        return house.getDateOfLastMeasurementByType(sensorTypeTemperature);
    }
}
