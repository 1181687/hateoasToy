package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.house.RoomId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorTypeId)) {
            return false;
        }
        SensorTypeId typeId = (SensorTypeId) obj;
        return this.sensorTypeId.equalsIgnoreCase(typeId.getSensorTypeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sensorTypeId);
    }
}