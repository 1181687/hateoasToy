package pt.ipp.isep.dei.project.controllers.getFirstHottestDayHouseAreaController;

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

    public boolean sensorListOfATypeIfEmpty(SensorType type) {
        return house.getTheSensorListOfAGivenType(type).isEmpty();
    }

    public void getChosenSensor() {
        chosenSensor = house.getNearestSensorWithMostRecentReading(sensorTypeTemperature, house.getLocation());
    }

    public boolean checkSensorReadingsExistenceBetweenDates(LocalDate startDate, LocalDate endDate) {
        return chosenSensor.checkMeasurementExistenceBetweenDates(startDate, endDate);
    }

    public LocalDate getFirstHighestReadingDateHouseArea(LocalDate startDate, LocalDate endDate) {
        return house.getFirstHighestReadingDateHouseArea(house.getLocation(), sensorTypeTemperature, startDate, endDate);
    }

    public Double getFirstHighestReadingValueHouseArea(LocalDate startDate, LocalDate endDate) {
        return house.getFirstHighestReadingValueHouseArea(house.getLocation(), sensorTypeTemperature, startDate, endDate);
    }

    public SensorType getTypeTemperature() {
        return sensorTypeTemperature;
    }
}
