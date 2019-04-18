package pt.ipp.isep.dei.project.model;

import pt.ipp.isep.dei.project.model.devices.DeviceReading;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface Measurable {

    String getNameToString();

    double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate);

    Map<LocalDateTime, Double> getDataSeries(LocalDateTime startDate, LocalDateTime endDate);

    List<DeviceReading> getReadings();


/*    default List<DeviceReading> getReadingsListInInterval(LocalDateTime startDate, LocalDateTime endDate) {
        List<DeviceReading> geoAreaReadingList = new ArrayList<>();
        int meteringPeriodGrid = Integer.parseInt(Utils.readConfigFile("Configuration.properties", "MeteringPeriodGrid"));
        for (DeviceReading geoAreaReading : getReadings()) {
            if (!startDate.isAfter(geoAreaReading.getDateTime()) && !endDate.isBefore(geoAreaReading.getDateTime())) {
                geoAreaReadingList.add(geoAreaReading);
            }
        }
        if (!(geoAreaReadingList.isEmpty())) {
            LocalDateTime firstValidReadingDateTime = geoAreaReadingList.get(0).getDateTime();

            if (startDate.isAfter(firstValidReadingDateTime.minusMinutes(meteringPeriodGrid))) {
                geoAreaReadingList.remove(0);
            }
        }
        return geoAreaReadingList;
    }*/

    double getNominalPower();
}
