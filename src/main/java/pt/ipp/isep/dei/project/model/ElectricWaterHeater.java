package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class ElectricWaterHeater implements Device, Measurable {
    private String mEWHName;
    private Room mEWHLocation;
    private ElectricWaterHeaterSpecs mEWHSpec;
    private List<Readings> mEWHReadings;
    private boolean mIsEWHActive;
    private LocalDateTime mEWHDeactivationDate;

    public ElectricWaterHeater(String name, Room location) {
        this.mEWHName = name;
        this.mEWHLocation = location;
        this.mEWHLocation.addDevice(this);
        this.mEWHSpec = new ElectricWaterHeaterSpecs();
        this.mIsEWHActive = true;
        this.mEWHReadings = new ArrayList<>();

    }

    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    @Override
    public double getNominalPower() {
        return this.mEWHSpec.getNominalPower();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    public Room getLocation() {
        return this.mEWHLocation;
    }

    /**
     * get method
     *
     * @return name of device
     */
    public String getName() {
        return this.mEWHName;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    public String getType() {
        return this.mEWHSpec.getTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        return this.mEWHSpec.getEnergyConsumptionInADay();
    }


    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    public boolean setName(String name) {
        if (this.mEWHLocation.isDeviceNameExistant(name) || this.mEWHName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.mEWHName = name;
        return true;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    public boolean setLocation(Room location) {
        if (this.mEWHLocation.equals(location)) {
            return false;
        }
        this.mEWHLocation.getDeviceList().remove(this);
        this.mEWHLocation = location;
        this.mEWHLocation.addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    public String getDevSpecsAttributesToString() {
        return this.mEWHSpec.getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mEWHName + "\n");
        attributes.append("2 - Device1 Specifications\n");
        attributes.append("3 - Location: " + mEWHLocation.getName() + "\n");
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
        return this.mEWHSpec.setAttributeValue(attribute,value);
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mEWHName);
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
        return this.mEWHName.equalsIgnoreCase(listOne.getName());
    }

    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    public int getNumberOfSpecsAttributes() {
        return this.mEWHSpec.getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + mEWHName);
        nameLocation.append(", located in room: " + mEWHLocation.getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a readings to the device.
     *
     * @param readings Readings to be added.
     */
    public void addReadingsToTheList(Readings readings) {
        this.mEWHReadings.add(readings);
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

    /**
     * TODO
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Readings> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Readings> readingsList = new ArrayList<>();
        for (Readings readings : this.mEWHReadings) {
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
        this.mIsEWHActive = false;
        this.mEWHDeactivationDate = LocalDateTime.now();
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    public boolean getIsActive() {
        return mIsEWHActive;
    }

    /**
     * TODO
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> hmap = new TreeMap<>();
        List<Readings> validReadingsList = getReadingsListInInterval(startDate, endDate);
        for (Readings readings : validReadingsList) {
            hmap.put(readings.getDateTime(), readings.getValue());
        }
        return hmap;
    }

    /**
     * TODO
     * @return
     */
    @Override
    public List<String> getSpecsList() {
        return mEWHSpec.getSpecsList();
    }

    /**
     * TODO
     *
     * @param attributeName
     * @return
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        return mEWHSpec.getAttributeValue(attributeName);
    }

    @Override
    public String getSpecsToString() {
        return this.mEWHSpec.getAttributesToString();
    }
}
