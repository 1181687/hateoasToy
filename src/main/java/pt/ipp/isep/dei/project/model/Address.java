package pt.ipp.isep.dei.project.model;

public class Address {
    private String mZipCode;
    private Location mLocation;

    public Address(String mZipCode, Location mLocation) {
        this.mZipCode = mZipCode;
        this.mLocation = mLocation;
    }

    public void setmZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public void setmLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

}