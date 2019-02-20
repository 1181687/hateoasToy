package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class PowerSourceType {
    private String powerSourceType;

    /**
     * constructor that receives a power source type (String)
     *
     * @param powerSourceType type of Power Source
     */
    public PowerSourceType(String powerSourceType) {
        this.powerSourceType = powerSourceType;
    }

    /**
     * Get Method
     * @return powerSourceType
     */
    public String getPowerSourceType() {
        return powerSourceType;
    }

    /**
     * method that creates the same hashcode to PowerSourcesTypes with the same attribute.
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.powerSourceType);
    }

    /**
     * Equals method to determine if two Power Source Types are equal.
     * @param obj receives an object
     * @return boolean
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PowerSourceType)) {
            return false;
        }

        PowerSourceType type = (PowerSourceType) obj;
        return this.powerSourceType.equalsIgnoreCase(type.powerSourceType);

    }
}
