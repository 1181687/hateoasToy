package pt.ipp.isep.dei.project.model;

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
     * method that displays a string of the choosen attribute (name of the attribute and its value)
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

    boolean setAttributeValue(String attributeName, Object attributeValue);

}
