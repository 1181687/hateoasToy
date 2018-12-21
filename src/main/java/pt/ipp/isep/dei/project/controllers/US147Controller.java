package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class US147Controller {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;
    private HouseGrid mGridToBeUsed;
    private Room mRoomToBeAttached;

    public US147Controller(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
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
     * Method that asks for the list of house grids from the class HouseGridList.
     * @return List of house grids.
     */
    public String listAllTheHouseGridsInTheList() {
        return mListOfHouseGrids.displayOfTheContentOfTheHouseGridsInTheList();
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
     * Method that asks for the list of rooms from the class RoomList.
     * @return List of house grids.
     */
    public String listAllTheRoomsInTheList() {
        return mListOfRooms.getDisplayRoomList();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromTheList(int position) {
        return mListOfRooms.getRoomFromASpecificPositionInTheList(position);
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
        return mListOfHouseGrids.checkIfARoomIsAlreadyInAHouseGridOfTheList(mGridToBeUsed, mRoomToBeAttached);
    }

    /**
     * Method that asks the class HouseGridList to attach the specified room in the specified house grid via class HouseGrid.
     */
    public void attachRoomInTheHouseGrid() {
        mListOfHouseGrids.attachRoomInASpecificHouseGridInTheList(mGridToBeUsed, mRoomToBeAttached);
    }
}