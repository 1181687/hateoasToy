package pt.ipp.isep.dei.project.model.PowerSource;

import java.util.Objects;

import static java.util.Objects.isNull;

public class PowerSource {
    private String name;
    private PowerSourceType powerSourceType;

    /**
     * constructor that receives a power source name (String) and a PowerSourceType. Throws an exception if any of the parameters is invalid.
     * Invalid parameters if PowerSourceType is null or name is null or empty
     *
     * @param powerSourceName name of the Power Source (String)
     * @param powerSourceType type of the Power Source
     */
    public PowerSource(String powerSourceName, PowerSourceType powerSourceType) {
        validateName(powerSourceName);
        validatePowerSourceType(powerSourceType);
        this.powerSourceType = powerSourceType;
        this.name = powerSourceName;
    }

    /**
     * Method that validates the name of the Power Source. Throws an exception if name is null or empty
     *
     * @param name name of the Power Source
     */
    private static void validateName(String name) {
        if (isNull(name) || name.trim().length() == 0) {
            throw new NullPointerException("Please enter a valid name. Name should not be empty");
        }
    }

    /**
     * Method that validates the Type of Power Source. Throws an exception if it is null.
     *
     * @param powerSourceType type of Power Source
     */
    private static void validatePowerSourceType(PowerSourceType powerSourceType) {
        if (isNull(powerSourceType)) {
            throw new NullPointerException("Please select a valid power source type");
        }
    }

    /**
     * method that creates the same hashcode to PowerSources with the same attributes: name and Type.
     * @return the hashcode created
     */

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    /**
     * Equals method to determine if two PowerSources are equal.
     * They are equals if all attributes are equal.
     * @param obj receives an object
     * @return boolean
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

        return this.name.equalsIgnoreCase(type.name);
    }

    /**
     * get method
     * @return power source name
     */
    public String getName() {
        return name;
    }
}
