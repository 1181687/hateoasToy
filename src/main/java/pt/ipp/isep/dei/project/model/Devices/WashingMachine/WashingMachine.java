package pt.ipp.isep.dei.project.model.Devices.WashingMachine;

import pt.ipp.isep.dei.project.model.Devices.Device;
import pt.ipp.isep.dei.project.model.Devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.Devices.Programmable;
import pt.ipp.isep.dei.project.model.Program;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.Room;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class WashingMachine implements Device, Programmable {

    private String name;
    private Room location;
    private WashingMachineSpecs specs;
    private List<Reading> readingList;
    private boolean isActive;
    private LocalDateTime deactivationDate;


    public WashingMachine(String name, DeviceSpecs washingMachineSpecs) {
        this.name = name;
        this.specs = (WashingMachineSpecs) washingMachineSpecs;
        this.isActive = true;
        this.readingList = new ArrayList<>();

    }

    /**
     * method that get the nominal power of the devices.
     *
     * @return the nominal power of the device.
     */
    @Override
    public double getNominalPower() {
        return specs.getNominalPower();
    }

    /**
     * method that get a location (room) of a device.
     *
     * @return the location.
     */
    @Override
    public Room getLocation() {
        return this.location;
    }

    /**
     * get method
     *
     * @return name of device
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * method that gets the Type
     *
     * @return String
     */
    @Override
    public String getType() {
        return specs.getTypeName();
    }

    /**
     * method that gets the list of Reading of the Device.
     *
     * @return
     */
    @Override
    public List<Reading> getReadings() {
        return this.readingList;
    }

    /**
     * Method that gets the energy consumption in a day.
     *
     * @return Energy consumption of the device in a given day.
     */
    @Override
    public double getEnergyConsumptionInADay() {
        return specs.getEnergyConsumptionInADay();
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
        if (this.location.isDeviceNameExistant(name) || this.name == name) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        this.name = name;
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
        if (this.location.equals(location)) {
            return false;
        }
        this.location.getDeviceList().remove(this);
        this.location = location;
        this.location.addDevice(this);
        return true;
    }

    /**
     * Method that returns the attributes of the device specs.
     *
     * @return String with the attributes.
     */
    public String getDevSpecsAttributesToString() {
        return specs.getAttributesToString();
    }

    /**
     * method that get all attributes of a device by strings.
     *
     * @return the device attributes.
     */
    public String getAttributesToString() {

        StringBuilder attributes = new StringBuilder();
        attributes.append("1 - Name: " + name + "\n");
        attributes.append("2 - Device1 Specifications\n");
        attributes.append("3 - Location: " + location.getName() + "\n");
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
        return this.specs.setAttributeValue(attribute, value);
    }

    /**
     * method that creates the hashcode to two devices that are have the same name.
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
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
        return this.name.equalsIgnoreCase(listOne.getName());
    }

    /**
     * method that get the number of specifications of a device.
     *
     * @return the number of attributes.
     */
    public int getNumberOfSpecsAttributes() {
        return specs.getNumberOfAttributes();
    }

    /**
     * method that returns the name of device and its location
     *
     * @return String
     */
    @Override
    public String getNameToString() {
        StringBuilder nameLocation = new StringBuilder();
        nameLocation.append("Device: " + name);
        nameLocation.append(", located in room: " + location.getName() + "\n");
        return nameLocation.toString();
    }

    /**
     * Method that adds a readingList to the device.
     *
     * @param reading Reading to be added.
     */
    public void addReadingsToTheList(Reading reading) {
        this.readingList.add(reading);
    }

    /**
     * Method that calculates the sum of the value in each Reading in a given Reading list.
     *
     * @param readingList List with Readingss.
     * @return Double with the required sum.
     */
    public double getSumOfTheReadings(List<Reading> readingList) {
        double sum = 0;
        for (Reading reading : readingList) {
            sum += reading.getValue();
        }
        return sum;
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
        List<Reading> readings = getReadingsListInInterval(startDate, endDate);
        if (!(readings.isEmpty())) {
            readings.remove(0);
            totalEnergyConsumption = getSumOfTheReadings(readings);
        }
        return totalEnergyConsumption;
    }

    @Override
    public LocalDateTime getDeactivationDate() {
        return this.deactivationDate;
    }

    @Override
    public String getDateDeactivateDeviceToString() {
        return this.deactivationDate.toLocalDate().toString() + " " + this.deactivationDate.toLocalTime().toString();
    }

    /**
     * method that set the deactivate device, turning it to false and giving a date
     */
    @Override
    public boolean setDeactivateDevice() {
        if (this.isActive) {
            this.isActive = false;
            this.deactivationDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            return true;
        }
        return false;
    }

    /**
     * method that get an active device.
     *
     * @return an active device.
     */
    @Override
    public boolean getIsActive() {
        return isActive;
    }


    /**
     * get method
     *
     * @param startDate starting date of readingList
     * @param endDate   end date of readingList
     * @return map with coordinates (value of readingList and time)
     */
    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        Map<LocalDateTime, Double> hmap = new TreeMap<>();
        List<Reading> validReadingList = getReadingsListInInterval(startDate, endDate);
        for (Reading reading : validReadingList) {
            hmap.put(reading.getDateTime(), reading.getValue());
        }
        return hmap;
    }


    /**
     * get method
     *
     * @return list of specs of washing machine specs
     */
    @Override
    public List<String> getSpecsList() {
        return specs.getSpecsList();
    }

    /**
     * get method
     *
     * @param attributeName string attribute
     * @return name of attributes of washing machine specs
     */
    @Override
    public Object getAttributeValue(String attributeName) {
        return specs.getAttributeValue(attributeName);
    }


    /**
     * get method
     *
     * @return the string of an attribute of washing machine Specs
     */

    @Override
    public String getSpecsToString() {
        return this.specs.getAttributesToString();
    }

    /**
     * get method
     *
     * @param attributeName string attribute
     * @return type data of the attribute (ex.integer, double)
     */
    @Override
    public String getAttributeDataType(String attributeName) {
        return specs.getAttributeDataType(attributeName);
    }

    @Override
    public boolean isProgrammable() {
        return true;
    }

    @Override
    public Programmable asProgrammable(){
        return this;
    }

    @Override
    public boolean addProgram(Program program) {
        return specs.addProgram(program);
    }

}