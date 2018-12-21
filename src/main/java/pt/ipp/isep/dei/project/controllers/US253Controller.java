package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;


public class US253Controller {

    private ListaTiposSensores mListSensorsType;
    private RoomList mRoomList;
    private House mHouse;
    private Sensor mNewSensor;
    private Room mSelectedRoom;
    private TipoSensor mSensorType;
    private Location mLocationOfTheHouse;

    public US253Controller(ListaTiposSensores mListSensorsType, RoomList mRoomList, House mHouse) {
        this.mListSensorsType = mListSensorsType;
        this.mRoomList = mRoomList;
        this.mHouse = mHouse;
    }

    public String displayRoomsInTheHouse () {
        return this.mRoomList.getDisplayRoomList();
    }

    public void getRoomByIndex (int position) {
        mSelectedRoom = this.mRoomList.getRoomFromASpecificPositionInTheList(position);
    }

    public String displayListOfSensorsType () {
        return this.mListSensorsType.displaySensorTypeList();
    }

    public void getSensorTypeByIndex (int position) {
        mSensorType = this.mListSensorsType.getTipoSensorPorPosicao(position);
    }

    public void getLocationOfTheHouse () {
        mLocationOfTheHouse = mHouse.getLocationOfTheHouse();
    }

    public boolean createAndAddSensorToTheList (String name) {
        mNewSensor = mSelectedRoom.getSensorList().createNewSensor(name, mSensorType, mLocationOfTheHouse);
        return mSelectedRoom.addSensorToTheListOfSensorsInTheRoom(mNewSensor);
    }

    public boolean checkIfRoomListIsEmpty () {
        return mRoomList.checkIfRoomListIsEmpty();
    }
}
