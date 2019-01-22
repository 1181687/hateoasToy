package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;


public class AddSensorToRoomController {

    private SensorTypeList mListSensorsType;
    private RoomList mRoomList;
    private House mHouse;
    private Sensor mNewSensor;
    private Room mSelectedRoom;
    private SensorType mSensorType;
    private Location mLocationOfTheHouse;

    public AddSensorToRoomController(SensorTypeList mListSensorsType, RoomList mRoomList, House mHouse) {
        this.mListSensorsType = mListSensorsType;
        this.mRoomList = mRoomList;
        this.mHouse = mHouse;
    }

    /**
     * This method display the rooms of the house
     * @return the RoomList.
     */
    public String getRoomListContent() {
        return this.mRoomList.getRoomListContent();
    }

    /**
     * This method give de position of the room in the rooms list.
     * @param position of the room in the list of rooms.
     */
    public void getRoomByIndex (int position) {
        mSelectedRoom = this.mRoomList.getRoomFromPosition(position);
    }

    /**
     * This method display the list of sensors type in the sensors type list.
     * @return the sensor type list.
     */
    public String displayListOfSensorsType () {
        return this.mListSensorsType.getSensorTypeListToString();
    }

    /**
     * This method get de position of the sensor type in the list of sensors type.
     * @param position of sensors type in the sensors type list.
     */
    public void getSensorTypeByIndex (int position) {
        mSensorType = this.mListSensorsType.getSensorTypeByPosition(position);
    }

    /**
     * This method get the location of the house.
     */
    public void getLocationOfTheHouse () {
        mLocationOfTheHouse = mHouse.getLocation();
    }

    /**
     * This method create and add a sensor to the list of sensors in the room.
     * @param name of the new sensor.
     * @return a new sensor added in the list of sensors in the room.
     */
    public boolean createAndAddSensorToTheList (String name) {
        mNewSensor = mSelectedRoom.getSensorList().newSensor(name, mSensorType, mLocationOfTheHouse);
        return mSelectedRoom.addSensorToListOfSensorsInRoom(mNewSensor);
    }

    /**
     * Method that checks if a room isn't already in the list of rooms.
     * @return true or false.
     */
    public boolean isRoomListEmpty() {
        return mRoomList.isEmpty();
    }

    /**
     * This method check if the list of Sensors Type is empty.
     * @return true or false.
     */
    public boolean isSensorTypeListEmpty() {
        return mListSensorsType.IsEmpty();
    }
}
