package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.Room;
import pt.ipp.isep.dei.project.model.house.RoomList;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;

public class AttachRoomToHouseGridController {
    private House house;
    private RoomList roomList;
    private HouseGrid gridToBeUsed;
    private Room roomToBeAttached;

    /**
     * Constructor.
     *
     * @param house       House attribute.
     * @param listOfRooms RoomList attribute.
     */
    public AttachRoomToHouseGridController(House house, RoomList listOfRooms) {
        this.house = house;
        this.roomList = listOfRooms;
    }

    /**
     * Method that checks if the housegrid grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }

    /**
     * Method that asks for the list of housegrid grids.
     *
     * @return List of housegrid grids.
     */
    public String getHouseGridListToString() {
        return house.getHouseGridListToString();
    }

    /**
     * Method that asks for the size of the list of housegrid grids.
     *
     * @return Size of the list.
     */
    public int getHouseGridListSize() {
        return house.getHouseGridListSize();
    }

    /**
     * Method that asks for the housegrid grid in a specific position in the list.
     *
     * @param position Specifies the position of the housegrid grid in the list.
     * @return The respective housegrid grid.
     */
    public HouseGrid getHouseGridFromTheList(int position) {
        return house.getHouseGridByPosition(position);
    }

    /**
     * Method that checks if the housegrid grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isRoomListEmpty() {
        return roomList.isEmpty();
    }

    /**
     * Method that asks for the list of rooms from the class RoomList.
     *
     * @return List of housegrid grids.
     */
    public String getRoomListContent() {
        return roomList.getRoomListContent();
    }

    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int getRoomListSize() {
        return roomList.getListOfRooms().size();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromTheList(int position) {
        return roomList.getRoomFromPosition(position);
    }

    /**
     * Method that sets the attribute gridToBeUsed as the housegrid grid selected.
     *
     * @param gridSelected Grid to be used in the set.
     */
    public void setGridToBeUsed(HouseGrid gridSelected) {
        this.gridToBeUsed = gridSelected;
    }

    /**
     * Method that sets the attribute roomToBeAttached as the room selected.
     *
     * @param roomSelected Room to be used in the set.
     */
    public void setRoomToBeAttached(Room roomSelected) {
        this.roomToBeAttached = roomSelected;
    }

    /**
     * Method that asks if the room isn't already in the chosen grid.
     *
     * @return True or false.
     */
    public boolean checkIfTheChosenRoomIsAlreadyInTheChosenGrid() {
        return house.checkIfRoomIsAlreadyInHouseGrid(gridToBeUsed, roomToBeAttached);
    }

    /**
     * Method that asks for the grid where the room might already be connected.
     *
     * @return Grid where the room is is connected to.
     */
    public HouseGrid getTheGridWhereTheRoomIsConnected() {
        return house.getTheGridWhereTheRoomIsConnected(roomToBeAttached);
    }

    /**
     * Method that detach the specified room in the specified housegrid grid via class housegrid.
     */
    public void detachRoomFromTheHouseGrid(HouseGrid grid) {
        house.detachRoomInASpecificHouseGridInTheList(grid, roomToBeAttached);
    }

    /**
     * Method that attach the specified room in the specified housegrid grid via class housegrid.
     */
    public void attachRoomInTheHouseGrid() {
        house.attachRoomInASpecificHouseGridInTheList(gridToBeUsed, roomToBeAttached);
    }
}