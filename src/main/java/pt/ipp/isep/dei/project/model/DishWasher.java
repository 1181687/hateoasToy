package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class DishWasher implements Device, Measurable {

    private String mDWName;
    private Room mDWLocation;
    private DishWasherSpecs mDWSpec;
    private List<Readings> mDWReadingsList;
    private boolean mIsDWActive;
    private LocalDateTime mDWDeactivationDate;

    public DishWasher(String name, Room location) {
        this.mDWName = name;
        this.mDWSpec = new DishWasherSpecs();
        this.mDWLocation = location;
        this.mDWLocation.addDevice(this);
        this.mIsDWActive = true;
        this.mDWReadingsList = new ArrayList<>();
    }

    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    @Override
    public double getNominalPower() {
        return mDWSpec.getNominalPower();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return this.mDWLocation;
    }

    /**
     * get method
     *
     * @return name of device
     */
    @Override
    public String getName() {
        return this.mDWName;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    @Override
    public String getType() {
        return mDWSpec.getTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return mDWSpec.getEnergyConsumptionInADay();
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
        if (this.mDWLocation.isDeviceNameExistant(name) || this.mDWName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.mDWName = name;
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
        if (this.mDWLocation.equals(location)) {
            return false;
        }
        this.mDWLocation.getDeviceList().remove(this);
        this.mDWLocation = location;
        this.mDWLocation.addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    public String getDevSpecsAttributesToString() {
        return mDWSpec.getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mDWName + "\n");
        attributes.append("2 - Device Specifications \n");
        attributes.append("3 - Location: " + mDWLocation.getName() + "\n");
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
        return this.mDWSpec.setAttributeValue(attribute, value);
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mDWName);
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
        return this.mDWName.equalsIgnoreCase(listOne.getName());
    }

    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    public int getNumberOfSpecsAttributes() {
        return mDWSpec.getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + mDWName);
        nameLocation.append(", located in room: " + mDWLocation.getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a readings to the device.
     *
     * @param readings Readings to be added.
     */
    public void addReadingsToTheList(Readings readings) {
        mDWReadingsList.add(readings);
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
        for (Readings readings : mDWReadingsList) {
            if (!startDate.isAfter(readings.getDateTime()) && !endDate.isBefore(readings.getDateTime())) {
                readingsList.add(readings);
            }
        }
        return readingsList;
    }

    /**
     * Method that calculates the total energy consumption of a device in a given interval.
     * This method has in count all the fully contained readings, i.e., if there's just one reading in the interval, it
     * is not counted.
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double with the required energy consumption.
     */
    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        List<Readings> readingsList = getReadingsListInInterval(startDate, endDate);
        if (!(readingsList.isEmpty())) {
            readingsList.remove(0);
            totalEnergyConsumption = getSumOfTheReadings(readingsList);
        }
        return totalEnergyConsumption;
    }

    /**
     * method that set the deactivate device, turning it to false and giving a date
     */
    public void setDeactivateDevice() {
        this.mIsDWActive = false;
        this.mDWDeactivationDate = LocalDateTime.now();
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    public boolean getIsActive() {
        return mIsDWActive;
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
        return mDWSpec.getSpecsList();
    }

    /**
     * TODO
     *
     * @param attributeName
     * @return
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        return mDWSpec.getAttributeValue(attributeName);
    }

    @Override
    public String getSpecsToString() {
        return this.mDWSpec.getAttributesToString();
    }

    public String getAttributeType(String attributeName) {
        return mDWSpec.getAttributeType(attributeName);
    }
}