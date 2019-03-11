package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Location;

public class Address {
    private String zipCode;
    private Location location;

    /**
     * constructor of Address that receives a zipCode and a location
     * @param zipCode
     * @param location
     */
    public Address(String zipCode, Location location) {
        this.zipCode = zipCode;
        this.location = location;
    }

    /**
     * method that creates the hashcode to address
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two Addresses are equal.
     * They are equals if all atributtes (zipCode and location) are equal.
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        String comparablemZipCode = zipCode;
        Location comparablemLocation = location;
        String comparableAddressZipCode = address.zipCode;
        Location comparableAddressLocation = address.location;
        return comparableAddressZipCode.equals(comparablemZipCode)
                && comparableAddressLocation.equals(comparablemLocation);
    }

    /**
     * Get the location on the address of the housegrid.
     * @return a location
     */
    public Location getLocation() {
        return location;
    }
}