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
    public boolean checkIfHouseGridListIsEmpty() {
        return mListOfHouseGrids.checkIfHouseGridListIsEmpty();
    }

    /**
     * Method that asks for the list of house grids.
     * @return List of house grids.
     */
    public String listAllTheHouseGridsInTheList() {
        return mListOfHouseGrids.getContentOfTheHouseGridsInTheList();
    }

    /**
     * Method that asks for the size of the list of house grids.
     *
     * @return Size of the list.
     */
    public int houseGridListLength() {
        return mListOfHouseGrids.getmHouseGridsList().size();
    }

    /**
     * Method that asks for the house grid in a specific position in the list.
     * @param position Specifies the position of the house grid in the list.
     * @return The respective house grid.
     */
    public HouseGrid getHouseGridFromTheList(int position){
        return mListOfHouseGrids.getHouseGridFromASpecificPositionInTheList(position);
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
     * @return True or false.
     */
    public boolean checkIfRoomListIsEmpty() {
        return mListOfRooms.checkIfRoomListIsEmpty();
    }

    /**
     * Method that asks for the list of rooms from the class RoomList.
     * @return List of house grids.
     */
    public String listAllTheRoomsInTheList() {
        return mListOfRooms.getRoomListContent();
    }

    /**
     * Method that asks for the size of the list of rooms.
     *
     * @return Size of the list.
     */
    public int roomListLength() {
        return mListOfRooms.getmRoomList().size();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromTheList(int position) {
        return mListOfRooms.getRoomFromASpecificPosition(position);
    }

    /**
     * Method that sets the attribute mGridToBeUsed as the house grid selected.
     *
     * @param gridSelected Grid to be used in the set.
     */
    public void setmGridToBeUsed(HouseGrid gridSelected) {
        this.mGridToBeUsed = gridSelected;
    }

    /**
     * Method that sets the attribute mRoomToBeAttached as the room selected.
     *
     * @param roomSelected Room to be used in the set.
     */
    public void setmRoomToBeAttached(Room roomSelected) {
        this.mRoomToBeAttached = roomSelected;
    }

    /**
     * Method that asks if the room isn't already in the chosen grid.
     * @return True or false.
     */
    public boolean checkIfTheChosenRoomIsAlreadyInTheChosenGrid() {
        return mListOfHouseGrids.checkIfARoomIsAlreadyInAHouseGrid(mGridToBeUsed, mRoomToBeAttached);
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