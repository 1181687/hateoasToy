package pt.ipp.isep.dei.project.model;

public interface DeviceType {
    Device createDevice(String name, Room location);
}
