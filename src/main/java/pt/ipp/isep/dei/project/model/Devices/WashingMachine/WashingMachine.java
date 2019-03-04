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
     * -     * method that set the location (room) of a added device.
     * -     *
     * -     * @param location
     * -     * @return false if the location is equals to another device. True if not.
     * -
     */
    @Override
    public boolean setLocation(Room location) {
        if (Objects.isNull(this.location)) {
            this.location = location;
            location.addDevice(this);
            return true;
        }
        if (this.location.equals(location)) {
            return false;
        }
        this.location.getDeviceList().remove(this);
        this.location = location;
        location.addDevice(this);
        return true;
    }

    /**
     * -     * method that set the given name only if the name don't exists in DeviceList
     * -     * and if it is different than the name that the Device1 has.
     * -     *
     * -     * @param name String given name
     * -     * @return true if sets false if don't
     * -
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
     * method that gets the Device Specifications
     * @return DeviceSpecs
     */
    @Override
    public DeviceSpecs getSpecs() {
        return this.specs;
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
     * Method that gets the boolean attribute from each device.
     * @return boolean
     */
    public boolean isActive() {
        return isActive;
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

    @Override
    public LocalDateTime getDeactivationDate() {
        return this.deactivationDate;
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