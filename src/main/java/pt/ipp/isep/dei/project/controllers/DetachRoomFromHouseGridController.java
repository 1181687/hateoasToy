package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class DetachRoomFromHouseGridController {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;

    public DetachRoomFromHouseGridController(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        this.mListOfHouseGrids = listOfHouseGrids;
        this.mListOfRooms = listOfRooms;
    }

    public String getListOfHouseGridsAttachedToHouseGrid() {
        return mListOfHouseGrids.getHouseGridListToString();
    }

    public HouseGrid getHouseGridFromTheList(int position) {
        return mListOfHouseGrids.getHouseGridByPosition(position);
    }

    public String getRoomListContent() {
        return mListOfRooms.getRoomListContent();
    }

    public String getListOfRoomsInACertainHouseGrid(int position) {
        return mListOfHouseGrids.getRoomsInTheHouseGrid(position);
    }

    public Room getRoomFromTheListOfRoomByAPosition(int position) {
        return mListOfRooms.getRoomFromPosition(position);
    }

    public boolean detachRoomFromGridList(HouseGrid houseGrid, Room roomSelected) {
        return mListOfHouseGrids.detachRoomInASpecificHouseGridInTheList(houseGrid, roomSelected);
    }

    public int getGridListSize() {
        return mListOfHouseGrids.getmHouseGridsList().size();
    }

}
