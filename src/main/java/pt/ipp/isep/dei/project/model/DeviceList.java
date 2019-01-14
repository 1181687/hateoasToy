package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Objects;

public class DeviceList {
    private List<Device> mDeviceList = new ArrayList<>();

    public DeviceList() {
    }

    /**
     * get DeviceList
     *
     * @return List<Device>
     */
    public List<Device> getmDeviceList() {
        return mDeviceList;
    }

    /**
     * get size of list of devices
     * @return integer
     */
    public int getLength() {
        return getmDeviceList().size();
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
        }
        return true;
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
                content.append(dev.getmName());
                content.append(", Location: ");
                content.append(dev.getLocation().getmName());
                content.append(".\n");
            }
            content.append("\n");
        }
        return content.toString();
    }


    /**
     * method that gets the List of devices ordered by Type
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
    //ELECTRIC WATER HEATER

    public DeviceSpecs createNewElectricWaterHeater(double volumeOfWater) {

        return new ElectricWaterHeater(volumeOfWater);
    }

    public Device newElectricWaterHeater(String name, Room selectedRoom, double nominalPower, double volumeOfWater) {

        DeviceSpecs electricWaterHeater = createNewElectricWaterHeater(volumeOfWater);

        return new Device(name, selectedRoom, electricWaterHeater, nominalPower);
    }

    //WASHING MACHINE

    public DeviceSpecs createNewWashingMachine (double capacity){
        return new WashingMachine(capacity);
    }

    public Device newWashingMachine (String name, Room selectedRoom, double nominalPower, double capacity){
        DeviceSpecs washingMachine = createNewWashingMachine(capacity);
        return new Device(name,selectedRoom, washingMachine, nominalPower);
    }

    //DISH WASHER
    public DeviceSpecs createNewDishWasher (int capacity){
        return new DishWasher(capacity);
    }

    public Device newDishWasher (String name, Room selectedRoom, double nominalPower, int capacity){
        DeviceSpecs dishWasher = createNewDishWasher(capacity);
        return new Device(name, selectedRoom, dishWasher, nominalPower);
    }

    //LAMP
    public DeviceSpecs createNewLamp (double luminousFlux){
        return new Lamp(luminousFlux);
    }

    public Device newLamp (String name, Room selectedRoom, double nominalPower, double luminousFlux){
        DeviceSpecs lamp = createNewLamp(luminousFlux);
        return new Device(name, selectedRoom, lamp, nominalPower);
    }

    //FRIDGE
    public DeviceSpecs createNewFridge (double freezerCapacity, double refrigeratorCapacity){
        return new Fridge(freezerCapacity, refrigeratorCapacity);
    }

    public Device newFridge (String name, Room selectedRoom, double nominalPower,double freezerCapacity, double refrigeratorCapacity){
        DeviceSpecs fridge = createNewFridge(freezerCapacity, refrigeratorCapacity);
        return new Device(name, selectedRoom, fridge, nominalPower);
    }


    /**
     * Method that adds a device to the existing list.
     * @param device
     * @return
     */
    public boolean addDeviceToDeviceList (Device device){
        if (!(mDeviceList.contains(device))) {
            mDeviceList.add(device);
            return true;
        }
        return false;
    }


}
