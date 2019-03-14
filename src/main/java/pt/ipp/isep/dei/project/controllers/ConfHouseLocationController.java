package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalAreaList;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;

import java.util.Objects;

public class ConfHouseLocationController {
    private GeographicalAreaList geographicalAreaList;
    private House house;
    private Address address;
    private GeographicalArea geographicalArea;

    public ConfHouseLocationController(GeographicalAreaList geographicalAreaList, House house) {
        this.geographicalAreaList = geographicalAreaList;
        this.house = house;
    }

    /**
     * Get method
     *
     * @return housegrid
     */
    public House getHouse() {
        return house;
    }

    /**
     * Method that list the content of the list of geo areas.
     *
     * @param useCriterion
     * @return the content of the list.
     */
    public String getGeoAreaListToString(boolean useCriterion) {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (GeographicalArea geographicalArea : geographicalAreaList.getGeoAreaList()) {
            content.append(numberInTheList + " - ID: " + geographicalArea.getId());
            content.append(", Description: " + geographicalArea.getDescription());
            content.append(", Type: " + geographicalArea.getGeoAreaType().getStringOfTypeOfGeoArea());
            content.append(", Latitude: " + geographicalArea.getLocation().getLatitude());
            content.append(", Longitude: " + geographicalArea.getLocation().getLongitude());
            if (useCriterion && !geographicalAreaList.checkIfGeoAreaDoesntHaveAnInsertedArea(geographicalArea)) {
                content.append(", Inserted in: " + geographicalArea.getInsertedIn().getGeoAreaType().getStringOfTypeOfGeoArea());
                content.append(" " + geographicalArea.getInsertedIn().getDescription());
            }
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that checks if the Geographical Area List is empty.
     *
     * @return True or False.
     */
    public boolean isGeographicalAreaListEmpty() {
        return geographicalAreaList.getGeoAreaList().isEmpty();
    }

    /**
     * @return
     */
    public int getNumberOfGeoAreas() {
        return geographicalAreaList.getSize();
    }

    /**
     * Set Geographical Area.
     *
     * @param position Position of the Geographical Area.
     */
    public void setGeographicalArea(int position) {
        geographicalArea = geographicalAreaList.getGeographicalAreaInTheList(position);
    }

    /**
     * Method that checks if the House is already in the specified Geographical Area.
     *
     * @return True or False.
     */
    public boolean isHouseAlreadyInGeoArea() {
        Address houseAddress = house.getAddress();
        return !Objects.isNull(houseAddress) && houseAddress.getInsertedGeoArea().equals(geographicalArea);
    }

    /**
     * Method that asks the Class House to define a new Address considering the following attributes
     *
     * @param zipCode   String ZipCode given by user
     * @param latitude  attribute of Location. Double given by user
     * @param longitude attribute of Location. Double given by user
     * @param altitude  attribute of Location. Double given by user
     */
    public void defineNewAddress(String zipCode, double latitude, double longitude, double altitude) {
        this.address = house.newAddresses(zipCode, latitude, longitude, altitude, geographicalArea);
    }

    /**
     * Method that sets the Address to the House
     */
    public void setAddress() {
        house.setAddress(address);
    }
}
