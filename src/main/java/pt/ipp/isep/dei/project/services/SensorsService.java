package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import pt.ipp.isep.dei.project.model.Reading;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SensorsService {

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    @Autowired
    private RoomSensorService roomSensorService;

    public Map<LocalDateTime, Double> getMapInstantsBelowComfortTemperatureInInterval
            (Map<LocalDate, List<Double>> mapComfortTemperatureMinMaxByDay, List<Reading> roomReadings) {

        Map<LocalDateTime, Double> mapOfInstancesBelowComfortTemp = new HashMap<>();

        Set<Map.Entry<LocalDate, List<Double>>> set = mapComfortTemperatureMinMaxByDay.entrySet();

        for (Map.Entry<LocalDate, List<Double>> dailyComfortTemp : set) {
            for (Reading roomReading : roomReadings) {
                if (dailyComfortTemp.getKey().equals(roomReading.getDateTime().toLocalDate())
                        && dailyComfortTemp.getValue().get(0) > roomReading.getValue()) {
                    mapOfInstancesBelowComfortTemp.put(roomReading.getDateTime(), roomReading.getValue());

                }
            }
        }
        return mapOfInstancesBelowComfortTemp;
    }



}
