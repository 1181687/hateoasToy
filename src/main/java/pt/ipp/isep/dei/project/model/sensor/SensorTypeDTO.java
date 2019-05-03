package pt.ipp.isep.dei.project.model.sensor;

import java.util.Objects;

public class SensorTypeDTO {
    private String sensorType;

    public SensorTypeDTO(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorType() {
        return this.sensorType;
    }

    /**
     * Equals method to determine if two SensorTypeDTO are equal.
     * They are equals if sensorType are equal.
     * Names are case insensitive.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorTypeDTO)) {
            return false;
        }
        SensorTypeDTO sensorTypeDTO = (SensorTypeDTO) obj;
        return this.getSensorType().equalsIgnoreCase(sensorTypeDTO.getSensorType());
    }

    /**
     * method that creates the same hashcode to SensorTypeDTO with the same type name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.sensorType);
    }
}
