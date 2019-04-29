package pt.ipp.isep.dei.project.controllers.getfirsthottestdayhouseareacontroller;

import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;

import java.time.LocalDate;

public class GetFirstHottestDayHouseAreaController {
    private House house;
    private SensorTypeId sensorTypeTemperature;

    /**
     * constructor that receives a House and a SensorType
     *
     * @param house parameter
     */
    public GetFirstHottestDayHouseAreaController(House house) {
        this.house = house;
        this.sensorTypeTemperature = new SensorTypeId("temperature");
    }

    /**
     * boolean method to check if the sensorlist of a given type is empty or not
     *
     * @return boolean - true if it is empty; false if it is not empty (there are sensor of the selected type)
     */
    public boolean isSensorListOfATypeEmpty() {
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
    public boolean checkNearestSensorReadingsExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return house.checkNearestSensorReadingsExistenceBetweenDates(sensorTypeTemperature, startDate, endDate);
    }

    /**
     * method that turns a reading into a readingDTO
     *
     * @param startDate Initial of the period the user was to consider
     * @param endDate   Final of the period the user was to consider
     * @return ReadingDTO
     */
    public ReadingDTO getFirstHighestReadingHouseArea(LocalDate startDate, LocalDate endDate) {
        return ReadingMapper.mapToDTO(house.getFirstHighestReadingHouseArea(sensorTypeTemperature, startDate, endDate));
    }
}
