package pt.ipp.isep.dei.project.model.sensor;

import javax.persistence.Embeddable;

@Embeddable
public class SensorType {

    private String sensorType;

    protected SensorType() {
    }

    /**
     * Constructor of SensorType
     *
     * @param tipoSensor Type of sensor
     */
    public SensorType(String tipoSensor) {
        this.sensorType = tipoSensor;
    }

    /**
     * get method
     *
     * @return sensorType of sensor
     */
    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
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
        return this.sensorType.equals(ts.sensorType);

    }
}
