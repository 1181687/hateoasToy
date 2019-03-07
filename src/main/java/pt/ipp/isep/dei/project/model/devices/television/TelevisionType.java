package pt.ipp.isep.dei.project.model.devices.television;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;


public class TelevisionType implements DeviceType {
    String typeName;

    public TelevisionType() {
        this.typeName = "Television";
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }

    public Device createDevice(String name) {
        DeviceSpecs televisionSpecs = new TelevisionSpecs();
        return new Television(name, televisionSpecs);
    }
}
