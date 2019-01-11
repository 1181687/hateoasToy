package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.Room;

public class GetListOfSensorsRoomController {

    private Room mRoom;

    public String getContentOfTheSensorList() {
        return mRoom.getContentOfSensorsList();
    }

    public boolean checkIfListIsEmpty () {
        return mRoom.checkIfRoomListIsEmpty();
    }

}
