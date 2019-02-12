package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.Map;

public class WashingMachine implements Device, Measurable {


    @Override
    public double getNominalPower() {
        return 0;
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
