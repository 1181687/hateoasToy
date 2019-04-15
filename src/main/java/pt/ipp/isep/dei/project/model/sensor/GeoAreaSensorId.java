package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GeoAreaSensorId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensorId;

    public GeoAreaSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    protected GeoAreaSensorId() {
        //empty
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
}
