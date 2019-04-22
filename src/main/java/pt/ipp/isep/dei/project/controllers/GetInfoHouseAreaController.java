package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;


public class GetInfoHouseAreaController {
    private Location houseLocation;
    private GeoAreaAggregateService geoAreaAggregateService;
    private SensorTypeId rainfall;
    private SensorTypeId temperature;


    /**
     * Constructor.
     *
     * @param house                House to be used.
     * @param geoAreaAggregateService Service to be used.
     */
    public GetInfoHouseAreaController(House house, GeoAreaAggregateService geoAreaAggregateService) {
        this.houseLocation = house.getAddress().getLocation();
        this.geoAreaAggregateService = geoAreaAggregateService;
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
     * Method that returns the most recent [valid] temperature reading of the closest sensor to the house.
     *
     * @return Map with the date time and value of the reading.
     */
/*    public HashMap<LocalDateTime, Double> getCurrentTemperature() {
        return geoAreaAggregateService.getLatestMeasurementByTypeAndLocation(houseLocation, temperature);
    }

    *//**
     * Method that returns the total daily rainfall reading of the closest sensor to the house.
     *
     * @return Double with the value of the total rainfall in the selected day.
     *//*
    public Double getTotalRainfallInTheHouseAreaInTheSelectedDay(LocalDate day) {
        return geoAreaAggregateService.getTotalDailyMeasurement(houseLocation, rainfall, day);
    }*/
/*
    public double getAverageDailyRainfall(LocalDate date1, LocalDate date2) {
        return house.getAverageDailyMeasurementInHouseArea(rainfall, date1, date2);
    }*/
}
