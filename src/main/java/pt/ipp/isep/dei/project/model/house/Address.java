package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.roles.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class Address implements ValueObject {
    private String completeAddress;
    @Transient
    private Location location;
    @Transient
    private GeographicalArea insertedGeoArea;


    /**
     * constructor of Address that receives a completeAddress and a location
     * @param completeAddress
     * @param location
     * @param insertedGeoArea
     */
    public Address(String completeAddress, Location location, GeographicalArea insertedGeoArea) {
        this.completeAddress = completeAddress;
        this.location = location;
        this.insertedGeoArea = insertedGeoArea;
    }

    protected Address() {
        //empty
    }

    /**
     * method that creates the hashcode to completeAddress
     */
    @Override
    public int hashCode() {
        return 1;
    }

    /**
     * Equals method to determine if two Addresses are equal.
     * They are equals if all atributtes (completeAddress and location) are equal.
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
        return address.completeAddress.equals(this.completeAddress)
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

    /**
     * Get method.
     *
     * @return
     */
    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}