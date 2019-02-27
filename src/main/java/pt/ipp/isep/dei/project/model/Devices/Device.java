package pt.ipp.isep.dei.project.model.Devices;

import pt.ipp.isep.dei.project.model.Measurable;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Device extends Measurable {


    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    //double getNominalPower();

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
    DeviceSpecs getSpecs ();

    /**
     * Method that gets the boolean attribute from each device.
     * @return boolean
     */
    boolean isActive();


    /**
     * Method that gets the energy consumption in a day.
     *
     * @return
     * consumption of the device in a given day.
     */
    default double getEnergyConsumptionInADay(){
            return getSpecs().getEnergyConsumptionInADay();
    }


    /**
     * method that set the given name only if the name don't exists in DeviceList
     * and if it is different than the name that the Device1 has.
     *
     * @param name String given name
     * @return true if sets false if don't
     */
    default boolean setName(String name) {
        String oldName = getName();
        if (this.getLocation().isDeviceNameExistant(name) || oldName == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        getName().equals(name);
        return true;
    }

    /**
     * method that set the location (room) of a added device.
     *
     * @param location
     * @return false if the location is equals to another device. True if not.
     */
    default boolean setLocation(Room location){
        if (this.getLocation().equals(location)) {
            return false;
        }
        this.getLocation().getDeviceList().remove(this);
        this.getLocation().equals(location);
        this.getLocation().addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    default String getDevSpecsAttributesToString(){
        return getSpecs().getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    default String getAttributesToString(){

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + getName() + "\n");
        attributes.append("2 - Device Specifications \n");
        attributes.append("3 - Location: " + getLocation().getName() + "\n");
        return attributes.toString();
    }


    /**
     * method that set the attributes of a device type.
     *
     * @param attribute
     * @param value
     * @return the position of an attribute and the value of it.
     */
    default boolean setAttributesDevType(String attribute, Object value){
        return this.getSpecs().setAttributeValue(attribute, value);
    }

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
    default int getNumberOfSpecsAttributes()  {
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
        nameLocation.append(", located in room: " + getLocation().getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a reading to the device.
     *
     * @param reading Reading to be added.
     */
    default void addReadingsToTheList(Reading reading)  {
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

    /**
     * TODO - GABIX
     * ???????? - está bem ?????
     * method that set the deactivate device, turning it to false and giving a date
     */
    default boolean setDeactivateDevice(){
        boolean isActive = this.getIsActive();
        if (isActive) {
            isActive = false;
            this.getDeactivationDate().isEqual(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
            return true;
        }
        return false;
    }

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

    boolean isProgrammable();

    Programmable asProgrammable();

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
        List<Reading> readings = getReadingsListInInterval(startDate, endDate);
        if (!(readings.isEmpty())) {
            readings.remove(0);
            totalEnergyConsumption = getSumOfTheReadings(readings);
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





}