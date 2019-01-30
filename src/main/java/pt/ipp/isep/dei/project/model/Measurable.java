package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.HashMap;

public interface Measurable {

    double getNominalPower();

    String getNameToString();

    double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate);

    HashMap<LocalDateTime,Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate);
}
