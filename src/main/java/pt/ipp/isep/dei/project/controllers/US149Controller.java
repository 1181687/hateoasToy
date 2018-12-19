package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.List;

public class US149Controller {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;

    public US149Controller(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        this.mListOfHouseGrids = listOfHouseGrids;
        this.mListOfRooms = listOfRooms;
    }

    public String getListContent() {
        return mListOfHouseGrids.getContentOfHouseGrid();
    }

    public HouseGrid getHouseGridFromTheList(int position) {
        return mListOfHouseGrids.getHouseGridFromASpecificPositionInTheList(position);
    }

    public List<Room> getListOfRooms() {
        return mListOfRooms.getmList();
    }

    public Room getRoomFromTheListOfRoomByAPosition(int position) {
        return mListOfRooms.getRoomFromASpecificPositionInTheList(position);
    }

    public void detachRoomFromGridList(HouseGrid houseGrid, Room roomSelected) {
        mListOfHouseGrids.detachRoomInASpecificHouseGridInTheList(houseGrid, roomSelected);
    }

    public int getNumberOfGridLists() {
        return mListOfHouseGrids.getmList().size();
    }

}
