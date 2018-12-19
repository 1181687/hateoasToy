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
     *
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

    public String getDisplayRoomList() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        String displayOfTheRoom = mList.get(0).getRoomDisplay();
        for (int i = 0; i < mList.size(); i++) {
            content.append(numberInTheList + "- ");
            content.append(displayOfTheRoom);
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    public String getDisplayOfTheChosenRoom(int position) {
        StringBuilder content = new StringBuilder();
        content.append("1 - Name: " + mList.get(position).getmName());
        content.append("\n");
        content.append("2 - House Floor: " + mList.get(position).getmHouseFloor());
        content.append("\n");
        content.append("3 - Dimensions - Height: " + mList.get(position).getmDimensions().getmHeight());
        content.append("\n");
        content.append("4 - Dimensions - Length: " + mList.get(position).getmDimensions().getmLength());
        content.append("\n");
        content.append("5 - Dimensions - Width: " + mList.get(position).getmDimensions().getmWidth());
        content.append("\n");
        return content.toString();
    }

    public void setRoomName(int chosenRoom, String change) {
        mList.get(chosenRoom).setmName(change);
    }

    public void setRoomFloor(int chosenRoom, int change) {
            mList.get(chosenRoom).setmHouseFloor(change);
        }

    public void setRoomDimensions(int chosenRoom, int chosenFeature, double change) {
        if (chosenFeature == 3) {
            mList.get(chosenRoom).getmDimensions().setmHeight(change);
        } else mList.get(chosenRoom).getmDimensions().getmHeight();
        if (chosenFeature == 4) {
            mList.get(chosenRoom).getmDimensions().setmLength(change);
        } else mList.get(chosenRoom).getmDimensions().getmLength();
        if (chosenFeature == 5) {
            mList.get(chosenRoom).getmDimensions().setmWidth(change);
        } else mList.get(chosenRoom).getmDimensions().getmWidth();
    }
}