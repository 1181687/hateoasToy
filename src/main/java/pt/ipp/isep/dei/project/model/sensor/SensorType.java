package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class SensorType {
    @EmbeddedId
    private SensorTypeId sensorType;

    protected SensorType() {
    }

    /**
     * Constructor of SensorType
     *
     * @param sensorTypeId
     */
    public SensorType(SensorTypeId sensorTypeId) {
        this.sensorType = sensorTypeId;
    }

    /**
     * get method
     *
     * @return sensorType of sensor
     */
    public SensorTypeId getSensorType() {
        return sensorType;
    }

    /**
     * method that creates the same hashcode to sensor types with the same attribute
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two sensor Types are equal. They are equals if all atributtes are equal.
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SensorType)) {
            return false;
        }
        SensorType ts = (SensorType) obj;
        return this.sensorType.equals(ts.sensorType);

    }
}