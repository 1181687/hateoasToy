package pt.ipp.isep.dei.project.controllers.getlastcoldestdayhouseareacontroller;

import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.SensorType;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.HouseService;

import java.time.LocalDate;

public class GetLastColdestDayHouseAreaController {
    private House house;
    private SensorTypeId sensorTypeTemperature;

    public GetLastColdestDayHouseAreaController(HouseService houseService) {
        this.house = houseService.getHouse();
        this.sensorTypeTemperature = new SensorTypeId("temperature");
    }

    public boolean hasSensorsOfGivenTypeInGeoArea() {
        return this.house.hasSensorsOfCertainTypeInInsertedGeoArea(this.sensorTypeTemperature);
    }

    public boolean hasReadingsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkNearestSensorReadingsExistenceBetweenDates(this.sensorTypeTemperature, startDate, endDate);
    }

    public ReadingDTO getLastLowestMaximumReading(LocalDate startDate, LocalDate endDate) {
        Reading reading = this.house.getLastLowestMaximumReading(this.sensorTypeTemperature, startDate, endDate);
        return ReadingMapper.mapToDTO(reading);
    }


}
