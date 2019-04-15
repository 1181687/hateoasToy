package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.sensor.SensorId;

import java.time.LocalDateTime;

public class ReadingId {

    private SensorId sensorId;
    private LocalDateTime localDateTime;

    public ReadingId(SensorId sensorId, LocalDateTime localDateTime) {
        this.sensorId = sensorId;
        this.localDateTime = localDateTime;
    }

    public SensorId getSensorId() {
        return sensorId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
