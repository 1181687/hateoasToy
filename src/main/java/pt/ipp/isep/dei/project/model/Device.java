package pt.ipp.isep.dei.project.model;


public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;

    public Device(String mName, Room mLocation, DeviceSpecs mSpec) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mLocation.addDevice(this);
        this.mSpec = mSpec;
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
    public String getmName() {
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
     * Method that allows the possibility of setting the cold-water temperature and the volume of water to heat in the
     * class Electric Water Heater.
     *
     * @param coldWaterTemp       Sets the current temperature of the water that is going to be heated.
     * @param volumeOfWaterToHeat Sets the amount of water to be heated.
     */
    public void setColdWaterTempAndVolumeOfWaterToHeat(double coldWaterTemp, double volumeOfWaterToHeat) {
        ((ElectricWaterHeater) mSpec).setmColdWaterTemperature(coldWaterTemp);
        ((ElectricWaterHeater) mSpec).setmVolumeOfWaterToHeat(volumeOfWaterToHeat);
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        return mSpec.getEnergyConsumptionInADay();
    }

    public boolean setmName(String mName) {
        this.mName = mName;
        return true;
    }

    public boolean setmLocation(Room location) {
        if (this.mLocation.equals(location)) {
            return false;
        }
        this.mLocation.getmDeviceList().removeDevice(this);
        this.mLocation = location;
        this.mLocation.addDevice(this);
        return true;
    }


    public String getSpecsAttributesToString() {
        return mSpec.getAttributesToString();
    }

    public String getAttributesToString() {
        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mName + "\n");
        attributes.append("2 - Device Specifications\n");
        attributes.append("3 - Location: " + mLocation + "\n");
        String deviceAttributes = attributes.toString();
        return  deviceAttributes;
    }

    public boolean setAttributes (int attribute, double value) {
        return this.mSpec.setAttribute(attribute, value);
    }

    public int getNumberOfSpecsAttributes(){
        return mSpec.getNumberOfAttributes();
    }
}
