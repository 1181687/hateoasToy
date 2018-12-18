package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private List<Room> mList = new ArrayList<>();

    public RoomList() {
    }

    public List<Room> getmList() {
        return mList;
    }


    /**
     * Method that get the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromASpecificPositionInTheList(int position) {
        return mList.get(position);
    }


    /**
     * Method that asks to the class Room if a room isn't in a house grid.
     *
     * @param room Specified room in the list.
     * @return True or false.
     */
    public boolean checkIfARoomInAListIsntInAHouseGrid(Room room) {
        int index = mList.indexOf(room);
        return mList.get(index).checkIfTheRoomIsntInAHouseGrid();
    }

    /**
     * Method that asks the class Room to change it's attribute to the house grid selected.
     *
     * @param roomSelected      Specified room in the list.
     * @param houseGridSelected Specified house grid.
     */
    public void changeTheAttributeHouseGridInTheSpecifiedRoomInTheList(Room roomSelected, HouseGrid houseGridSelected) {
        int index = mList.indexOf(roomSelected);
        mList.get(index).changeTheAttributeHouseGrid(houseGridSelected);
    }
}