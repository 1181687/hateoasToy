package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class DishWasher implements Device, Measurable {

    private String mName;
    private Room mLocation;
    private DishWasherSpecs mSpec;
    private List<Readings> mReadingsList;
    private boolean mIsActive;
    private LocalDateTime mDeactivationDate;

    public DishWasher(String name, Room location) {
        this.mName = name;
        this.mSpec = new DishWasherSpecs();
        this.mLocation = location;
        this.mLocation.addDevice(this);
        this.mIsActive = true;
        this.mReadingsList = new ArrayList<>();
        if (validateName(name)) {
            this.mName = name;
        }
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
    @Override
    public Room getLocation() {
        return this.mLocation;
    }

    /**
     * get method
     *
     * @return name of device
     */
    @Override
    public String getName() {
        return this.mName;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    @Override
    public String getType() {
        return mSpec.getTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mSpec.getEnergyConsumptionInADay();
    }

    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    @Override
    public boolean setName(String name) {
        if (this.mLocation.isDeviceNameExistant(name) || this.mName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.mName = name;
        return true;
    }

    public boolean validateName(String name) {
        if (this.mLocation.isDeviceNameExistant(name)) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        return true;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    @Override
    public boolean setLocation(Room location) {
        if (this.mLocation.equals(location)) {
            return false;
        }
        this.mLocation.getDeviceList().remove(this);
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
        attributes.append("2 - Device1 Specifications\n");
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
    public boolean setAttributesDevType(String attribute, Object value) {
        return this.mSpec.setAttributeValue(attribute,value);
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
     * Equals method to determine if two Device1 are equal.     *
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
        return this.mName.equalsIgnoreCase(listOne.getName());
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
    public double getSumOfTheReadings(List<Readings> readingsList) {
        double sum = 0;
        for (Readings readings : readingsList) {
            sum += readings.getValue();
        }
        return sum;
    }

    public List<Readings> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Readings> readingsList = new ArrayList<>();
        for (Readings readings : mReadingsList) {
            if (!startDate.isAfter(readings.getDateTime()) && !endDate.isBefore(readings.getDateTime())) {
                readingsList.add(readings);
            }
        }
        return readingsList;
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
        List<Readings> readingsList = getReadingsListInInterval(startDate, endDate);
        if (readingsList.size() > 1) {
            readingsList.remove(0);
            totalEnergyConsumption = getSumOfTheReadings(readingsList);
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
        Map<LocalDateTime, Double> hmap = new TreeMap<>();
        List<Readings> validReadingsList = getReadingsListInInterval(startDate, endDate);
        for (Readings readings : validReadingsList) {
            hmap.put(readings.getDateTime(), readings.getValue());
        }
        return hmap;
    }

    @Override
    public DeviceSpecs getSpecs() {
        return this.mSpec;
    }
}