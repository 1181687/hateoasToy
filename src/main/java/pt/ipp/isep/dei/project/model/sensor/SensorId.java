package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SensorId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensorId;

    public SensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    protected SensorId() {
        //empty
    }

    public String getSensorId() {
        return sensorId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorId)) {
            return false;
        }
        SensorId sensorId = (SensorId) obj;
        return sensorId.getSensorId().equalsIgnoreCase(this.sensorId);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
