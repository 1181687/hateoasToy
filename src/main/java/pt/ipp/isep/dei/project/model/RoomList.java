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
    public List<Room> getRoomList() {
        return mRoomList;
    }

    /**
     * Method that gets the room in a specific position in the list.
     *
     * @param position Specifies the position of the room in the list.
     * @return The respective room.
     */
    public Room getRoomFromPosition(int position) {
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
        if (isNameExistant(name)) {
            throw new RuntimeException("Name already exists. Please write a new one.");
        }
        Dimension newDimension = new Dimension(height, length, width);
        return new Room(name, housefloor, newDimension);
    }

    /**
     * method that displays the List of Rooms
     * @return Content of Room List
     */
    public String getRoomListContent() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (int i = 0; i < mRoomList.size(); i++) {
            String displayOfTheRoom = mRoomList.get(i).getRoomToString();
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
     * @return true if it is empty
     */
    public boolean isEmpty() {
        return mRoomList.isEmpty();
    }

    /**
     * Method that gives us the size of the Room List
     * @return Room List size
     */
    public int getLength() {
        return mRoomList.size();
    }

    /**
     * Method that displays a choosen Room (in a specific position) with its characteristics (name, house floor, height, length and width)
     * @param position position of the Room in the Room List
     * @return Content of the Room
     */
    public String getChosenRoomToString(int position) {
        StringBuilder content = new StringBuilder();
        content.append("1 - Name: " + mRoomList.get(position).getName());
        content.append("\n");
        content.append("2 - House Floor: " + mRoomList.get(position).getHouseFloor());
        content.append("\n");
        content.append("3 - Dimension - Height: " + mRoomList.get(position).getDimension().getHeight());
        content.append("\n");
        content.append("4 - Dimension - Length: " + mRoomList.get(position).getDimension().getLength());
        content.append("\n");
        content.append("5 - Dimension - Width: " + mRoomList.get(position).getDimension().getWidth());
        content.append("\n");
        return content.toString();
    }

    /**
     * Method that changes the name of a Room
     * @param chosenRoom room that you want to change
     * @param changeName new name for the room
     */
    public void changeRoomName(int chosenRoom, String changeName) {
        mRoomList.get(chosenRoom).setName(changeName);
    }

    /**
     * Method that changes the house floor of a Room
     * @param chosenRoom       room that you want to change
     * @param changeHouseFloor new house floor for the room
     */
    public void setRoomFloor(int chosenRoom, int changeHouseFloor) {
        mRoomList.get(chosenRoom).setHouseFloor(changeHouseFloor);
    }

    /**
     * Method that changes the dimensions of a Room
     * @param chosenRoom      room that you want to change
     * @param chosenFeature   Dimension that you want to change (Height, Length, Width)
     * @param changeDimension New value (double) for the chosen dimension (Height, Length, Width)
     */
    public void setRoomDimensions(int chosenRoom, int chosenFeature, double changeDimension) {
        if (chosenFeature == 3) {
            mRoomList.get(chosenRoom).getDimension().setHeight(changeDimension);
        } else mRoomList.get(chosenRoom).getDimension().getHeight();
        if (chosenFeature == 4) {
            mRoomList.get(chosenRoom).getDimension().setLength(changeDimension);
        } else mRoomList.get(chosenRoom).getDimension().getLength();
        if (chosenFeature == 5) {
            mRoomList.get(chosenRoom).getDimension().setWidth(changeDimension);
        } else mRoomList.get(chosenRoom).getDimension().getWidth();
    }

    /**
     * Method that goes through the room list and shows the room by name if that name matches a room with the same name in the list
     * @param name room name (string)
     * @return room
     */

    public Room getRoomByName(String name) {

        for (Room room : mRoomList) {
            if (room.getName().equals(name)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Method that gets the name of the chosen room in a specific position in the room list
     * @param position position of the chosen room in a List
     * @return if the List is empty, returns null. Else returns the name of room in that position
     */

    public String getRoomNameByPosition(int position) {
        if (mRoomList.isEmpty()) {
            return null;
        }
        return mRoomList.get(position).getName();
    }

    /**
     * method that get the maximum temperature in a room in a given day.
     * @param name of Room
     * @param type of Sensor (temperature)
     * @param date any given day
     * @return the maximum temperature in a specific Room in a given day
     */
    public double getMaximumTemperatureInRoomInGivenDay(String name, SensorType type, LocalDate date) {
        return getRoomByName(name).getMaximumMeasurementInGivenDay(type, date);
    }

    /**
     * method that check if a name of a room already exists on the list of rooms.
     * @param name
     * @return boolean
     */
    public boolean isNameExistant(String name) {

        for (int i = 0; i < mRoomList.size(); i++) {
            if (mRoomList.get(i).getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that displays the device list content of a Room
     * @param position position of the room in the room list
     * @return list of devices of a room
     */
    public String getDeviceListContentByPosition(int position) {
        return mRoomList.get(position).getDeviceListToString();
    }

    /**
     * Method that checks if the Device List of the room is empty
     * @param position chosen room
     */
    public boolean isDeviceListEmpty(int position) {
        return mRoomList.get(position).isDeviceListEmpty();
    }

    /**
     * method that checks if there are no devices in the RoomList
     * @return true if there aren't devices. False if there are devices
     */
    public boolean isDeviceListOfAllRoomsEmpty() {

        for (int i = 0; i < mRoomList.size(); i++) {
            if (!isDeviceListEmpty(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that displays the sensor list content of a Room
     * @param position
     * @return
     */
    public String getSensorListContentOfRoom(int position) {
        return mRoomList.get(position).getSensorListContent();
    }

    /**
     * method that check if the sensor list of the room is empty
     *
     * @param position
     */
    public boolean isSensorListEmpty(int position) {
        return mRoomList.get(position).isSensorListEmpty();
    }

    /**
     * method that gets a list of devices in all rooms of roomlist
     * @return DeviceList
     */
    public DeviceList getAllDevicesList() {
        DeviceList roomDeviceList;
        DeviceList allDeviceList = new DeviceList();
        Device dev;

        for (int i = 0; i < mRoomList.size(); i++) {
            roomDeviceList = mRoomList.get(i).getDeviceList();
            for (int j = 0; j < roomDeviceList.getSize(); j++) {
                dev = roomDeviceList.getDeviceByPosition(j);
                allDeviceList.addDevice(dev);
            }
        }
        return allDeviceList;
    }

    /**
     * get the list of rooms to string.
     *
     * @return the content of the list by string.
     */
    public String getRoomListToString() {
        StringBuilder content = new StringBuilder();
        for (int i = 1; i <= mRoomList.size(); i++) {
            content.append(i + " - Name: " + mRoomList.get(i - 1).getName());
            content.append("\n");
        }
        return content.toString();
    }

    /**
     * method that get all devices of a type.
     * @return list with a devices type.
     */
    public DeviceList getAllDevicesOfAType(String type) {
        DeviceList listWithDevicesOfAType = new DeviceList();
        for (Room room : mRoomList) {
            listWithDevicesOfAType.getDeviceList().addAll(room.getAllDevicesOfType(type).getDeviceList());
        }
        return listWithDevicesOfAType;
    }

    /**
     * Method that returns the content of all the devices present in all the rooms in the list.
     *
     * @return String with the list of devices content.
     */
    public String getAllDevicesToString() {
        StringBuilder content = new StringBuilder();
        int numberInTheList = 1;
        for (Room room : mRoomList) {
            if (!room.getDeviceList().isDeviceListEmpty()) {
                content.append(numberInTheList + room.getDeviceListToString().substring(1));
                numberInTheList++;
            }
        }
        return content.toString();
    }
}