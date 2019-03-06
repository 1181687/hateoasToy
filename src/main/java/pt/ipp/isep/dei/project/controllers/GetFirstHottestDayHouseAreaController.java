package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.SensorType;

import java.time.LocalDate;

public class GetFirstHottestDayHouseAreaController {
    private House house;
    private SensorType sensorTypeTemperature;

    public GetFirstHottestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public LocalDate getFirstHighestReadingDateHouseArea(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        return house.getFirstHighestReadingDateHouseArea(location, type, startDate, endDate);
    }

    public Location getHouseLocation() {
        return house.getLocation();
    }

    public SensorType getTypeTemperature() {
        return sensorTypeTemperature;
    }
}
