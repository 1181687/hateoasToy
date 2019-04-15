package pt.ipp.isep.dei.project.model.house.powersource;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class PowerSource {

    @EmbeddedId
    private PowerSourceId powerSourceId;

    @Embedded
    private PowerSourceTypeId powerSourceTypeId;

    /**
     * Protected constructor used by Spring.
     */
    protected PowerSource() {
        // empty
    }

    /**
     * Constructor.
     *
     * @param id Id to be used.
     * @param typeId Type Id to be used.
     */
    public PowerSource(PowerSourceId id, PowerSourceTypeId typeId) {
        this.powerSourceId = id;
        this.powerSourceTypeId = typeId;
    }

    /**
     * Method that validates the name of the Power Source. Throws an exception if name is null or empty
     *
     * @param name name of the Power Source
     */
    /*
    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new NullPointerException("Please enter a valid name. Name should not be empty");
        }
    }
    */

    /**
     * Method that validates the Type of Power Source. Throws an exception if it is null.
     *
     * @param powerSourceType type of Power Source
     */
    /*
    private static void validatePowerSourceType(PowerSourceType powerSourceType) {
        if (isNull(powerSourceType)) {
            throw new NullPointerException("Please select a valid power source type");
        }
    }
    */

    /**
     * Override method for the hash code.
     *
     * @return Hash code created.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.powerSourceId);
    }

    /**
     * Override method for the equals method.
     * Two Power sources are equal if they share the same Id.
     *
     * @param obj Object to be analysed.
     * @return True or false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PowerSource)) {
            return false;
        }

        PowerSource type = (PowerSource) obj;

        return this.powerSourceId.equals(type.getId());
    }

    /**
     * Get method.
     * @return PowerSourceId.
     */
    public PowerSourceId getId() {
        return powerSourceId;
    }

    /**
     * Get method.
     *
     * @return PowerSourceTypeId.
     */
    public PowerSourceTypeId getPowerSourceTypeId() {
        return powerSourceTypeId;
    }
}
