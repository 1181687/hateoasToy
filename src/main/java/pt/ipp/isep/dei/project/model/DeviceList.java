package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeviceList {
    private List<Device1> mDeviceList;

    public DeviceList() {
        this.mDeviceList = new ArrayList<>();
    }

    /**
     * get DeviceList
     *
     * @return List<Device1>
     */
    public List<Device1> getDeviceList() {
        return mDeviceList;
    }

    /**
     * get size of list of devices
     *
     * @return integer
     */
    public int getSize() {
        return getDeviceList().size();
    }

    /**
     * gets a Device1 by it's position
     *
     * @param position integer position of Device1
     * @return Device1
     */
    public Device1 getDeviceByPosition(int position) {
        return mDeviceList.get(position);
    }

    /**
     * Method that adds a device to the list of Devices
     *
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */
    public boolean addDevice(Device1 device) {
        if (Objects.isNull(device)) {
            return false;
        }
        if (!(mDeviceList.contains(device))) {
            this.mDeviceList.add(device);
            return true;
        }
        return false;
    }

    /**
     * method that check if a name of a Device1 already exists on the list of devices.
     *
     * @param name name of device
     * @return boolean true if exists, false if it doesn't
     */
    public boolean isNameExistant(String name) {

        for (int i = 0; i < mDeviceList.size(); i++) {
            if (mDeviceList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that get the String content Name and Location of all devices in the list,
     * grouped by device type.
     *
     * @return String with Device1 Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType() {

        StringBuilder content = new StringBuilder();
        Map<String, List<Device1>> byDeviceType = mDeviceList.stream()
                .collect(Collectors.groupingBy(Device1::getType));


        for (Map.Entry<String, List<Device1>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device1 dev : entry.getValue()) {

                content.append("- Device1 Name: ");
                content.append(dev.getName());
                content.append(", Location: ");
                content.append(dev.getLocation().getName());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that creates the same hashCode to the same DeviceLists
     *
     * @return the hashcode created
     */
    @Override
    public int hashCode() {
        return Objects.hash(mDeviceList);
    }

    /**
     * Equals method to determine if two DeviceList are equal.     *
     *
     * @param obj receives an object
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DeviceList)) {
            return false;
        }
        DeviceList listOne = (DeviceList) obj;
        return this.mDeviceList.equals(listOne.mDeviceList);
    }

    /**
     * method that check if the device list is empty
     */
    public boolean isEmpty() {
        return mDeviceList.isEmpty();
    }

    /**
     * method that get de active device list to string.
     *
     * @return the name and the status of the device. "Activated" if true, "Deactivated" if false.
     */
    public String getActiveDeviceListToString() {
        StringBuilder content = new StringBuilder();
        int deviceListLength = getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            if (mDeviceList.get(i - 1).getIsActive()) {
                content.append(numberInTheList + " - Name of the device: " + getDeviceList().get(i - 1).getName() + " - ACTIVATED");
                content.append("\n");
                numberInTheList++;
            } else {
                content.append(numberInTheList + " - Name of the device: " + getDeviceList().get(i - 1).getName() + " - DEACTIVATED");
                content.append("\n");
                numberInTheList++;
            }
        }
        return content.toString();
    }

    /**
     * Method that remove a device from the list of devices
     */
    public boolean removeDevice(Device1 device) {
        return this.getDeviceList().remove(device);
    }

    /**
     * Method that gets all the devices of a certain type.
     *
     * @param type Required type.
     * @return DeviceList with all the devices of the required type.
     */
    public DeviceList getAllDevicesOfAType(String type) {
        DeviceList listOfDevicesWithTheType = new DeviceList();
        for (Device1 device : mDeviceList) {
            if (device.getType().equals(type)) {
                listOfDevicesWithTheType.addDevice(device);
            }
        }
        return listOfDevicesWithTheType;
    }


    /**
     * Method that sets the value of an attribute of a device.
     *
     * @param devicePosition    Device1 position in the list of devices.
     * @param attributePosition Position of the attribute to be set.
     * @param value             Value to be used.
     * @return True or false.
     */
    public boolean setAttribute(int devicePosition, int attributePosition, double value) {
        Device1 device = mDeviceList.get(devicePosition);
        return device.setAttributesDevType(attributePosition, value);
    }

    /**
     * Method that returns the energy consumption of a device.
     *
     * @param devicePosition Device1 position in the list of devices.
     * @return Double with the energy consumption.
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        Device1 device = mDeviceList.get(devicePosition);
        return device.getEnergyConsumptionInADay();
    }

    /**
     * Method that returns the combined energy consumption of all the devices in a list.
     *
     * @return Double with the combined energy consumption.
     */
    public double getTotalEnergyConsumption() {
        double totalEnergyConsumption = 0;
        for (Device1 device : mDeviceList) {
            totalEnergyConsumption += device.getEnergyConsumptionInADay();
        }
        return Utils.round(totalEnergyConsumption, 2);
    }

    /**
     * method that delete a device from the list of devices.
     *
     * @param device
     * @return true if the device was removed. False if not.
     */
    public boolean deleteDevice(String device) {
        for (Device1 searchDevice : this.mDeviceList) {
            if (device.equals(searchDevice.getName())) {
                this.mDeviceList.remove(searchDevice);
                return true;
            }
        }
        return false;
    }

    /**
     * method that get the name of the device by position.
     * @param position
     * @return null if the list is empty. True if
     */
    public String getDeviceNameByPosition(int position) {
        if (this.mDeviceList.isEmpty()) {
            return "There are no devices in the device list.";
        }
        return this.mDeviceList.get(position).getName();
    }

    /**
     * method that deactivate the device.
     *
     * @param device
     * @return true if the device was deactivated. False, if not.
     */
    public boolean deactivateDevice(String device) {
        for (Device1 searchDevice : this.mDeviceList) {
            if (device.equals(searchDevice.getName())) {
                searchDevice.setDeactivateDevice();
                return true;
            }
        }
        return false;
    }


}