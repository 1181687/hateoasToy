package pt.ipp.isep.dei.project.model;

public class Address {
    private String mZipCode;
    private Location mLocation;

    /**
     * constructor of Address that receives a zipCode and a location
     * @param mZipCode
     * @param mLocation
     */
    public Address(String mZipCode, Location mLocation) {
        this.mZipCode = mZipCode;
        this.mLocation = mLocation;
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
        return this.mZipCode.equals(address.mZipCode) && this.mLocation.equals(address.mLocation);

    }

    /**
     * Get the location on the address of the house.
     * @return a location
     */
    public Location getLocation() {
        return mLocation;
    }
}