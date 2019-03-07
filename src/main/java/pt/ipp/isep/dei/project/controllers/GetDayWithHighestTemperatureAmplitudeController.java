package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;
import java.util.Map;

/*US633: As a Regular User, I want to get the day with the highest temperature amplitude in the house area in a given period.*/

public class GetDayWithHighestTemperatureAmplitudeController {

    private House house;
    private SensorType sensorTypeTemperature;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house
     */
    public GetDayWithHighestTemperatureAmplitudeController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public Map<LocalDate, Double> getDailyAmplitudeInIntervalInHouseArea(LocalDate startDate, LocalDate endDate) {
        return house.getDailyAmplitudeInIntervalInHouseArea(this.sensorTypeTemperature, this.house.getLocation(), startDate, endDate);
    }

    public Map<LocalDate, Double> getHighestDailyAmplitudeInHouseArea(Map<LocalDate, Double> mapOfDailyAmplitude) {
        return this.house.getInsertedGeoArea().getHighestDailyAmplitude(mapOfDailyAmplitude);
    }
}


