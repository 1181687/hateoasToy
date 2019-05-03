package pt.ipp.isep.dei.project.model.house;

import java.util.Objects;

public class RoomIdDTO {
    private String id;

    public RoomIdDTO() {
        //intentionally empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoomIdDTO)) {
            return false;
        }
        RoomIdDTO roomIdDTO = (RoomIdDTO) obj;
        return this.id.equals(roomIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
