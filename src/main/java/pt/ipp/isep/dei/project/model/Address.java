package pt.ipp.isep.dei.project.model;

public class Address {
    private String mZipCode;
    private Location mLocation;

    public Address(String mZipCode, Location mLocation) {
        this.mZipCode = mZipCode;
        this.mLocation = mLocation;
    }

    @Override
    public int hashCode() {
        return 1;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Address)) {
            return false;
        }
        Address address = (Address) obj;
        return this.mZipCode.equals(address.mZipCode) && this.mLocation.equals(address.mLocation);

    }

}