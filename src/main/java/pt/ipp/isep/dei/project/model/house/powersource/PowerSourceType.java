package pt.ipp.isep.dei.project.model.house.powersource;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PowerSourceType {
    @EmbeddedId
    private PowerSourceTypeId typeOfPowerSource;

    /**
     * constructor that receives a power source type (String)
     *
     * @param typeOfPowerSource type of Power Source
     */
    public PowerSourceType(PowerSourceTypeId typeOfPowerSource) {
        this.typeOfPowerSource = typeOfPowerSource;
    }

    protected PowerSourceType() {
    }

    /**
     * Get Method
     * @return typeOfPowerSource
     */
    public String getTypeOfPowerSource() {
        return typeOfPowerSource.getPowerSourceTypeId();
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
        return this.typeOfPowerSource.getPowerSourceTypeId().equalsIgnoreCase(type.typeOfPowerSource.getPowerSourceTypeId());
    }
}
