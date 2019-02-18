package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class DetachRoomFromHouseGridController {
    private House mHouse;
    private RoomList mListOfRooms;
    private HouseGrid mhouseGrid;

    public DetachRoomFromHouseGridController(House house, RoomList listOfRooms) {
        this.mHouse = house;
        this.mListOfRooms = listOfRooms;
    }

    public String getListOfHouseGridsAttachedToHouseGrid() {
        return mHouse.getHouseGridListToString();
    }

    public HouseGrid getHouseGridFromTheList(int position) {
        return mhouseGrid = mHouse.getHouseGridByPosition(position);
    }

    public String getRoomListContent() {
        return mListOfRooms.getRoomListContent();
    }

    public String getListOfRoomsInACertainHouseGrid(int position) {
        return mHouse.getRoomsInTheHouseGrid(position);
    }

    /**
     * TODO - separar este método no model. Gabi_X
     *
     * @param position
     * @return
     */
    public Room getRoomFromTheListOfRoomByAPosition(int position) {
        return mhouseGrid.getRoomList().getRoomFromPosition(position);
    }

    public boolean detachRoomFromGridList(HouseGrid houseGrid, Room roomSelected) {
        return mHouse.detachRoomInASpecificHouseGridInTheList(houseGrid, roomSelected);
    }

    public int getGridListSize() {
        return mHouse.getHouseGridListSize();
    }

}
