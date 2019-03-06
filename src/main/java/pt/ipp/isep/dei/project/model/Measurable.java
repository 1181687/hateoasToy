package pt.ipp.isep.dei.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Measurable {

    String getNameToString();

    double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate);

    Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate);

    List<Reading> getReadings();


    default List<Reading> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<Reading> readingList = new ArrayList<>();
        for (Reading reading : getReadings()) {
            if (!startDate.isAfter(reading.getDateTime()) && !endDate.isBefore(reading.getDateTime())) {
                readingList.add(reading);
            }
        }
        return readingList;
    }

    double getNominalPower();
}
