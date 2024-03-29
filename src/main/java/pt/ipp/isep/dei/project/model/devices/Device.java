package pt.ipp.isep.dei.project.model.devices;

import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.house.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public interface Device extends Measurable {


    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    Room getLocation();

    /**
     * get method
     *
     * @return name of device
     */
    String getName();

    /**
     * get method
     *
     * @return device Specifications
     */
    DeviceSpecs getSpecs();

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    boolean setLocation(Room location);

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    int hashCode();

    /**
     * Equals method to determine if two Device1 are equal.     *
     *
     * @param obj receives an object
     * @return boolean true if are equal and false if are not.
     */
    boolean equals(Object obj);

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    boolean getIsActive();

    LocalDateTime getDeactivationDate();

    boolean setDeactivateDevice();

    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    boolean setName(String name);


    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    default String getDevSpecsAttributesToString() {
        return getSpecs().getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    default String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + getName() + "\n");
        attributes.append("2 - Device Specifications \n");
        attributes.append("3 - Location: " + getLocation().getRoomId() + "\n");
        return attributes.toString();
    }


    /**
     * method that set the attributes of a device type.
     *
     * @param attribute
     * @param value
     * @return the position of an attribute and the value of it.
     */
    default boolean setAttributesDevType(String attribute, Object value) {
        return this.getSpecs().setAttributeValue(attribute, value);
    }


    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    default int getNumberOfSpecsAttributes() {
        return getSpecs().getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    default String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + getName());
        nameLocation.append(", located in room: " + getLocation().getRoomId() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a reading to the device.
     *
     * @param reading Reading to be added.
     */
    default void addReadingsToTheList(Reading reading) {
        this.getReadings().add(reading);
    }

    /**
     * Method that calculates the sum of the value in each Reading in a given Reading list.
     *
     * @param readingList List with Readingss.
     * @return Double with the required sum.
     */
    default double getSumOfTheReadings(List<Reading> readingList) {
        double sum = 0;
        for (Reading reading : readingList) {
            sum += reading.getValue();
        }
        return sum;
    }


    default String getDateDeactivateDeviceToString() {
        return this.getDeactivationDate().toLocalDate().toString() + " " + this.getDeactivationDate().toLocalTime().toString();
    }

    /**
     * Method that calculates the total energy consumption of a device in a given interval.
     * This method has in count all the fully contained readingList, i.e., if there's just one readingList in the interval, it
     * is not counted.
     *
     * @param startDate Start date.
     * @param endDate   End date.
     * @return Double with the required energy consumption.
     */
    default double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        double totalEnergyConsumption = 0;
        List<Reading> validReadings = getReadingsListInInterval(startDate, endDate);
        if (!(validReadings.isEmpty())) {
            totalEnergyConsumption = getSumOfTheReadings(validReadings);
        }
        return totalEnergyConsumption;
    }

    /**
     * method that gets the Device Type
     *
     * @return String
     */
    default String getType() {
        return getSpecs().getTypeName();
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return
     * consumption of the device in a given day.
     */
    default double getEnergyConsumptionInADay() {
        return getSpecs().getEnergyConsumptionInADay();
    }

    default Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> map = new TreeMap<>();
        List<Reading> validReadingList = getReadingsListInInterval(startDate, endDate);
        for (Reading reading : validReadingList) {
            map.put(reading.getDateTime(), reading.getValue());
        }
        return map;
    }

    default Object getAttributeValue(String attributeName) {
        return getSpecs().getAttributeValue(attributeName);
    }

    default List<String> getSpecsList() {
        return getSpecs().getSpecsList();
    }

    default String getSpecsToString() {
        return getSpecs().getAttributesToString();
    }

    default String getAttributeDataType(String attributeName) {
        return getSpecs().getAttributeDataType(attributeName);
    }

    default double getNominalPower() {
        return getSpecs().getNominalPower();
    }


}