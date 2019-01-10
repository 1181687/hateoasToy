package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;

public class ConfHouseLocationController {
    private House mHouse;
    private Address mAddress;

    public ConfHouseLocationController(House house) {
        this.mHouse = house;
    }

    /**
     * Get method
     *
     * @return mHouse
     */
    public House getmHouse() {
        return mHouse;
    }

    /**
     * Method that asks the Class House to define a new Address considering the following attributes
     * @param zipCode String ZipCode given by user
     * @param latitude attribute of Location. Double given by user
     * @param longitude attribute of Location. Double given by user
     * @param altitude attribute of Location. Double given by user
     */
    public void defineNewAddress(String zipCode, double latitude, double longitude, double altitude) {
        this.mAddress = mHouse.newAddresses(zipCode, latitude, longitude, altitude);
    }

    /**
     * Method that sets the Address to the House
     */
    public void setAddressToTheHouse() {
        mHouse.setmAddress(mAddress);
    }
}
