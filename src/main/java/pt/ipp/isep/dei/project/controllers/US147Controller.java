package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

public class US147Controller {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;

    public US147Controller(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        mListOfHouseGrids = listOfHouseGrids;
        mListOfRooms = listOfRooms;
    }

    /**
     * Method that checks if the house grid's list is empty.
     *
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
     * Method that asks if the room isn't already in the chosen grid.
     * @param chosenGrid Specific house grid to search throughout the list of grids.
     * @param chosenRoom Specific room to search throughout the list of room of the grid.
     * @return True or false.
     */
    public boolean checkIfTheChosenRoomIsAlreadyInTheChosenGrid(HouseGrid chosenGrid, Room chosenRoom) {
        return mListOfHouseGrids.checkIfARoomIsAlreadyInAHouseGridOfTheList(chosenGrid, chosenRoom);
    }

    /**
     * Method that asks the class HouseGridList to attach the specified room in the specified house grid via class HouseGrid.
     * @param houseGridSelected Specific house grid to search throughout the list of grids.
     * @param roomSelected Chosen room to attach to the house grid.
     */
    public void attachRoomInTheHouseGrid(HouseGrid houseGridSelected, Room roomSelected) {
        mListOfHouseGrids.attachRoomInASpecificHouseGridInTheList(houseGridSelected, roomSelected);
    }
}