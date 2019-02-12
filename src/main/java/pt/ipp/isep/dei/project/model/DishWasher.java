package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.Map;

public class DishWasher implements Device, Measurable {


    @Override
    public double getNominalPower() {
        return 0;
    }

    @Override
    public Room getLocation() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean setName() {
        return false;
    }

    @Override
    public boolean setLocation() {
        return false;
    }

    @Override
    public String getAttributesToString() {
        return null;
    }

    @Override
    public boolean equals() {
        return false;
    }

    @Override
    public void setDeactivateDevice() {

    }

    @Override
    public String getNameToString() {
        return null;
    }

    @Override
    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return 0;
    }

    @Override
    public Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
