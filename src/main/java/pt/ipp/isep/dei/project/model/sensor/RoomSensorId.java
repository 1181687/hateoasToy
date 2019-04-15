package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoomSensorId implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sensorId;

    public RoomSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    protected RoomSensorId() {
        //empty
    }

    public String getSensorId() {
        return sensorId;
    }
}
