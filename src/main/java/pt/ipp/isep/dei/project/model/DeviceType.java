package pt.ipp.isep.dei.project.model;

public interface DeviceType {

    String getTypeName();

    Device createDevice(String name, Room location);
}
