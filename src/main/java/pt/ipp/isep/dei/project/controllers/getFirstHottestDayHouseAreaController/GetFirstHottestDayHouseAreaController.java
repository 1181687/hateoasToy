package pt.ipp.isep.dei.project.controllers.getFirstHottestDayHouseAreaController;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;

public class GetFirstHottestDayHouseAreaController {
    private House house;
    private SensorType sensorTypeTemperature;

    public GetFirstHottestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public double getHighestReadingOfASensor(LocalDate startDate, LocalDate endDate) {
        return house.getHighestReadingOfASensor(startDate, endDate);
    }

    public LocalDate getFirstHighestReadingDateHouseArea(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        return house.getFirstHighestReadingDateHouseArea(location, type, startDate, endDate);
    }

    public Double getFirstHighestReadingValueHouseArea(Location location, SensorType type, LocalDate startDate, LocalDate endDate) {
        return house.getFirstHighestReadingValueHouseArea(location, type, startDate, endDate);
    }

    public Location getHouseLocation() {
        return house.getLocation();
    }

    public SensorType getTypeTemperature() {
        return sensorTypeTemperature;
    }

    public boolean checkMeasurementExistenceBetweenDates(Location location, LocalDate startDate, LocalDate endDate) {
        return house.checkMeasurementExistenceBetweenDates(location, startDate, endDate);
    }
}
