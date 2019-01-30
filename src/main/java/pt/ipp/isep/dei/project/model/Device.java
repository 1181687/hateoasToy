package pt.ipp.isep.dei.project.model;


import java.time.LocalDateTime;
import java.util.*;

public class Device implements Measurable {
    private String mName;
    private Room mLocation;
    private DeviceSpecs mSpec;
    private List<Readings> mReadingsList = new ArrayList<>();
    private boolean mIsActive;
    private LocalDateTime mDeactivationDate;

    public Device(String name, Room location, DeviceSpecs spec) {
        this.mName = name;
        this.mLocation = location;
        this.mSpec = spec;
        this.mLocation.addDevice(this);
        this.mIsActive = true;
    }

    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    @Override
    public double getNominalPower() {
        return mSpec.getNominalPower();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
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
     * method that gets the Type
     *
     * @return String
     */
    public String getType() {
        return mSpec.getTypeName();
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
        if (this.mLocation.isDeviceNameExistant(name) || this.mName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.mName = name;
        return true;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    public boolean setLocation(Room location) {
        if (this.mLocation.equals(location)) {
            return false;
        }
        this.mLocation.getDeviceList().removeDevice(this);
        this.mLocation = location;
        this.mLocation.addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    public String getDevSpecsAttributesToString() {
        return mSpec.getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mName + "\n");
        attributes.append("2 - Device Specifications\n");
        attributes.append("3 - Location: " + mLocation.getName() + "\n");
        return attributes.toString();
    }

    /**
     * method that set the attributes of a device type.
     *
     * @param attribute
     * @param value
     * @return the position of an attribute and the value of it.
     */
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

    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    public int getNumberOfSpecsAttributes() {
        return mSpec.getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + mName);
        nameLocation.append(", located in room: " + mLocation.getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a readings to the device.
     *
     * @param readings Readings to be added.
     */
    public void addReadingsToTheList(Readings readings) {
        mReadingsList.add(readings);
    }

    /**
     * Method that calculates the sum of the value in each Readings in a given Readings list.
     *
     * @param readingsList List with Readingss.
     * @return Double with the required sum.
     */
    public double getSumOfTheReadingss(List<Readings> readingsList) {
        double sum = 0;
        for (Readings readings : readingsList) {
            sum += readings.getValue();
        }
        return sum;
    }

    public List<Readings> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Readings> ReadingsList = new ArrayList<>();
        for (Readings Readings : mReadingsList) {
            if (!startDate.isAfter(Readings.getDateTime()) && !endDate.isBefore(Readings.getDateTime())) {
                ReadingsList.add(Readings);
            }
        }
        return ReadingsList;
    }

    /**
     * Method that calculates the total energy consumption of a device in a given interval.
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double with the required energy consumption.
     */
    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        List<Readings> ReadingsList = getReadingsListInInterval(startDate, endDate);
        if (ReadingsList.size() > 1) {
            ReadingsList.remove(0);
            totalEnergyConsumption = getSumOfTheReadingss(ReadingsList);
        }
        return totalEnergyConsumption;
    }

    /**
     * method that set the deactivate device, turning it to false and giving a date
     */
    public void setDeactivateDevice() {
        this.mIsActive = false;
        this.mDeactivationDate = LocalDateTime.now();
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    public boolean getIsActive() {
        return mIsActive;
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        HashMap<LocalDateTime, Double> hmap = new HashMap<>();
        List<Readings> validReadingsList = getReadingsListInInterval(startDate, endDate);
        for (Readings Readings : validReadingsList) {
            hmap.put(Readings.getDateTime(), Readings.getValue());
        }
        return hmap;
    }
}