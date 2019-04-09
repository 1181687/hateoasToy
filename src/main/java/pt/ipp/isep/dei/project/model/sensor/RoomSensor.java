package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.Reading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomSensor implements Sensor {

    private String id;
    private String sensorName;
    private LocalDateTime startingDate;
    private List<Reading> listOfReadings = new ArrayList<>();
    private SensorType sensorType;
    private String units;
    private boolean isActive;

    public RoomSensor(String id, String sensorName, LocalDateTime startingDate, SensorType sensorType, String units) {
        this.id = id;
        this.sensorName = sensorName;
        this.startingDate = startingDate;
        this.sensorType = sensorType;
        this.units = units;
        this.isActive = true;
    }



}
