package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class AttachRoomToHouseGridController {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;
    private HouseGrid mGridToBeUsed;
    private Room mRoomToBeAttached;

    /**
     * Constructor.
     *
     * @param listOfHouseGrids HouseGridList attribute.
     * @param listOfRooms      RoomList attribute.
     */
    public AttachRoomToHouseGridController(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        mListOfHouseGrids = listOfHouseGrids;
        mListOfRooms = listOfRooms;
    }

    /**
     * Method that checks if the house grid's list is empty.
     * @return True or false.
     */
    public boolean isHouseGridListEmpty() {
        return mListOfHouseGrids.isHouseGridListEmpty();
    }

    /**
     * Method that asks for the list of house grids.
     * @return List of house grids.
     */
    public String getHouseGridListToString() {
        return mListOfHouseGrids.getHouseGridListToString();
    }

    /**
     * Method that asks for the size of the list of house grids.
     *
     * @return Size of the list.
     */
    public int getHouseGridListSize() {
        return mListOfHouseGrids.getmHouseGridsList().size();
    }

    /**
     * Method that asks for the house grid in a specific position in the list.
     * @param position Specifies the position of the house grid in the list.
     * @return The respective house grid.
     */
    public HouseGrid getHouseGridFromTheList(int position){
        return mListOfHouseGrids.getHouseGridByPosition(position);
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
     * @return True or false.
     */
    public boolean isRoomListEmpty() {
        return mListOfRooms.isEmpty();
    }

    /**
     * Method that asks for the list of rooms from the class RoomList.
     * @return List of house grids.
     */
    public String getRoomListContent() {
        return mListOfRooms.getRoomListContent();
    }

    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int getRoomListSize() {
        return mListOfRooms.getRoomList().size();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromTheList(int position) {
        return mListOfRooms.getRoomFromPosition(position);
    }

    /**
     * Method that sets the attribute mGridToBeUsed as the house grid selected.
     *
     * @param gridSelected Grid to be used in the set.
     */
    public void setGridToBeUsed(HouseGrid gridSelected) {
        this.mGridToBeUsed = gridSelected;
    }

    /**
     * Method that sets the attribute mRoomToBeAttached as the room selected.
     *
     * @param roomSelected Room to be used in the set.
     */
    public void setRoomToBeAttached(Room roomSelected) {
        this.mRoomToBeAttached = roomSelected;
    }

    /**
     * Method that asks if the room isn't already in the chosen grid.
     * @return True or false.
     */
    public boolean checkIfTheChosenRoomIsAlreadyInTheChosenGrid() {
        return mListOfHouseGrids.checkIfRoomIsAlreadyInHouseGrid(mGridToBeUsed, mRoomToBeAttached);
    }

    /**
     * Method that asks for the grid where the room might already be connected.
     *
     * @return Grid where the room is is connected to.
     */
    public HouseGrid getTheGridWhereTheRoomIsConnected() {
        return mListOfHouseGrids.getTheGridWhereTheRoomIsConnected(mRoomToBeAttached);
    }

    /**
     * Method that asks the class HouseGridList to detach the specified room in the specified house grid via class HouseGrid.
     */
    public void detachRoomFromTheHouseGrid(HouseGrid grid) {
        mListOfHouseGrids.detachRoomInASpecificHouseGridInTheList(grid, mRoomToBeAttached);
    }

    /**
     * Method that asks the class HouseGridList to attach the specified room in the specified house grid via class HouseGrid.
     */
    public void attachRoomInTheHouseGrid() {
        mListOfHouseGrids.attachRoomInASpecificHouseGridInTheList(mGridToBeUsed, mRoomToBeAttached);
    }
}