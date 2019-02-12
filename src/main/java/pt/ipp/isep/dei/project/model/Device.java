package pt.ipp.isep.dei.project.model;

public interface Device {

    Room getLocation();

    String getName();

    boolean setName();

    boolean setLocation();

    String getAttributesToString();

    int hashCode();

    boolean equals();

    void setDeactivateDevice();


    String getNameToString();


}
