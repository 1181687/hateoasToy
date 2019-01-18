package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class GetNominalPowerRoomsDevicesController {
    private House mHouse;
    private HouseGrid mSelectedHouseGrid;
    private MeasurableObjectsList mMeasurableList;

    public GetNominalPowerRoomsDevicesController(House house) {
        this.mHouse=house;
        this.mMeasurableList = house.getNewMeasurableObjList();

    }

    public boolean checkIfGridListIsEmpty(){
        return this.mHouse.checkIfHouseGridListIsEmpty();
    }

    public String listHouseGrids(){
        return this.mHouse.getHouseGridListContent();
    }

    public int getHouseGridListLength(){
        return this.mHouse.getHouseGridListLength();
    }

    public void getHouseGridbyPosition(int position){
        mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
    }

    public Room getChosenRoomInTheGrid(int position){
        return mSelectedHouseGrid.getRoomByPosition(position);
    }


    public String getContentOfDeviceListInRoomOfGrid(int position){
        return mSelectedHouseGrid.getDeviceListContent(position);
    }

    public int getSizeOfListOfDevicesInARoom(int position){
        return mSelectedHouseGrid.getDeviceListSizeByRoomPosition(position);
    }

    public boolean checkIfRoomListIsEmpty() {
        return mSelectedHouseGrid.checkIfRoomListIsEmpty();
    }

    public boolean checkIfDeviceListIsEmpty(int position){
        return mSelectedHouseGrid.checkIfDeviceListIsEmpty(position);
    }

    public Device getDeviceFromPositionInList(int pos1, int pos2){
        return mSelectedHouseGrid.getDeviceFromPositionInList(pos1, pos2);
    }

    public double getNominalPowerOfSelectedMeasurableObjects(){
        return mMeasurableList.getNominalPower();
    }

    public void addAMeasurableObject(Measurable measurable){
        mMeasurableList.addMeasurableObjToMeasurableList(measurable);
    }

    public String getRoomsInTheHouseGrid() {
        return mSelectedHouseGrid.getRoomListContent();
    }

    public int getSizeOfRoomListInGrid() {
        return mSelectedHouseGrid.getRoomListSize();
    }

    public boolean checkIfObjInList(Measurable measurable){
        return mMeasurableList.checkIfMeasurableObjIsInList(measurable);
    }

}