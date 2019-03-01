package pt.ipp.isep.dei.project.model.Devices;

public interface DeviceType {

    String getTypeName();

    Device createDevice(String name);
}
