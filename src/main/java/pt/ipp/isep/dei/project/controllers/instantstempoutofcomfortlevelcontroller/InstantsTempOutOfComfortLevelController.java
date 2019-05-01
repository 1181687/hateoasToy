package pt.ipp.isep.dei.project.controllers.instantstempoutofcomfortlevelcontroller;

import pt.ipp.isep.dei.project.model.Location;
import pt.ipp.isep.dei.project.model.Reading;
import pt.ipp.isep.dei.project.model.geographicalarea.GeoAreaId;
import pt.ipp.isep.dei.project.model.house.Address;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomId;
import pt.ipp.isep.dei.project.model.sensor.SensorTypeId;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private Map <LocalDateTime, Double> instantsOutOfComfortTemp;
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

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


}
