package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class GetNominalPowerRoomsDevicesController {
    private House mHouse;
    private HouseGrid mSelectedHouseGrid;
    private MeasurableList mMeasurableList;

    public GetNominalPowerRoomsDevicesController(House house) {
        this.mHouse=house;
        this.mMeasurableList = house.getNewMeasurableObjList();

    }

    /**
     * method that returns the method isHouseGridListEmpty of the model class House
     *
     * @return true if house grid list is empty or false if it is not empty
     */
    public boolean isGridListEmpty(){
        return this.mHouse.isHouseGridListEmpty();
    }

    /**
     * method that returns the method getHouseGridListContent of the model class House
     * @return house grid list content
     */
    public String getHouseGridsListToString(){
        return this.mHouse.getHouseGridListContent();
    }

    /**
     * method that returns the method getHouseGridListSize of the model class House
     * @return an integer that matches the house grid list size
     */
    public int getHouseGridListSize(){
        return this.mHouse.getHouseGridListSize();
    }

    /**
     * method that stores the chosen house grid in Controller
     * @param position integer that matches the position of house grid in house grid list of house
     */
    public void getHouseGridByPosition(int position){
        mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
    }

    /**
     * method that returns the method getRoomPosition of the model class House Grid
     * @param position integer that matches the position of room in room list of house grid
     * @return Room that matches the chosen position
     */
    public Room getRoomOfHouseGridByPosition(int position){
        return mSelectedHouseGrid.getRoomByPosition(position);
    }

    /**
     * method that returns the method getDeviceListToString of the model class House Grid
     * @param position integer that matches the position of room in room list of house grid
     * @return device list content of the chosen room (position)
     */
    public String getDeviceListToString(int position){
        return mSelectedHouseGrid.getDeviceListContent(position);
    }

    /**
     * method that returns the method getDeviceListSizeByRoomPosition of the model class House Grid
     * @param position integer that matches the position of room in room list of house grid
     * @return integer that matches the device list size
     */
    public int getDeviceListSize(int position){
        return mSelectedHouseGrid.getDeviceListSizeByRoomPosition(position);
    }

    /**
     * method that returns the method isRoomListEmpty of the model class House Grid
     * @return true if room list is empty or false if it is not empty
     */
    public boolean roomListOfHouseGridIsEmpty() {
        return mSelectedHouseGrid.isRoomListEmpty();
    }

    /**
     * method that returns the method isDeviceListEmpty of the model class House Grid
     * @param position integer that matches the position of room in room list of house grid
     * @return true if device list of chosen room (position) is empty or false if it is not empty
     */
    public boolean deviceListIsEmpty(int position){
        return mSelectedHouseGrid.isDeviceListOfRoomEmpty(position);
    }

    /**
     * method that returns the method getDeviceFromPositionInList of the model class House Grid
     * @param pos1 integer that matches the room in room list of house grid
     * @param pos2 integer that matches the device in device list of chosen room
     * @return device that matches the chosen position
     */
    public Device getDeviceListByPosition(int pos1, int pos2){
        return mSelectedHouseGrid.getDeviceFromPositionInList(pos1, pos2);
    }

    /**
     * method that returns the method getNominalPower of model class MeasurableList
     * @return nominal power of objects in measurable list
     */
    public double getNominalPowerOfMeasurableObjects(){
        return mMeasurableList.getNominalPower();
    }

    /**
     * method that adds a measurable object (room or device)
     * @param measurable matches an object: room or device
     */
    public void addMeasurable(Measurable measurable) {
        mMeasurableList.addMeasurable(measurable);
    }

    /**
     * method that returns the method getRoomListContent of model class House Grid
     * @return the room list content
     */
    public String getRoomListInHouseGridToString() {
        return mSelectedHouseGrid.getRoomListContent();
    }

    /**
     * method that returns the method getRoomListSize of model class House Grid
     * @return an integer that matches the size of room list
     */
    public int getRoomListInHouseGridSize() {
        return mSelectedHouseGrid.getRoomListSize();
    }

    /**
     * method that returns the method isMeasurableInList of model class measurable list
     *
     * @param measurable matches an object: room or device
     * @return true if measurable list is empty and false if it is not
     */
    public boolean isMeasurableInList(Measurable measurable){
        return mMeasurableList.checkIfMeasurableObjIsInList(measurable);
    }

    /**
     * method that returns the method getListToString of model class MeasurableList
     *
     * @return list (string)
     */
    public String getListToString() {
        return mMeasurableList.getListToString();
    }

}