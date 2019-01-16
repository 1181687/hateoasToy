package pt.ipp.isep.dei.project.model;


import java.util.Objects;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;

    public Device(String name, Room location, DeviceSpecs spec) {
        this.mName = name;
        this.mLocation = location;
        this.mSpec = spec;
    }

    @Override
    public double getNominalPower() {
        return mSpec.getmNominalPower();
    }

    public Room getLocation() {
        return this.mLocation;
    }

    /**
     * get method
     *
     * @return name of device
     */
    public String getName() {
        return this.mName;
    }

    /**
     * method that gets the DeviceSpecs
     *
     * @return DeviceSpecs
     */
    public DeviceSpecs getDeviceSpecs() {
        return this.mSpec;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    public String getType() {
        return mSpec.getmTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        return mSpec.getEnergyConsumptionInADay();
    }


    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    public boolean setName(String name) {
        if (this.mLocation.checkIfNameAlreadyExists(name)) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        if (this.mName == name) {
            return false;
        }
        this.mName = name;
        return true;
    }

    public boolean setmLocation(Room mLocation) {
        this.mLocation = mLocation;
        return true;
    }

    /**
     * Method that returns the content of the non-optional attributes of the each .
     *
     * @return String with the non-optional attributes.
     */
    public String getSpecsAttributesToString() {
        return mSpec.getAttributesToString();
    }

    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mName + "\n");
        attributes.append("2 - Device Specifications\n");
        attributes.append("3 - Location: " + mLocation.getmName() + "\n");
        String deviceAttributes = attributes.toString();
        return  deviceAttributes;
    }

    public boolean setAttributesDevType(int attribute, double value) {
        return this.mSpec.setAttribute(attribute, value);
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mName);
    }

    /**
     * Equals method to determine if two Device are equal.     *
     *
     * @param obj receives an object
     * @return boolean true if are equal and false if are not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device listOne = (Device) obj;
        return this.mName.equalsIgnoreCase(listOne.mName);
    }

    public int getNumberOfSpecsAttributes(){
        return mSpec.getNumberOfAttributes();
    }
}
