package pt.ipp.isep.dei.project.model;

import java.util.List;

public interface DeviceSpecs {

    String getmTypeName ();

    double getEnergyConsumptionInADay();

    double getmNominalPower();

    String getAttributesToString();

    boolean setAttribute (int attribute, double value);

    int getNumberOfAttributes();

    List<String> getAttributeNames();

    Object getAttributeValue(String attributeName);

    boolean setAttributeValue(String attributeName, Object attributeValue);

    String getEditableAttributesContent();


}
