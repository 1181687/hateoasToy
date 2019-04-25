package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.readings.GeoAreaReading;
import pt.ipp.isep.dei.project.model.readings.ReadingDTO;
import pt.ipp.isep.dei.project.model.readings.ReadingMapper;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeoAreaAggregateService;


public class GetInfoHouseAreaController {
    private Location houseLocation;
    private GeoAreaId houseAreaId;
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
        this.houseAreaId = house.getAddress().getInsertedGeoArea().getId();
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
     * @return Latest reading.
     */
    public ReadingDTO getCurrentTemperature() {
        GeoAreaReading geoAreaReading = geoAreaAggregateService.getLatestMeasurement(houseLocation, houseAreaId, temperature);
        return ReadingMapper.mapToDTO(geoAreaReading);
    }

    /**
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

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house parameter
     */
    /*public GetFirstHottestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    /**
     * boolean method to check if the sensorlist of a given type is empty or not
     *
     * @return boolean - true if it is empty; false if it is not empty (there are sensor of the selected type)
     */
    /*public boolean isSensorListOfATypeEmpty() {
        return house.isSensorListOfAGivenTypeEmpty(sensorTypeTemperature);
    }

    /**
     * boolean method that checks if there are readings between selected dates of the chosen sensor (the nearest
     * one with most recent readings)
     *
     * @param startDate Initial of the period the user was to consider
     * @param endDate   Final of the period the user was to consider
     * @return boolean - true if there are readings in the interval; false if there are no readings in the interval
     */
    /*public boolean checkNearestSensorReadingsExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkNearestSensorReadingsExistenceBetweenDates(sensorTypeTemperature, startDate, endDate);
    }

    /**
     * method that turns a reading into a readingDTO
     *
     * @param startDate Initial of the period the user was to consider
     * @param endDate   Final of the period the user was to consider
     * @return ReadingDTO
     */
    /*public ReadingDTO getFirstHighestReadingHouseArea(LocalDate startDate, LocalDate endDate) {
        return ReadingMapper.mapToDTO(house.getFirstHighestReadingHouseArea(sensorTypeTemperature, startDate, endDate));
    }*/
}
