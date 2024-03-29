package pt.ipp.isep.dei.project.controllers.instantstempoutofcomfortlevelcontroller;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.ReadingDTO;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.house.RoomDTO;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class InstantsTempOutOfComfortLevelController {

    private SensorsService sensorsService;
    private RoomService roomService;
    private SensorTypeId sensorTypeId = new SensorTypeId("temperature");
    private RoomId roomId;
    private SensorId roomSensorId;
    private Location location;
    private GeoAreaId geoAreaId;
    private Map<LocalDate, List<Double>> comfortTemp;
    private Map<LocalDateTime, Double> mapInstantsOutOfComfortTemp;
    private List<Reading> roomReadings;
    private int category;
    private int option;
    private List<LocalDateTime> listOfInstantsOutOfComfortTemp;
    private List<LocalDate> daysNotConsidered = new ArrayList<>();


    /**
     * Constructor.
     *
     * @param sensorsService Service to be used.
     */
    public InstantsTempOutOfComfortLevelController(HouseService houseService, SensorsService sensorsService, RoomService roomService) {
        this.sensorsService = sensorsService;
        this.roomService = roomService;
        this.location = houseService.getHouse().getLocation();
        this.geoAreaId = houseService.getHouse().getInsertedGeoArea().getId();
    }

    //save category from UI
    public void setCategory(int category) {
        this.category = category;
    }

    //save option from UI
    public void setOption(int option) {
        this.option = option;
    }

    public List<RoomDTO> getAllRoomsDTO() {
        return this.roomService.getAllRoomsDTO();
    }

    public boolean isRoomListEmpty (){
        return roomService.isListOfRoomsEmpty();
    }

    public void setRoomId (String roomId){
        this.roomId= new RoomId(roomId);
    }

    public boolean existTempSensors() {
        return sensorsService.existSensors(roomId, sensorTypeId);
    }

    public Map<LocalDate, List<Double>> getComfortTemperature (LocalDate startDate, LocalDate endDate){
        return comfortTemp = sensorsService.getComfortTemperature(location, geoAreaId, sensorTypeId, startDate, endDate, category);
    }

    public void setSensorID(String roomId) {
        this.roomSensorId = sensorsService.getSensorId(roomId, this.sensorTypeId);
    }

    public Map<LocalDateTime, Double> getInstantsOutOfComfortTemperature(LocalDate startDate, LocalDate endDate) {
        List<ReadingDTO> roomReadingsDTO = this.sensorsService.getRoomReadingsDTO(startDate, endDate, this.roomSensorId);
        return mapInstantsOutOfComfortTemp = sensorsService.getInstantsOutOfComfortTemperature(comfortTemp, roomReadingsDTO, option);
    }

    public List<LocalDateTime> getInstantListOutOfComfortLevel (){
        listOfInstantsOutOfComfortTemp = sensorsService.getInstantListOutOfComfortLevel(mapInstantsOutOfComfortTemp);
        Collections.sort(listOfInstantsOutOfComfortTemp);
        return listOfInstantsOutOfComfortTemp;
    }

    //passa a lista de instantes localdatetime para localdate
    public List<LocalDate> getDatesListOutOfComfortLevel() {
        List<LocalDate> datesListOutOfComfortLevel = new ArrayList<>();

        for (LocalDateTime localDateTime : this.getInstantListOutOfComfortLevel()) {
            datesListOutOfComfortLevel.add(localDateTime.toLocalDate());
        }
        return datesListOutOfComfortLevel;
    }

    //chamar este metodo abaixo antes de imprimir "os instantes calculados foram"
    public boolean instantListIsEmpty() {
        return this.getInstantListOutOfComfortLevel().isEmpty();
    }

    public void getDaysNotConsideredInCalculation(LocalDate startDate, LocalDate endDate) {
        for (LocalDate dateIterator = startDate; !dateIterator.isAfter(endDate); dateIterator = dateIterator.plusDays(1)) {
            if (!this.getDatesListOutOfComfortLevel().contains(dateIterator)) {
                this.daysNotConsidered.add(dateIterator);
            }
        }
    }

    ////agora chamo este metodo acima que grava a lista aqui e faço to string para mostrar ao user os dias onde nao foi possivel calcular instantes

    public boolean daysNotConsideredListIsEmpty() {
        return this.daysNotConsidered.isEmpty();
    }

    public List<LocalDate> getDaysNotConsidered() {
        return this.daysNotConsidered;
    }

    //abaixo verifico se é vazia a lista de dias nao considerados para ver se os imprimo


    public boolean existsDaysWithoutComfortTemp (){
        return sensorsService.existsDaysWithoutComfortTemp(comfortTemp);
    }

    public List<LocalDate> getDaysWithoutComfortTemp (){
        return sensorsService.getDaysWithoutComfortTemp(comfortTemp);
    }


    public boolean existsReadingsHouseAreaAndRoom(LocalDate startDate, LocalDate endDate) {
        return sensorsService.existReadingsHouseAreaAndRoom(this.roomId, this.sensorTypeId, this.geoAreaId, startDate, endDate);
    }

}
