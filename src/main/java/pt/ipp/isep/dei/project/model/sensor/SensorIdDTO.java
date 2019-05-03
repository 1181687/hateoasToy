package pt.ipp.isep.dei.project.model.sensor;

import java.util.Objects;

public class SensorIdDTO {
    private String id;

    public SensorIdDTO() {
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
        if (!(obj instanceof SensorIdDTO)) {
            return false;
        }
        SensorIdDTO geoAreaId = (SensorIdDTO) obj;
        return this.id.equals(geoAreaId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
