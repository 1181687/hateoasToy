package pt.ipp.isep.dei.project.controllers.getfirsthottestdayhouseareacontroller;

import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
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

    public boolean isSensorListOfATypeEmpty() {
        return house.isSensorListOfAGivenTypeEmpty(sensorTypeTemperature);
    }

    public boolean checkNearestSensorReadingsExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkNearestSensorReadingsExistenceBetweenDates(sensorTypeTemperature, startDate, endDate);
    }

    public ReadingDTO getFirstHighestReadingHouseArea(LocalDate startDate, LocalDate endDate) {
        return ReadingMapper.mapToDTO(house.getFirstHighestReadingHouseArea(sensorTypeTemperature, startDate, endDate));
    }
}