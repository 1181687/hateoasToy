package pt.ipp.isep.dei.project.model.Devices;

import pt.ipp.isep.dei.project.model.Room;

public interface DeviceType {

    String getTypeName();

    Device createDevice(String name, Room location);
}
