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
    public Map<LocalDateTime, Double> getMapInstantsBelowComfortTemperatureInInterval
            (Map<LocalDate, List<Double>> mapComfortTemperatureMinMaxByDay, List<Reading> roomReadings) {

        Map<LocalDateTime, Double> mapOfInstancesBelowComfortTemp = new HashMap<>();

        Set<Map.Entry<LocalDate, List<Double>>> set = mapComfortTemperatureMinMaxByDay.entrySet();

        if (!set.isEmpty()) {
            for (Map.Entry<LocalDate, List<Double>> dailyComfortTemp : set) {
                for (Reading roomReading : roomReadings) {
                    LocalDate dayOfComfortTemp = dailyComfortTemp.getKey();
                    LocalDate dayOfRoomReading = roomReading.getDateTime().toLocalDate();
                    double valueOfRoomReading = roomReading.getValue();

                    if (dayOfComfortTemp.equals(dayOfRoomReading)
                            && !Objects.isNull(dailyComfortTemp.getValue().get(0)) &&
                            Double.compare(dailyComfortTemp.getValue().get(0), valueOfRoomReading) == 1) {
                        mapOfInstancesBelowComfortTemp.put(roomReading.getDateTime(), valueOfRoomReading);
                    }
                }
            }
        }
        return mapOfInstancesBelowComfortTemp;
    }

    public List<LocalDateTime> getInstantListOutOfComfortLevel(Map<LocalDateTime, Double> mapOfInstantsOutOfComfortLevel) {
        Set<Map.Entry<LocalDateTime, Double>> set = mapOfInstantsOutOfComfortLevel.entrySet();

        List<LocalDateTime> listOfInstantsOutOfComfortLevel = new ArrayList<>();

        if (!set.isEmpty()) {
            for (Map.Entry<LocalDateTime, Double> outOfComfortInstants : set) {
                listOfInstantsOutOfComfortLevel.add(outOfComfortInstants.getKey());
            }
        }
        return listOfInstantsOutOfComfortLevel;
    }

    public boolean existsInstantsOutOfComfortLevel(Map<LocalDateTime, Double> mapOfInstantsOutOfComfortLevel) {
        return !getInstantListOutOfComfortLevel(mapOfInstantsOutOfComfortLevel).isEmpty();
    }

                    if (!Objects.isNull(mapComfTempDaily.getValue()) && localDateReading.compareTo(localDateComfTempMax) == 0 && Double.compare(valueReading, mapComfTempDaily.getValue().get(1)) == 1) {


                        mapInstantsAboveComfortTemperature.put(roomReading.getDateTime(), valueReading);
                    }
                }
            }
        }
        return mapInstantsAboveComfortTemperature;
    }
}
