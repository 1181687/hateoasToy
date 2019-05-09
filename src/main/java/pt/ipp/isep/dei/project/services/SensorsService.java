package pt.ipp.isep.dei.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.ReadingMapper;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SensorsService {

    @Autowired
    private GeoAreaSensorService geoAreaSensorService;

    @Autowired
    private RoomSensorService roomSensorService;


    /**
     * gets Map<LocalDate, List<Double>> with list of the Comfort Temperature Min
     * and Max for category One or Two or Three, organized By LocalDate
     *
     * @param location
     * @param geoAreaId
     * @param typeId    type of sensor
     * @param startDate
     * @param endDate
     * @param category  house category (int)
     * @return Map<LocalDate, List < Double>> map with List of Comfort Temperature Min and Max,  By Day
     */
    public Map<LocalDate, List<Double>> getComfortTemperature(Location location, GeoAreaId geoAreaId, SensorTypeId typeId,
                                                              LocalDate startDate, LocalDate endDate, int category) {

        return this.geoAreaSensorService.getComfortTemperature(location, geoAreaId, typeId, startDate, endDate, category);
    }

    /**
     * Method that gets a RoomSensor by its id.
     *
     * @param idDTO Id of the RoomSensor.
     * @return RoomSensor required.
     */
    public RoomSensor getRoomSensorById(SensorIdDTO idDTO) {
        return RoomSensorMapper.mapToEntity(this.roomSensorService.getSensorById(idDTO));
    }


    public Map<LocalDateTime, Double> getInstantsOutOfComfortTemperature(Map<LocalDate, List<Double>> mapComfortTemperatureMinMaxByDay, List<ReadingDTO> intervalRoomReadings, int option) {

        //controlar esta variavel na ui: if BELLOW --> int option = 0; if ABOVE --> int option = 1;

        List<Reading> readings = new ArrayList<>();
        for (ReadingDTO readingDTO : intervalRoomReadings) {
            readings.add(ReadingMapper.mapToEntity(readingDTO));
        }

        Map<LocalDateTime, Double> mapInstantsAboveComfortTemperature = new HashMap<>();

        Set<Map.Entry<LocalDate, List<Double>>> mapComfTemp = mapComfortTemperatureMinMaxByDay.entrySet();

        if (!mapComfTemp.isEmpty()) {
            for (Reading roomReading : readings) {

                for (Map.Entry<LocalDate, List<Double>> mapComfTempDaily : mapComfTemp) {

                    LocalDate localDateReading = roomReading.getDateTime().toLocalDate();
                    double valueReading = roomReading.getValue();
                    LocalDate localDateComfTempMax = mapComfTempDaily.getKey();

                    //IF OPTION IS ABOVE
                    if (option == 1 && !Objects.isNull(mapComfTempDaily.getValue()) && localDateReading.compareTo(localDateComfTempMax) == 0
                            && Double.compare(valueReading, mapComfTempDaily.getValue().get(option)) == 1) {
                        mapInstantsAboveComfortTemperature.put(roomReading.getDateTime(), valueReading);
                    }
                    //IF OPTION IS BELLOW
                    if (option == 0 && !Objects.isNull(mapComfTempDaily.getValue()) && localDateComfTempMax.equals(localDateReading) &&
                            Double.compare(mapComfTempDaily.getValue().get(option), valueReading) == 1) {
                        mapInstantsAboveComfortTemperature.put(roomReading.getDateTime(), valueReading);
                    }
                }
            }
        }
        return mapInstantsAboveComfortTemperature;
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

    public boolean existInstantsOutOfComfortLevel(Map<LocalDateTime, Double> mapOfInstantsOutOfComfortLevel) {
        return !getInstantListOutOfComfortLevel(mapOfInstantsOutOfComfortLevel).isEmpty();
    }

    /**
     * gets room readings DTO from a sensor by sensorId, in an interval of days
     *
     * @param startDate    initial LocalDate
     * @param endDate      final LocalDate
     * @param roomSensorId SensorId
     * @return List of Reading DTO
     */
    public List<ReadingDTO> getRoomReadingsDTO(LocalDate startDate, LocalDate endDate, SensorId roomSensorId) {
        return this.roomSensorService.getReadingsDTO(startDate, endDate, roomSensorId);
    }

    public boolean existSensors(RoomId roomId, SensorTypeId sensorTypeId) {
        return roomSensorService.existSensors(roomId, sensorTypeId);
    }

    public SensorId getSensorId(String roomId, SensorTypeId sensorTypeId) {
        RoomId roomId1 = new RoomId(roomId);
        return roomSensorService.getSensorId(roomId1, sensorTypeId);
    }

    public List<LocalDate> getDaysWithoutComfortTemp(Map<LocalDate, List<Double>> mapComfortDailyTemperature) {
        return geoAreaSensorService.getDaysWithoutComfortTemp(mapComfortDailyTemperature);
    }

    public boolean existsDaysWithoutComfortTemp(Map<LocalDate, List<Double>> mapComfortDailyTemperature) {
        return geoAreaSensorService.existsDaysWithoutComfortTemp(mapComfortDailyTemperature);
    }


    public boolean existReadingsHouseAreaAndRoom(RoomId roomId, SensorTypeId sensorTypeId, GeoAreaId geoAreaId,
                                                 LocalDate startDate, LocalDate endDate) {
        RoomSensor roomSensor = roomSensorService.getRoomSensor(roomId, sensorTypeId);
        GeoAreaSensor geoAreaSensor = geoAreaSensorService.getGeoAreaSensor(geoAreaId, sensorTypeId);
        return roomSensor.existReadingsBetweenDates(startDate, endDate) && geoAreaSensor.existReadingsBetweenDates(startDate, endDate);
    }
/*
    //GGG////////////////////////////////////////////////////////////
    public boolean existReadingsBothHouseAreaAndRoomDaily(Location location, SensorTypeId sensorTypeId,
                                                          LocalDate startDate, LocalDate endDate, RoomId roomId) {

        for (LocalDate dateIterator = startDate; !dateIterator.isAfter(endDate); dateIterator = dateIterator.plusDays(1)) {

            if (geoAreaSensorService.hasReadingsInGivenDay(location, sensorTypeId, startDate, endDate, dateIterator) &&
                    (roomSensorService.hasReadingsInGivenDay(dateIterator, roomId, sensorTypeId))) {
                return true;
            }

        }
        return false;
    }*/

}
