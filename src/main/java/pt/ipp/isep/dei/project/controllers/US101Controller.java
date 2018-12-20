package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;

public class US101Controller {
    private House mHouse;

    public US101Controller(House mHouse) {
        this.mHouse = mHouse;
    }

    public Address defineNewAddress(String zipCode, double latitude, double longitude, double altitude) {
        return mHouse.newAddresses(zipCode, latitude, longitude, altitude);
    }

    public void addTheAddressToTheHouse(Address address) {
        mHouse.setmAddress(address);
    }
}
