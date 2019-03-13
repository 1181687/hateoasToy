package pt.ipp.isep.dei.project.controllers.getFirstHottestDayHouseAreaController;

import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.Sensor;
import pt.ipp.isep.dei.project.model.sensor.SensorType;

import java.time.LocalDate;

public class GetFirstHottestDayHouseAreaController {
    private House house;
    private SensorType sensorTypeTemperature;
    private Sensor chosenSensor;


    public GetFirstHottestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorType("temperature");
    }

    public boolean isSensorListOfATypeEmpty() {
        return house.isSensorListOfAGivenTypeEmpty(sensorTypeTemperature);
    }

    public boolean checkSensorReadingsExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        chosenSensor = house.getNearestSensorWithMostRecentReading(sensorTypeTemperature, house.getLocation());
        return chosenSensor.checkMeasurementExistenceBetweenDates(startDate, endDate);
    }

    public ReadingDTO getFirstHighestReadingHouseArea(LocalDate startDate, LocalDate endDate) {
        return ReadingMapper.mapToDTO(house.getFirstHighestReadingHouseArea(sensorTypeTemperature, startDate, endDate));
    }
}
