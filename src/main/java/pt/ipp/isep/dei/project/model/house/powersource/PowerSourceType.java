package pt.ipp.isep.dei.project.model.house.powersource;

import java.util.Objects;

public class PowerSourceType {


    private Long id;


    private String typeOfPowerSource;

    /**
     * constructor that receives a power source type (String)
     *
     * @param typeOfPowerSource type of Power Source
     */
    public PowerSourceType(String typeOfPowerSource) {
        this.typeOfPowerSource = typeOfPowerSource;
    }

    /**
     * Get Method
     * @return typeOfPowerSource
     */
    public String getTypeOfPowerSource() {
        return typeOfPowerSource;
    }

    /**
     * method that creates the same hashcode to PowerSourcesTypes with the same attribute.
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.typeOfPowerSource);
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
        return this.typeOfPowerSource.equalsIgnoreCase(type.typeOfPowerSource);

    }
}
