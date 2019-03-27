package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SensorType {

    @Id
    private String type;

    protected SensorType() {
    }

    /**
     * Constructor of SensorType
     *
     * @param tipoSensor Type of sensor
     */
    public SensorType(String tipoSensor) {
        this.type = tipoSensor;
    }

    /**
     * get method
     *
     * @return type of sensor
     */
    public String getType() {
        return type;
    }

    /**
     * method that creates the same hashcode to sensor types with the same attribute
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two sensor Types are equal. They are equals if all atributtes are equal.
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
        return this.type.equals(ts.type);

    }
}
