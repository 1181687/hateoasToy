package pt.ipp.isep.dei.project.model;

public class Address {
    private String mZipCode;
    private Location mLocation;

    /**
     * constructor of Address that receives a zipCode and a location
     * @param mZipCode
     * @param location
     */
    public Address(String mZipCode, Location location) {
        this.mZipCode = mZipCode;
        this.mLocation = location;
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
     * They are equals if all atributtes (mZipCode and mLocation) are equal.
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
        String comparablemZipCode = mZipCode;
        Location comparablemLocation = mLocation;
        String comparableAddressZipCode = address.mZipCode;
        Location comparableAddressLocation = address.mLocation;
        return comparableAddressZipCode.equals(comparablemZipCode)
                && comparableAddressLocation.equals(comparablemLocation);
    }

    /**
     * Get the location on the address of the house.
     * @return a location
     */
    public Location getLocation() {
        return mLocation;
    }
}