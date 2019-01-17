package pt.ipp.isep.dei.project.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private List<Room> mRoomList = new ArrayList<>();

    /**
     * Constructor.
     */
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
     * Method that gets the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromAPosition(int position) {
        return mRoomList.get(position);
    }


    /**
     * Method that adds a room to the list of rooms
     *
     * @param room the room to be added
     * @return true if it adds, false if it doesn't add, because it already contains it or the room is null
     */
    public boolean addRoom(Room room) {
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
     * method that creates a new room
     *
     * @param name       given name to the new room
     * @param housefloor given housefloor number to the new room
     * @param height     given height number to the new room
     * @param length     given length number to the new room
     * @param width      given width number to the new room
     * @return the new room
     */
    public Room newRoom(String name, int housefloor, double height, double length, double width) {
        if (checkIfNameAlreadyExists(name)) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        Dimensions newDimension = new Dimensions(height, length, width);
        return new Room(name, housefloor, newDimension);
    }

    /**
     * method that displays the List of Rooms
     *
     * @return Content of Room List
     */
    public String getRoomListContent() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (int i = 0; i < mRoomList.size(); i++) {
            String displayOfTheRoom = mRoomList.get(i).getRoomContent();
            //   content.append("\n");
            content.append(numberInTheList);
            content.append("- ");
            content.append(displayOfTheRoom);
            content.append("\n");
            numberInTheList++;
        }
        return content.toString();
    }

    /**
     * Method that checks if a Room List is Empty
     *
     * @return true if it is empty
     */
    public boolean checkIfRoomListIsEmpty() {
        return mRoomList.isEmpty();
    }

    /**
     * Method that gives us the size of the Room List
     *
     * @return Room List size
     */
    public int listSize() {
        return mRoomList.size();
    }

    /**
     * Method that displays a choosen Room (in a specific position) with its characteristics (name, house floor, height, length and width)
     *
     * @param position position of the Room in the Room List
     * @return Content of the Room
     */
    public String getChosenRoomContent(int position) {
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

    /**
     * Method that changes the name of a Room
     *
     * @param chosenRoom room that you want to change
     * @param changeName new name for the room
     */
    public void changeRoomName(int chosenRoom, String changeName) {
        mRoomList.get(chosenRoom).setmName(changeName);
    }

    /**
     * Method that changes the house floor of a Room
     *
     * @param chosenRoom       room that you want to change
     * @param changeHouseFloor new house floor for the room
     */
    public void setRoomFloor(int chosenRoom, int changeHouseFloor) {
        mRoomList.get(chosenRoom).setmHouseFloor(changeHouseFloor);
    }

    /**
     * Method that changes the dimensions of a Room
     *
     * @param chosenRoom      room that you want to change
     * @param chosenFeature   Dimension that you want to change (Height, Length, Width)
     * @param changeDimension New value (double) for the chosen dimension (Height, Length, Width)
     */
    public void setRoomDimensions(int chosenRoom, int chosenFeature, double changeDimension) {
        if (chosenFeature == 3) {
            mRoomList.get(chosenRoom).getmDimensions().setmHeight(changeDimension);
        } else mRoomList.get(chosenRoom).getmDimensions().getmHeight();
        if (chosenFeature == 4) {
            mRoomList.get(chosenRoom).getmDimensions().setmLength(changeDimension);
        } else mRoomList.get(chosenRoom).getmDimensions().getmLength();
        if (chosenFeature == 5) {
            mRoomList.get(chosenRoom).getmDimensions().setmWidth(changeDimension);
        } else mRoomList.get(chosenRoom).getmDimensions().getmWidth();
    }

    /**
     * Method that goes through the room list and shows the room by name if that name matches a room with the same name in the list
     *
     * @param name room name (string)
     * @return room
     */

    public Room getRoomByName(String name) {

        for (Room room : mRoomList) {
            if (room.getmName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Method that gets the name of the chosen room in a specific position in the room list
     *
     * @param position position of the chosen room in a List
     * @return if the List is empty, returns null. Else returns the name of room in that position
     */

    public String getRoomNameByPosition(int position) {
        if (mRoomList.isEmpty()) {
            return null;
        }
        return mRoomList.get(position).getmName();
    }

    /**
     * @param name of Room
     * @param type of Sensor (temperature)
     * @param date any given day
     * @return the maximum temperature in a specific Room in a given day
     */
    public double getMaximumTemperatureInARoomInAGivenDay(String name, SensorType type, LocalDate date) {
        return getRoomByName(name).getMaximumMeasurementInAGivenDay(type, date);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     *
     * @param name
     * @return boolean
     */
    public boolean checkIfNameAlreadyExists(String name) {

        for (int i = 0; i < mRoomList.size(); i++) {
            if (mRoomList.get(i).getmName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that displays the device list content of a Room
     *
     * @param position position of the room in the room list
     * @return list of devices of a room
     */
    public String getDeviceListContentByPosition(int position) {
        return mRoomList.get(position).getDeviceListContent();
    }

    /**
     * Method that checks if the Device List of the room is empty
     *
     * @param position chosen room
     */
    public boolean checkIfDeviceListIsEmpty(int position) {
        return mRoomList.get(position).checkIfDeviceListIsEmpty();
    }

    /**
     * method that checks if there are no devices in the RoomList
     *
     * @return true if there aren't devices. False if there are devices
     */
    public boolean checkIfThereAreNoDevices() {

        for (int i = 0; i < mRoomList.size(); i++) {
            if (!checkIfDeviceListIsEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that displays the sensor list content of a Room
     *
     * @param position
     * @return
     */
    public String getSensorListContentOfARoom(int position) {
        return mRoomList.get(position).getSensorListContent();
    }

    /**
     * method that check if the sensor list of the room is empty
     *
     * @param position
     */
    public boolean checkIfSensorListIsEmpty(int position) {
        return mRoomList.get(position).checkIfSensorListIsEmpty();
    }

    /**
     * method that gets a list of devices in all rooms of roomlist
     *
     * @return DeviceList
     */
    public DeviceList getAllDevicesList() {
        DeviceList roomDeviceList;
        DeviceList allDeviceList = new DeviceList();
        Device dev;

        for (int i = 0; i < mRoomList.size(); i++) {
            roomDeviceList = mRoomList.get(i).getmDeviceList();
            for (int j = 0; j < roomDeviceList.getLength(); j++) {
                dev = roomDeviceList.getDeviceByPosition(j);
                allDeviceList.addDevice(dev);
            }
        }
        return allDeviceList;
    }

    public String getRoomListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= mRoomList.size(); i++) {
            content.append(i + " - Name: " + mRoomList.get(i - 1).getmName());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * TO DO - LUÍS
     *
     * @return
     */
    public DeviceList getAllDevicesOfAType(String type) {
        DeviceList listWithDevicesOfAType = new DeviceList();
        for (Room room : mRoomList) {
            listWithDevicesOfAType.getmDeviceList().addAll(room.getAllDevicesOfAType(type).getmDeviceList());
        }
        return listWithDevicesOfAType;
    }
}