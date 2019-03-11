package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;

public class DetachRoomFromHouseGridController {
    private House house;
    private RoomList roomList;
    private HouseGrid houseGrid;

    public DetachRoomFromHouseGridController(House house, RoomList listOfRooms) {
        this.house = house;
        this.roomList = listOfRooms;
    }

    public String getListOfHouseGridsAttachedToHouseGrid() {
        return house.getHouseGridListToString();
    }

    public HouseGrid getHouseGridFromTheList(int position) {
        houseGrid = house.getHouseGridByPosition(position);
        return houseGrid;
    }

    public String getRoomListContent() {
        return roomList.getRoomListContent();
    }

    public String getListOfRoomsInACertainHouseGrid(int position) {
        return house.getRoomsInTheHouseGrid(position);
    }

    public Room getRoomFromTheListOfRoomByAPosition(int position) {
        return houseGrid.getRoomFromTheListOfRoomByAPosition(position);
    }

    public boolean detachRoomFromGridList(HouseGrid houseGrid, Room roomSelected) {
        return house.detachRoomInASpecificHouseGridInTheList(houseGrid, roomSelected);
    }

    public int getGridListSize() {
        return house.getHouseGridListSize();
    }

}
