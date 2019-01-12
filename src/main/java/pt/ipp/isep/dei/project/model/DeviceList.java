package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeviceList {
    private List<Device> mDeviceList = new ArrayList<>();

    public DeviceList() {
    }


    ///////////////////////////////////////////////////////////////////////
    public List<Device> getmDeviceList() {
        return mDeviceList;
    }

    public int getLength() {
        return getmDeviceList().size();
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

}
