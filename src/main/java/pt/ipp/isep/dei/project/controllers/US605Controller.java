package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.TipoSensor;

public class US605Controller {

    private House mHouse;
    private TipoSensor mType;

    public US605Controller(House house, TipoSensor type) {
        this.mHouse = house;
        this.mType = type;
    }

    public double getCurrentTemperatureRoom(String name) {
        return this.mHouse.getListOfRoom().getRoomByName(name).getSensorList().getUltimoRegistoDeUmTipoDeSensor(mType);
    }

    public String getDisplayRoomList() {
        return this.mHouse.getListOfRoom().getDisplayRoomList();
    }

    public int lengthOfRoomList() {
        return this.mHouse.getListOfRoom().listSize();
    }

    public String getNameOfTheChosenRoomInSpecificPos(int posicion) {
        return this.mHouse.getListOfRoom().getNameOfTheChosenRoomInSpecificPos(posicion);
    }

    public Room getRoomFromASpecificPositionInTheList(int position) {
        return this.mHouse.getListOfRoom().getRoomFromASpecificPositionInTheList(position);
    }
}
