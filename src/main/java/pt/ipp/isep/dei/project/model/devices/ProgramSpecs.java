package pt.ipp.isep.dei.project.model.devices;

public interface ProgramSpecs {

    boolean setAttributes(String attributeName, Object attributeValue);

    double getEnergyConsumption();
}
