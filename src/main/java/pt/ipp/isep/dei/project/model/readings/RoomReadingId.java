package pt.ipp.isep.dei.project.model.readings;
import pt.ipp.isep.dei.project.model.sensor.RoomSensorId;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class RoomReadingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private RoomSensorId roomSensorId;
    private LocalDateTime localDateTime;

    public RoomReadingId(RoomSensorId roomSensorId, LocalDateTime localDateTime) {
        this.roomSensorId = roomSensorId;
        this.localDateTime = localDateTime;
    }

    protected RoomReadingId() {
        //empty
    }

    public RoomSensorId getRoomSensorId() {
        return roomSensorId;
    }

    public void setRoomSensorId(RoomSensorId roomSensorId) {
        this.roomSensorId = roomSensorId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}