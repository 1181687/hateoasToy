package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;

public class GetListOfSensorsRoomController {

    private House mHouse;

    public GetListOfSensorsRoomController(House mHouse) {
        this.mHouse = mHouse;
    }

    public String getSensorsListContent(int position) {
        return this.mHouse.getSensorListContentOfARoom(position);
    }

    public boolean checkIfListIsEmpty (int position) {
        return this.mHouse.checkIfSensorListIsEmpty(position);
    }

}
