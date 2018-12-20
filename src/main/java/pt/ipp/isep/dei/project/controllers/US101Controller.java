package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Address;
import pt.ipp.isep.dei.project.model.House;

public class US101Controller {
    private House mHouse;
    private Address mAddress;

    public US101Controller(House mHouse) {
        this.mHouse = mHouse;
    }

    public void defineNewAddress(String zipCode, double latitude, double longitude, double altitude) {
        this.mAddress = mHouse.newAddresses(zipCode, latitude, longitude, altitude);
    }

    public void setAddressToTheHouse() {
        mHouse.setmAddress(mAddress);
    }
}
