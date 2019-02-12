package pt.ipp.isep.dei.project.model;

public interface DeviceType {
    Device1 createDevice(String name, Room location);

    String getTypeName();

}