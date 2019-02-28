package pt.ipp.isep.dei.project.model.Devices;

import java.util.List;

public interface DeviceSpecs {
    /**
     * get method
     *
     * @return energy consumption
     */
    double getEnergyConsumptionInADay();

    /**
     * get method
     *
     * @return nominal power
     */
    double getNominalPower();

    /**
     * method that displays a string of the chosen attribute (name of the attribute and its value)
     * @return
     */
    String getAttributesToString();

    /**
     * get method
     * @return number of Washing Machine attributes
     */
    int getNumberOfAttributes();

    List<String> getSpecsList();

    Object getAttributeValue(String attributeName);

    String getAttributeDataType(String attributeName);

    boolean setAttributeValue(String attributeName, Object attributeValue);

    String getTypeName();
}