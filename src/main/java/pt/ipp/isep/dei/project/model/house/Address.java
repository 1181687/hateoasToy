package pt.ipp.isep.dei.project.model.house;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.roles.ValueObject;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Address implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ADDRESS")
    private String completeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude",
                    column = @Column(name = "HOUSE_LATITUDE")),
            @AttributeOverride(name = "longitude",
                    column = @Column(name = "HOUSE_LONGITUDE")),
            @AttributeOverride(name = "elevation",
                    column = @Column(name = "HOUSE_ELEVATION"))
    })
    private Location location;
    @Embedded
    private GeoAreaId houseAreaId;

    @Autowired
    @Transient
    private GeographicalAreaService service;


    /**
     * constructor of Address that receives a completeAddress and a location
     *
     * @param completeAddress
     * @param location
     * @param insertedGeoArea
     */
    public Address(String completeAddress, Location location, GeographicalArea insertedGeoArea) {
        this.completeAddress = completeAddress;
        this.location = location;
        if (Objects.nonNull(insertedGeoArea)) {
            this.houseAreaId = insertedGeoArea.getId();
        }
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
     *
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
                && address.houseAreaId.equals(this.houseAreaId);
    }

    /**
     * Get the Location on the Address.
     *
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
        return service.getGeoAreaById(houseAreaId.getId());
    }

    /**
     * Set method for the inserted geo area.
     *
     * @param geoArea House area.
     */
    public void setInsertedGeoArea(GeographicalArea geoArea) {
        houseAreaId = geoArea.getId();
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