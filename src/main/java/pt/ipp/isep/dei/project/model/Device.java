package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.List;

public interface Device extends Measurable {


    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    double getNominalPower();

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
     * method that gets the Type
     *
     * @return String
     */
    String getType();

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    double getEnergyConsumptionInADay();


    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    boolean setName(String name);

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    boolean setLocation(Room location);

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    String getDevSpecsAttributesToString();

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    String getAttributesToString();

    /**
     * method that set the attributes of a device type.
     *
     * @param attribute
     * @param value
     * @return the position of an attribute and the value of it.
     */
    boolean setAttributesDevType(String attribute, Object value);

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
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    int getNumberOfSpecsAttributes();

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    String getNameToString();

    /**
     * Method that adds a readings to the device.
     *
     * @param readings Readings to be added.
     */
    void addReadingsToTheList(Readings readings);

    /**
     * Method that calculates the sum of the value in each Readings in a given Readings list.
     *
     * @param readingsList List with Readingss.
     * @return Double with the required sum.
     */
    double getSumOfTheReadings(List<Readings> readingsList);

    List<Readings> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * method that set the deactivate device, turning it to false and giving a date
     */
    void setDeactivateDevice();

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    boolean getIsActive();

    List<String> getSpecsList();

    Object getAttributeValue(String attributeName);

    String getSpecsToString();

    String getAttributeDataType(String attributeName);

    LocalDateTime getDeactivationDate();
}