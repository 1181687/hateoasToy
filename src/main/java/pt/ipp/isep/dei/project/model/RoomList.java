package pt.ipp.isep.dei.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomList {
    private List<Room> mRoomList = new ArrayList<>();

    public RoomList() {
    }

    /**
     * Get method.
     *
     * @return mRoomList.
     */
    public List<Room> getmRoomList() {
        return mRoomList;
    }

    /**
     * Method that get the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromASpecificPositionInTheList(int position) {
        return mRoomList.get(position);
    }

    /**
     * Method that adds a room to the list of rooms
     *
     * @param room the room to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the room is null
     */
    public boolean addRoomToRoomList(Room room) {
        if (room == null) {
            return false;
        }
        if (!(mRoomList.contains(room))) {
            mRoomList.add(room);
            return true;
        }
        return false;
    }

    /**
     * method that creates a newroom
     *
     * @param name       given name to the new room
     * @param housefloor given housefloor number to the new room
     * @param height     given height number to the new room
     * @param length     given length number to the new room
     * @param width      given width number to the new room
     * @return the new room
     */
    public Room newRoom(String name, int housefloor, double height, double length, double width) {
        Dimensions newDimension = new Dimensions(height, length, width);
        SensorList sensorList = new SensorList();
        return new Room(name, housefloor, newDimension, sensorList);
    }

    public String getDisplayRoomList() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (int i = 0; i < mRoomList.size(); i++) {
            String displayOfTheRoom = mRoomList.get(i).getRoomDisplay();
            content.append(numberInTheList + "- ");
            content.append(displayOfTheRoom);
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    public boolean checkIfRoomListIsEmpty() {
        return mRoomList.isEmpty();
    }

    public int listSize() {
        return mRoomList.size();
    }

    public String getDisplayOfTheChosenRoom(int position) {
        StringBuilder content = new StringBuilder();
        content.append("1 - Name: " + mRoomList.get(position).getmName());
        content.append("\n");
        content.append("2 - House Floor: " + mRoomList.get(position).getmHouseFloor());
        content.append("\n");
        content.append("3 - Dimensions - Height: " + mRoomList.get(position).getmDimensions().getmHeight());
        content.append("\n");
        content.append("4 - Dimensions - Length: " + mRoomList.get(position).getmDimensions().getmLength());
        content.append("\n");
        content.append("5 - Dimensions - Width: " + mRoomList.get(position).getmDimensions().getmWidth());
        content.append("\n");
        return content.toString();
    }

    public void setRoomNameInList(int chosenRoom, String change) {
        mRoomList.get(chosenRoom).setmName(change);
    }

    public void setRoomFloorInList(int chosenRoom, int change) {
        mRoomList.get(chosenRoom).setmHouseFloor(change);
    }

    public void setRoomDimensionsInList(int chosenRoom, int chosenFeature, double change) {
        if (chosenFeature == 3) {
            mRoomList.get(chosenRoom).getmDimensions().setmHeight(change);
        } else mRoomList.get(chosenRoom).getmDimensions().getmHeight();
        if (chosenFeature == 4) {
            mRoomList.get(chosenRoom).getmDimensions().setmLength(change);
        } else mRoomList.get(chosenRoom).getmDimensions().getmLength();
        if (chosenFeature == 5) {
            mRoomList.get(chosenRoom).getmDimensions().setmWidth(change);
        } else mRoomList.get(chosenRoom).getmDimensions().getmWidth();
    }

    public Room getRoomByName(String name) {

        for (Room room : mRoomList) {
            if (room.getmName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    public String getNameOfTheChosenRoomInSpecificPos(int position) {
        return mRoomList.get(position).getmName();
    }

    /**
     * @param name of Room
     * @param type of Sensor (temperature)
     * @param date any given day
     * @return the maximum temperature in a Rooom in a given day
     */
    public double getMaximumTemperatureInARoomInAGivenDay(String name, SensorType type, Date date) {
        return getRoomByName(name).getMaximumMeasurementInAGivenDay(type, date);
    }

}