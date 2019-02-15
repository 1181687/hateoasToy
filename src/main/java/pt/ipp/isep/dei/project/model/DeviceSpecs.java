package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface DeviceSpecs {

    double getEnergyConsumptionInADay();

    double getNominalPower();

    String getAttributesToString();

    int getNumberOfAttributes();

    List<String> getSpecsList();

    Object getAttributeValue(String attributeName);

    boolean setAttributeValue(String attributeName, Object attributeValue);

}
