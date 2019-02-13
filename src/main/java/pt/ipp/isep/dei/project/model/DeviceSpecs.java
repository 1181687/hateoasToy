package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface DeviceSpecs {

    double getEnergyConsumptionInADay();

    double getNominalPower();

    String getAttributesToString();

    boolean setAttribute (int attribute, double value);

    int getNumberOfAttributes();

    List<String> getAttributeToString();

    Object getAttributeValue(String attributeName);

    boolean setAttributeValue(String attributeName, Object attributeValue);
}
