package pt.ipp.isep.dei.project.model;

import java.util.Objects;

public class PowerSourceType {
    private String mPowerSourceType;

    /**
     * constructor that receives a power source type (String)
     *
     * @param mPowerSourceType type of Power Source
     */
    public PowerSourceType(String mPowerSourceType) {
        this.mPowerSourceType = mPowerSourceType;
    }

    /**
     * Get Method
     * @return mPowerSourceType
     */
    public String getPowerSourceType() {
        return mPowerSourceType;
    }

    /**
     * method that creates the same hashcode to PowerSourcesTypes with the same attribute.
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mPowerSourceType);
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
        return this.mPowerSourceType.equalsIgnoreCase(type.mPowerSourceType);

    }
}
