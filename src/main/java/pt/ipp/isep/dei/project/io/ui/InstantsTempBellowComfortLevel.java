package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.instantstempbellowcomfortlevelcontroller.InstantsTempOutOfComfortLevelController;
import pt.ipp.isep.dei.project.services.GeographicalAreaService;
import pt.ipp.isep.dei.project.services.HouseService;
import pt.ipp.isep.dei.project.services.RoomService;
import pt.ipp.isep.dei.project.services.SensorsService;

public class InstantsTempBellowComfortLevel {

    private InstantsTempOutOfComfortLevelController controller;

    public InstantsTempBellowComfortLevel(GeographicalAreaService geographicalAreaService, HouseService houseService, SensorsService sensorsService, RoomService roomService) {
        controller = new InstantsTempOutOfComfortLevelController(geographicalAreaService, houseService, sensorsService, roomService);
    }

    public void run(int option) {
        String room = InputValidator.getString("What is the room you want to get the list?");
        //mostrar lista de quartos disponíveis
    }


}
