package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Measurable {

    double getNominalPower();

    String getNameToString();

    double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate);

    Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate);

    List<Readings> getReadings();


    default List<Readings> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Readings> readingsList = new ArrayList<>();
        for (Readings readings : getReadings()) {
            if (!startDate.isAfter(readings.getDateTime()) && !endDate.isBefore(readings.getDateTime())) {
                readingsList.add(readings);
            }
        }
        return readingsList;
    }
}
