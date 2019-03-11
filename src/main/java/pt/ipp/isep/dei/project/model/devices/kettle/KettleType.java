package pt.ipp.isep.dei.project.model.devices.kettle;

import pt.ipp.isep.dei.project.model.devices.Device;
import pt.ipp.isep.dei.project.model.devices.DeviceSpecs;
import pt.ipp.isep.dei.project.model.devices.DeviceType;

public class KettleType implements DeviceType {
    String typeName;

    public KettleType (){
        this.typeName = "Kettle";
    }

    public String getTypeName() {
        return typeName;
    }

    public Device createDevice (String name){
        DeviceSpecs kettleSpecs = new KettleSpecs();
        return new Kettle(name, kettleSpecs);
    }
    
}
