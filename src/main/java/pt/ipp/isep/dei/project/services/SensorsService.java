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

}
