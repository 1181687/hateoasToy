package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.House;

public class ConfHouseLocationController {
    private House house;
    private Address address;

    public ConfHouseLocationController(House house) {
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
     * Method that asks the Class House to define a new Address considering the following attributes
     * @param zipCode String ZipCode given by user
     * @param latitude attribute of Location. Double given by user
     * @param longitude attribute of Location. Double given by user
     * @param altitude attribute of Location. Double given by user
     */
    public void defineNewAddress(String zipCode, double latitude, double longitude, double altitude) {
        this.address = house.newAddresses(zipCode, latitude, longitude, altitude);
    }

    /**
     * Method that sets the Address to the House
     */
    public void setAddress() {
        house.setAddress(address);
    }
}
