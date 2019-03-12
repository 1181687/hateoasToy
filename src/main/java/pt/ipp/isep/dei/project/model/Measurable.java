package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.utils.Utils;

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
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        for (Reading reading : getReadings()) {
            if (!startDate.isAfter(reading.getDateTime()) && !endDate.isBefore(reading.getDateTime())) {
                readingList.add(reading);
            }
        }
        if (!(readingList.isEmpty())) {
            LocalDateTime firstValidReadingDateTime = readingList.get(0).getDateTime();

            if (startDate.isAfter(firstValidReadingDateTime.minusMinutes(meteringPeriodGrid))) {
                readingList.remove(0);
            }
        }
        return readingList;
    }

    double getNominalPower();
}
