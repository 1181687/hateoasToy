package pt.ipp.isep.dei.project.controllers.instantstempoutofcomfortlevelcontroller;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.geographicalarea.GeographicalArea;
import pt.ipp.isep.dei.project.model.house.*;
import pt.ipp.isep.dei.project.model.sensor.SensorId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InstantsTempOutOfComfortLevelController {

    private SensorsService sensorsService;
    private RoomService roomService;
    private SensorTypeId sensorTypeId = new SensorTypeId("Temperature");
    private Address houseId;
    private RoomId roomId;
    private Location location;
    private GeoAreaId geoAreaId;
    private Map<LocalDate, List<Double>> comfortTemp;
    private Map<LocalDateTime, Double> instantsOutOfComfortTemp;
    private List<Reading> roomReadings;
    private int category;
    private int option;
    private List<LocalDateTime> listOfInstantsOutOfComfortTemp;


    /**
     * Constructor.
     *
     * @param sensorsService Service to be used.
     */
    public InstantsTempOutOfComfortLevelController(SensorsService sensorsService, RoomService roomService) {
        this.sensorsService = sensorsService;
        this.roomService = roomService;
    }

    //save category from UI
    public void setCategory(int category) {
        this.category = category;
    }

    //save option from UI
    public void setOption(int option) {
        this.option = option;
    }

    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for(Room room: roomService.getAllRooms()){
            roomDTOList.add(RoomMapper.mapToDTO(room));
        }
        return roomDTOList;
    }

    public boolean isRoomListEmpty (){
        return roomService.isListOfRoomsEmpty();
    }

    public void setRoomId (String roomId){
        this.roomId= new RoomId(roomId);
    }

    public boolean existSensors (){
        return sensorsService.existSensors(roomId, sensorTypeId);
    }

    public Location getHouseLocation (){
        return location = houseId.getLocation();
    }

    public GeoAreaId getGeoAreaId (){
        GeographicalArea geoArea = houseId.getInsertedGeoArea();
        return geoAreaId = geoArea.getId();
    }

    public Map<LocalDate, List<Double>> getComfortTemperature (LocalDate startDate, LocalDate endDate){
        return comfortTemp = sensorsService.getComfortTemperature(location, geoAreaId, sensorTypeId, startDate, endDate, category);
    }

    public SensorId getSensorID (){
        return sensorsService.getSensorId(roomId);
    }

    







}
