package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Reading;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SensorsService {

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    @Autowired
    private RoomSensorService roomSensorService;

    /**
     * receives a list of readings and gets a Map Map<LocalDateTime, Double>  of instants and temperatures, that were
     * above the comfort temperature in an interval
     *
     * @param mapComfortTemperatureMinMaxByDay Map<LocalDate, List<Double>> with list of the Comfort Temperature Min
     *                                         and Max for category One or Two or Three, organized By LocalDate
     * @param intervalRoomReadings             list of Reading in an interval
     * @return Map<LocalDateTime, Double> map os instants and temperatures above the comfort temperature
     */
    public Map<LocalDateTime, Double> getMapInstantsAboveComfortTemperatureInInterval(Map<LocalDate, List<Double>> mapComfortTemperatureMinMaxByDay,
                                                                                      List<Reading> intervalRoomReadings) {
        Map<LocalDateTime, Double> mapInstantsAboveComfortTemperature = new HashMap<>();

        Set<Map.Entry<LocalDate, List<Double>>> mapComfTemp = mapComfortTemperatureMinMaxByDay.entrySet();

        if (!mapComfTemp.isEmpty()) {
            for (Reading roomReading : intervalRoomReadings) {

                for (Map.Entry<LocalDate, List<Double>> mapComfTempDaily : mapComfTemp) {

                    LocalDate localDateReading = roomReading.getDateTime().toLocalDate();
                    double valueReading = roomReading.getValue();
                    LocalDate localDateComfTempMax = mapComfTempDaily.getKey();

                    if (!Objects.isNull(mapComfTempDaily.getValue()) && localDateReading.compareTo(localDateComfTempMax) == 0 && Double.compare(valueReading, mapComfTempDaily.getValue().get(1)) == 1) {


                        mapInstantsAboveComfortTemperature.put(roomReading.getDateTime(), valueReading);
                    }
                }
            }
        }
        return mapInstantsAboveComfortTemperature;
    }
}
