package pt.ipp.isep.dei.project.model.sensor;

import pt.ipp.isep.dei.project.model.ReadingDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomSensorDTO {

    private String id;
    private String name;
    private LocalDate startingDate;
    private String type;
    private String RoomId;
    private String units;
    private boolean isActive = true;
    private List<ReadingDTO> readingDTOs = new ArrayList<>();

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
        if (Objects.isNull(startingDate)){
            startingDate = LocalDate.now();
        }
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

    public List<ReadingDTO> getReadingDTOs() {
        return this.readingDTOs;
    }

    public void setReadingDTOs(List<ReadingDTO> readingDTOs) {
        this.readingDTOs = readingDTOs;
    }

    public boolean addReadingDTO(ReadingDTO readingDTO) {
        if (!this.readingDTOs.contains(readingDTO)) {
            this.readingDTOs.add(readingDTO);
            return true;
        }
        return false;
    }
}
