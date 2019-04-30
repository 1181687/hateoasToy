package pt.ipp.isep.dei.project.controllers.instantstempbellowcomfortlevelcontroller;

import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

import java.util.List;

public class InstantsTempOutOfComfortLevelController {

    private SensorsService sensorsService;
    private HouseService houseService;
    private GeographicalAreaService geographicalAreaService;
    private RoomService roomService;


    //private List<Object> readingDTOs;


    /**
     * Constructor.
     *
     * @param sensorsService Service to be used.
     */
    public InstantsTempOutOfComfortLevelController(GeographicalAreaService geographicalAreaService, HouseService houseService, SensorsService sensorsService, RoomService roomService) {
        this.sensorsService = sensorsService;
        this.houseService = houseService;
        this.geographicalAreaService = geographicalAreaService;
        this.roomService = roomService;
    }

    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


}
