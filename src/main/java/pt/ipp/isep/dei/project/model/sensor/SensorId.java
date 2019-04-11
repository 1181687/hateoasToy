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
}
