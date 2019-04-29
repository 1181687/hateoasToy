package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SensorTypeId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensorTypeId;

    public SensorTypeId(String sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }

    protected SensorTypeId() {
    }

    public String getSensorTypeId() {
        return sensorTypeId;
    }

    public void setSensorTypeId(String sensorTypeId) {
        this.sensorTypeId = sensorTypeId;
    }
}