package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;

public interface Measurable {

    double getNominalPower();

    String getNameToString();

    double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate);
}
