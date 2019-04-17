package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeoAreaSensorService;

import java.time.LocalDateTime;
import java.util.HashMap;


public class GetTotalAndAverageRainfallAndCurrentTempHouseAreaController {
    private Location houseLocation;
    private GeoAreaSensorService geoAreaSensorService;
    private SensorTypeId rainfall;
    private SensorTypeId temperature;


    /**
     * Constructor
     *
     * @param house                House to be used.
     * @param geoAreaSensorService Service to be used.
     */
    public GetTotalAndAverageRainfallAndCurrentTempHouseAreaController(House house, GeoAreaSensorService geoAreaSensorService) {
        this.houseLocation = house.getAddress().getLocation();
        this.geoAreaSensorService = geoAreaSensorService;
        rainfall = new SensorTypeId("Rainfall");
        temperature = new SensorTypeId("Temperature");
    }

    /**
     * Get method.
     *
     * @return String with the required info.
     */
    public String getTypeTemperature() {
        return temperature.getSensorTypeId();
    }

    /**
     * Method that returns the most recent [valid] reading of the closest sensor to the house.
     *
     * @return Double with the required value.x
     */
    public HashMap<LocalDateTime, Double> getCurrentTemperature() {
        return geoAreaSensorService.getLatestMeasurement(houseLocation, temperature);
    }
    /*
    public double getTotalRainfallInTheHouseAreaInTheSelectedDay(LocalDate day) {
        return house.getTotalDailyMeasurementInHouseArea(sensorTypeRainfall, day);
    }

    public double getAverageDailyRainfall(LocalDate date1, LocalDate date2) {
        return house.getAverageDailyMeasurementInHouseArea(sensorTypeRainfall, date1, date2);
    }*/


}
