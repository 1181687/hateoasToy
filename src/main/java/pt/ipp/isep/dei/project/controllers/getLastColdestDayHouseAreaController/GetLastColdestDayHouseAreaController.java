package pt.ipp.isep.dei.project.controllers.getLastColdestDayHouseAreaController;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;

public class GetLastColdestDayHouseAreaController {
    private House house;
    private SensorType sensorTypeTemperature;

    public GetLastColdestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public boolean hasReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkMeasurementExistenceBetweenDates(this.house.getLocation(), startDate, endDate);
    }

    public ReadingDTO getLastLowestMaximumReading(LocalDate startDate, LocalDate endDate) {
        Reading reading=this.house.getLastLowestMaximumReading(this.sensorTypeTemperature, startDate,endDate);
        return ReadingMapper.mapToDTO(reading);
    }
}
