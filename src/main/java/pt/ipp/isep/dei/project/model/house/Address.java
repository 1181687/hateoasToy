package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;

public class Address {
    private String zipCode;
    private Location location;
    private GeographicalArea insertedGeoArea;


    /**
     * constructor of Address that receives a zipCode and a location
     * @param zipCode
     * @param location
     * @param insertedGeoArea
     */
    public Address(String zipCode, Location location, GeographicalArea insertedGeoArea) {
        this.zipCode = zipCode;
        this.location = location;
        this.insertedGeoArea = insertedGeoArea;
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
        return address.zipCode.equals(this.zipCode)
                && address.location.equals(this.location)
                && address.insertedGeoArea.equals(this.insertedGeoArea);
    }

    /**
     * Get the Location on the Address.
     * @return a location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Get the GeographicalArea of the Address.
     *
     * @return
     */
    public GeographicalArea getInsertedGeoArea() {
        return insertedGeoArea;
    }

    /**
     * Set method for the inserted geo area.
     *
     * @param geoArea House area.
     */
    public void setInsertedGeoArea(GeographicalArea geoArea) {
        insertedGeoArea = geoArea;
    }
}