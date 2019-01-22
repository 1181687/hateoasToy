package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DeviceList {
    private List<Device> mDeviceList;
    private static final String SAME_NAME = "Name already exists. Please write a new one.";

    public DeviceList() {
        this.mDeviceList = new ArrayList<>();
    }

    /**
     * get DeviceList
     *
     * @return List<Device>
     */
    public List<Device> getDeviceList() {
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
     * gets a Device by it's position
     *
     * @param position integer position of Device
     * @return Device
     */
    public Device getDeviceByPosition(int position) {
        return mDeviceList.get(position);
    }

    /**
     * Method that adds a device to the list of Devices
     *
     * @param device the device to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the device is null
     */
    public boolean addDevice(Device device) {
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
     * method that check if a name of a Device already exists on the list of devices.
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
     * @return String with Device Name and Location grouped by Type.
     */
    public String getContentNameLocationOrderedByType() {

        StringBuilder content = new StringBuilder();
        Map<String, List<Device>> byDeviceType = mDeviceList.stream()
                .collect(Collectors.groupingBy(Device::getType));


        for (Map.Entry<String, List<Device>> entry : byDeviceType.entrySet()) {
            content.append(entry.getKey());
            content.append("\n");
            for (Device dev : entry.getValue()) {

                content.append("- Device Name: ");
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
     * Method that create a new Device ELECTRIC WATER HEATER
     *
     * @param name                name of the device
     * @param selectedRoom        Room where the device will be installed
     * @param hotWaterTemperature maximum temperature configured by user
     * @param maximumVolume       capacity in liters of the Electric Water Heater
     * @param nominalPower        nominal power of the device
     * @param performanceRatio    performance ratio introduced by user that typically is 0,9
     * @return a new device
     */
    public Device newElectricWaterHeater(String name, Room selectedRoom, double hotWaterTemperature, double maximumVolume, double nominalPower, double performanceRatio) {

        if (isNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        ElectricWaterHeater electricWaterHeater = new ElectricWaterHeater(hotWaterTemperature, maximumVolume, nominalPower, performanceRatio);

        return new Device(name, selectedRoom, electricWaterHeater);
    }


    /**
     * Method that create a new Device WASHING MACHINE
     *
     * @param name name of the device
     * @param selectedRoom Room where the device will be installed
     * @param nominalPower nominal power of the device
     * @param capacity capacity in kilograms of the Electric Water Heater
     * @param programList list of programs
     * @return a new device
     */
    public Device newWashingMachine(String name, Room selectedRoom, double nominalPower, double capacity,
                                    ProgramList programList) {
        if (isNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        WashingMachine washingMachine = new WashingMachine(capacity, nominalPower, programList);
        return new Device(name, selectedRoom, washingMachine);
    }


    /**
     * Method that create a new Device DISH WASHER
     *
     * @param name         name of the device
     * @param selectedRoom Room where the device will be installed
     * @param nominalPower nominal power of the device
     * @param capacity     capacity in dish sets of the Electric Water Heater
     * @param programList  list of programs
     * @return a new device
     */
    public Device newDishWasher(String name, Room selectedRoom, double nominalPower, int capacity, ProgramList programList) {
        if (isNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DishWasher dishwasher = new DishWasher(capacity, nominalPower, programList);
        return new Device(name, selectedRoom, dishwasher);
    }


    /**
     * Method that create a new Device LAMP
     *
     * @param name name of the device
     * @param selectedRoom Room where the device will be installed
     * @param nominalPower nominal power of the device
     * @param luminousFlux luminous flux of the lamp
     * @return a new device
     */

    public Device newLamp(String name, Room selectedRoom, double nominalPower, double luminousFlux) {
        if (isNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        DeviceSpecs lamp = new Lamp(luminousFlux, nominalPower);
        return new Device(name, selectedRoom, lamp);
    }

    /**
     * Method that create a new Device FRIDGE
     *
     * @param name name of the device
     * @param selectedRoom Room where the device will be installed
     * @param annualEnergyConsumption annual ennergy consumption of the fridge
     * @param nominalPower nominal power of the device
     * @param freezerCapacity freezer Capacity
     * @param refrigeratorCapacity refrigerator Capacity
     * @return a new device
     */
    public Device newFridge(String name, Room selectedRoom, double annualEnergyConsumption, double nominalPower, double freezerCapacity, double refrigeratorCapacity) {
        if (isNameExistant(name)) {
            throw new RuntimeException(SAME_NAME);
        }
        Fridge fridge = new Fridge(freezerCapacity, refrigeratorCapacity, annualEnergyConsumption, nominalPower);
        return new Device(name, selectedRoom, fridge);
    }


    /**
     * method that displays the device list content
     *
     * @return content of the device list
     */
    public String getDeviceListToString() {
        StringBuilder content = new StringBuilder();
        int deviceListLength = getSize();
        int numberInTheList = 1;
        for (int i = 1; i <= deviceListLength; i++) {
            content.append(numberInTheList + " - Name of the device: " + getDeviceList().get(i - 1).getName());
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * method that check if the device list is empty
     */
    public boolean isDeviceListEmpty() {
        return mDeviceList.isEmpty();
    }

    /**
     * method that get de device type list content
     *
     * @return the content of the list by string
     */
        public String getDeviceTypeListToString() {
            StringBuilder content = new StringBuilder();
            int numberInTheList = 1;
            for (DeviceTypes deviceTypeName : DeviceTypes.values()) {
                String deviceType = deviceTypeName.getDeviceTypeName();
                content.append(numberInTheList + "- ");
                content.append(deviceType);
                content.append("\n");
                numberInTheList++;
            }
            return content.toString();
        }

    /**
     * Method that remove a device from the list of devices
     */
    public boolean removeDevice(Device device) {
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
        for (Device device : mDeviceList) {
            if (device.getType().equals(type)) {
                listOfDevicesWithTheType.addDevice(device);
            }
        }
        return listOfDevicesWithTheType;
    }

    /**
     * Method that gets the name of a device.
     * @param devicePosition Device position in the list of devices.
     * @return String with the device name.
     */
    public String getDeviceName(int devicePosition) {
        Device device = mDeviceList.get(devicePosition);
        return device.getName();
    }

    /**
     * Method that sets the value of an attribute of a device.
     *
     * @param devicePosition Device position in the list of devices.
     * @param attributePosition Position of the attribute to be set.
     * @param value Value to be used.
     * @return True or false.
     */
    public boolean setAttribute(int devicePosition, int attributePosition, double value) {
        Device device = mDeviceList.get(devicePosition);
        return device.setAttributesDevType(attributePosition, value);
    }

    /**
     * Method that returns the energy consumption of a device.
     *
     * @param devicePosition Device position in the list of devices.
     * @return Double with the energy consumption.
     */
    public double getEnergyConsumptionOfADevice(int devicePosition) {
        Device device = mDeviceList.get(devicePosition);
        return device.getEnergyConsumptionInADay();
    }

    /**
     * Method that returns the combined energy consumption of all the devices in a list.
     *
     * @return Double with the combined energy consumption.
     */
    public double getTotalEnergyConsumption() {
        double totalEnergyConsumption = 0;
        for (Device device : mDeviceList) {
            totalEnergyConsumption += device.getEnergyConsumptionInADay();
        }
        return Utils.round(totalEnergyConsumption, 2);
    }
}
