package pt.ipp.isep.dei.project.model.sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RoomSensorDTO {

    private String id;
    private String name;
    private LocalDate startingDate;
    private String type;
    private String RoomId;
    private String units;
    private boolean isActive = true;

    public RoomSensorDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartingDate() {
        return startingDate.atStartOfDay();
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public String getSensorType() {
        return type;
    }

    public void setSensorType(String sensorType) {
        this.type = sensorType;
    }

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}