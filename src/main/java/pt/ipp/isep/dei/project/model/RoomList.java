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
     * Method that adds a new room to the list of rooms
     * @param room the new room that i want to add
     * @return true if it adds, false if it doesn't add, because it already contains it
     */
    public boolean addRoomToRoomList(Room room) {
        if (!(mList.contains(room))) {
            mList.add(room);
            return true;
        }
        return false;
    }
}
