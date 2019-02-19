package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class Fridge implements Device, Measurable {
    private String mFridgeName;
    private Room mFridgeLocation;
    private FridgeSpecs mFridgeSpec;
    private List<Readings> mFridgeReadings;
    private boolean mIsFridgeActive;
    private LocalDateTime mFridgeDeactivationDate;

    public Fridge(String name, Room location) {
        this.mFridgeName = name;
        this.mFridgeLocation = location;
        this.mFridgeLocation.addDevice(this);
        this.mFridgeSpec = new FridgeSpecs();
        this.mIsFridgeActive = true;
        this.mFridgeReadings = new ArrayList<>();
    }


    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    @Override
    public double getNominalPower() {
        return this.mFridgeSpec.getNominalPower();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    public Room getLocation() {
        return this.mFridgeLocation;
    }

    /**
     * get method
     *
     * @return name of device
     */
    public String getName() {
        return this.mFridgeName;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    public String getType() {
        return this.mFridgeSpec.getTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    public double getEnergyConsumptionInADay() {
        return this.mFridgeSpec.getEnergyConsumptionInADay();
    }


    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    public boolean setName(String name) {
        if (this.mFridgeLocation.isDeviceNameExistant(name) || this.mFridgeName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.mFridgeName = name;
        return true;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    public boolean setLocation(Room location) {
        if (this.mFridgeLocation.equals(location)) {
            return false;
        }
        this.mFridgeLocation.getDeviceList().remove(this);
        this.mFridgeLocation = location;
        this.mFridgeLocation.addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    public String getDevSpecsAttributesToString() {
        return this.mFridgeSpec.getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + mFridgeName + "\n");
        attributes.append("2 - Device Specifications\n");
        attributes.append("3 - Location: " + mFridgeLocation.getName() + "\n");
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
        return this.mFridgeSpec.setAttributeValue(attribute, value);
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.mFridgeName);
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
        return this.mFridgeName.equalsIgnoreCase(listOne.getName());
    }

    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    public int getNumberOfSpecsAttributes() {
        return this.mFridgeSpec.getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + mFridgeName);
        nameLocation.append(", located in room: " + mFridgeLocation.getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a readings to the device.
     *
     * @param readings Readings to be added.
     */
    public void addReadingsToTheList(Readings readings) {
        this.mFridgeReadings.add(readings);
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
        for (Readings readings : this.mFridgeReadings) {
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
        this.mIsFridgeActive = false;
        this.mFridgeDeactivationDate = LocalDateTime.now();
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    public boolean getIsActive() {
        return mIsFridgeActive;
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
        return mFridgeSpec.getSpecsList();
    }

    /**
     * TODO
     *
     * @param attributeName
     * @return
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        return mFridgeSpec.getAttributeValue(attributeName);
    }

    public String getSpecsToString() {
        return this.mFridgeSpec.getAttributesToString();
    }

    public String getAttributeDataType(String attributeName) {
        return mFridgeSpec.getAttributeDataType(attributeName);
    }

    @Override
    public boolean isProgrammable() {
        return false;
    }
}
