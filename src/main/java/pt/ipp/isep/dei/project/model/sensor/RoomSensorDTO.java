package pt.ipp.isep.dei.project.model.sensor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RoomSensorDTO {

    private String id;
    private String name;
    private LocalDate startingDate;
    private String typeId;
    private String units;
    private boolean isActive = true;
    private String RoomId;

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

    public String getSensorTypeId() {
        return typeId;
    }

    public void setSensorTypeId(String sensorType) {
        this.typeId = sensorType;
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
