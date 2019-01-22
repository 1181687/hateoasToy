package pt.ipp.isep.dei.project.model;

public class SensorType {
    private String mTipo;

    /**
     * Constructor of SensorType
     *
     * @param tipoSensor Type of sensor
     */
    public SensorType(String tipoSensor) {
        this.mTipo = tipoSensor;
    }

    /**
     * get method
     *
     * @return type of sensor
     */
    public String getType() {
        return mTipo;
    }

    /**
     * method that creates the same hashcode to Sensor types with the same attribute
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two Sensor Types are equal. They are equals if all atributtes are equal.
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
        return this.mTipo.equals(ts.mTipo);

    }
}
