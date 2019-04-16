package pt.ipp.isep.dei.project.model.devices;

public interface DeviceType {

    String getTypeName();

    Device createDevice(String name);
}
