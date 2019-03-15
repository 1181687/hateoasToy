package pt.ipp.isep.dei.project.model.devices;

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

    boolean setAttributeValue(String attributeName, Object attributeValue);

    String getTypeName();

    boolean isProgrammable();

    Programmable asProgrammable();

    //DEFAULT METHODS


    /**
     * get method
     * @param attributeName string name of attribute
     * @return type data of the attribute (ex.integer, double)
     */
    default String getAttributeDataType(String attributeName) {
        if ((getAttributeValue(attributeName).equals("not a valid attribute"))) {
            return "not a valid attribute";
        }
        return getAttributeValue(attributeName).getClass().getName().substring(10);
    }
}
