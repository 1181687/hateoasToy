package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;

public class US108Controller {
    private RoomList mLista;

    public US108Controller(RoomList list) {
        this.mLista = list;
    }

    public String displayOfTheRoomList() {
        return mLista.getDisplayRoomList();
    }

    public String displayOfTheChosenRoom(int position) {
        return mLista.getDisplayOfTheChosenRoom(position);
    }

    public void changeNameOfTheRoom(int chosenRoom, String change) {
        mLista.setRoomName(chosenRoom, change);
    }

    public void changeHouseFloorOfTheRoom(int chosenRoom, int change) {
        mLista.setRoomFloor(chosenRoom, change);
    }

    public void changeDimensionsOfTheRoom(int chosenRoom, int chosenFeature, double change) {
        mLista.setRoomDimensions(chosenRoom, chosenFeature, change);
    }
}
