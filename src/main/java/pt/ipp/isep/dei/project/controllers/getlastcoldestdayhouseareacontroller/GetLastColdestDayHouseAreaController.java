package pt.ipp.isep.dei.project.controllers.getlastcoldestdayhouseareacontroller;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;

public class GetLastColdestDayHouseAreaController {
    private House house;
    private SensorType sensorTypeTemperature;

    public GetLastColdestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType(new SensorTypeId("temperature"));
    }

   /* public boolean hasSensorsOfGivenTypeInGeoArea() {
        return this.house.hasSensorsOfCertainTypeInInsertedGeoArea(this.sensorTypeTemperature);
    }

    public boolean hasReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkNearestSensorReadingsExistenceBetweenDates(this.sensorTypeTemperature, startDate, endDate);
    }

    public ReadingDTO getLastLowestMaximumReading(LocalDate startDate, LocalDate endDate) {
        Reading reading = this.house.getLastLowestMaximumReading(this.sensorTypeTemperature, startDate, endDate);
        return ReadingMapper.mapToDTO(reading);
    }*/
}
