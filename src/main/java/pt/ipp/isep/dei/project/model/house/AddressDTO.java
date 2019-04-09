package pt.ipp.isep.dei.project.model.house;

import pt.ipp.isep.dei.project.model.LocationDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaDTO;

public class AddressDTO {

    private String completeAddress;
    private LocationDTO location;
    private GeographicalAreaDTO insertedGeoArea;


    /**
     * constructor
     */
    public AddressDTO() {
        //Intentionally empty
    }


    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public GeographicalAreaDTO getInsertedGeoArea() {
        return insertedGeoArea;
    }

    public void setInsertedGeoArea(GeographicalAreaDTO insertedGeoArea) {
        this.insertedGeoArea = insertedGeoArea;
    }
}
