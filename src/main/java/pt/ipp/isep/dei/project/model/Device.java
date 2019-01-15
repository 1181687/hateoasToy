package pt.ipp.isep.dei.project.model;


import java.util.List;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;

    public Device(String mName, Room mLocation, DeviceSpecs mSpec) {
        this.mName = mName;
        this.mLocation = mLocation;
        this.mSpec = mSpec;
    }

    @Override
    public double getNominalPower() {
        return mSpec.getmNominalPower();
    }

    public Room getLocation() {
        return this.mLocation;
    }

    public List<String> getTypeNames() {

        List<String> listOfTypeNames = null;

        DeviceSpecs fridge = new Fridge();
        listOfTypeNames.add(fridge.getmTypeName());

        DeviceSpecs lamp = new Lamp();
        listOfTypeNames.add(lamp.getmTypeName());

        DeviceSpecs dishWasher = new DishWasher();
        listOfTypeNames.add(dishWasher.getmTypeName());

        DeviceSpecs washingMachine = new WashingMachine();
        listOfTypeNames.add(washingMachine.getmTypeName());

        DeviceSpecs electricWaterHeater = new ElectricWaterHeater();
        listOfTypeNames.add(electricWaterHeater.getmTypeName());

        return listOfTypeNames;
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
        if (this.mName == mName) {
            return true;
        }
        return false;
    }

    public boolean setmLocation(Room mLocation) {
        if (this.mLocation == mLocation) {
            return true;
        }
        return false;
    }

    public String getSpecsAttributesToString() {
        return mSpec.getAttributesToString();
    }

    /* public boolean setDeviceAttribute(int attribute, String name, Room room) {
        switch (attribute) {
            case 1:
                return setmName(name);
            case 2:
                return setDeviceAttribute(attribute, name, room);
            case 3:
                return setmLocation(room);
        }
        System.out.println("Please select a valid number.");
        return false;
    } */

    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mName + "\n");
        attributes.append("2 - Device Specifications\n");
        attributes.append("3 - Location: " + mLocation + "\n");
        String deviceAttributes = attributes.toString();
        return  deviceAttributes;
    }

}
