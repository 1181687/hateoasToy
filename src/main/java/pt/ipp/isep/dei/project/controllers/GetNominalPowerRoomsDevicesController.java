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

    public String getHouseGridsListToString(){
        return this.mHouse.getHouseGridListContent();
    }

    public int getHouseGridListSize(){
        return this.mHouse.getHouseGridListLength();
    }

    public void getHouseGridByPosition(int position){
        mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
    }

    public Room getRoomOfHouseGridByPosition(int position){
        return mSelectedHouseGrid.getRoomByPosition(position);
    }


    public String getDeviceListToString(int position){
        return mSelectedHouseGrid.getDeviceListContent(position);
    }

    public int getDeviceListSize(int position){
        return mSelectedHouseGrid.getDeviceListSizeByRoomPosition(position);
    }

    public boolean roomListOfHouseGridIsEmpty() {
        return mSelectedHouseGrid.checkIfRoomListIsEmpty();
    }

    public boolean deviceListIsEmpty(int position){
        return mSelectedHouseGrid.checkIfDeviceListIsEmpty(position);
    }

    public Device getDeviceListByPosition(int pos1, int pos2){
        return mSelectedHouseGrid.getDeviceFromPositionInList(pos1, pos2);
    }

    public double getNominalPowerOfMeasurableObjects(){
        return mMeasurableList.getNominalPower();
    }

    public void addAMeasurableObject(Measurable measurable){
        mMeasurableList.addMeasurableObjToMeasurableList(measurable);
    }

    public String getRoomListInHouseGridToString() {
        return mSelectedHouseGrid.getRoomListContent();
    }

    public int getRoomListInHouseGridSize() {
        return mSelectedHouseGrid.getRoomListSize();
    }

    public boolean measurableListIsEmpty(Measurable measurable){
        return mMeasurableList.checkIfMeasurableObjIsInList(measurable);
    }

}