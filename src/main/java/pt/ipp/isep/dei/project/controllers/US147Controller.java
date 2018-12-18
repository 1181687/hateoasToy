package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.Room;
import pt.ipp.isep.dei.project.model.RoomList;

import java.util.List;

public class US147Controller {
    private HouseGridList mListOfHouseGrids;
    private RoomList mListOfRooms;

    public US147Controller(HouseGridList listOfHouseGrids, RoomList listOfRooms) {
        mListOfHouseGrids = listOfHouseGrids;
        mListOfRooms = listOfRooms;
    }

    /**
     * Method that asks for the list of house grids from the class HouseGridList.
     * @return List of house grids.
     */
    public List<HouseGrid> getmListOfHouseGrids(){
        return mListOfHouseGrids.getmList();
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
     * @return List of rooms.
     */
    public List<Room> getmListOfRooms(){
        return mListOfRooms.getmList();
    }

    /**
     * Method that asks for the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromTheList(int position) {
        return mListOfRooms.getRoomFromASpecificPositionInTheList(position);
    }
}