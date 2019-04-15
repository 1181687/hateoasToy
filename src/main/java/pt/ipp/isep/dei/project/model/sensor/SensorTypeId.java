package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;

@Embeddable
public class SensorTypeId {
    private String sensorTypeId;

    public SensorTypeId(String sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    public String getSensorTypeId() {
        return sensorTypeId;
    }
}