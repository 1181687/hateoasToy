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
        StringBuilder conteudo = new StringBuilder();
        for (int i = 1; i <= mList.size(); i++) {
            conteudo.append(i + " - Name: " + mList.get(i - 1).getmName());
            conteudo.append(", House Floor: " + mList.get(i - 1).getmHouseFloor());
            conteudo.append(", Dimensions - Height: " + mList.get(i - 1).getmDimensions().getmHeight());
            conteudo.append(", Dimensions - Length: " + mList.get(i - 1).getmDimensions().getmLength());
            conteudo.append(", Dimensions - Width: " + mList.get(i - 1).getmDimensions().getmWidth());
            conteudo.append("\n");
        }
        return conteudo.toString();
    }

    public String getDisplayOfTheChosenRoom(int position) {
        StringBuilder conteudo = new StringBuilder();
        conteudo.append("1 - Name: " + mList.get(position).getmName());
        conteudo.append("\n");
        conteudo.append("2 - House Floor: " + mList.get(position).getmHouseFloor());
        conteudo.append("\n");
        conteudo.append("3 - Dimensions - Height: " + mList.get(position).getmDimensions().getmHeight());
        conteudo.append("\n");
        conteudo.append("4 - Dimensions - Length: " + mList.get(position).getmDimensions().getmLength());
        conteudo.append("\n");
        conteudo.append("5 - Dimensions - Width: " + mList.get(position).getmDimensions().getmWidth());
        conteudo.append("\n");
        return conteudo.toString();
    }
}
