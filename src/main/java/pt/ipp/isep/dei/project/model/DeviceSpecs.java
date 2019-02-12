package pt.ipp.isep.dei.project.model;

public interface DeviceSpecs {


    double getEnergyConsumptionInADay();

    double getNominalPower();

    String getAttributesToString();

    boolean setAttribute (int attribute, double value);

    int getNumberOfAttributes();
}
