package pt.ipp.isep.dei.project.model;

public interface DeviceSpecs {

    String getmTypeName ();

    double getEnergyConsumptionInADay();

    double getmNominalPower();

    String getAttributesToString();

    boolean setAttribute (int attribute, double value);

    int getNumberOfAttributes();
}
