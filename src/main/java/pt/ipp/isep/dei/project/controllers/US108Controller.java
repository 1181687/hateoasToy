package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;

public class US108Controller {
    private RoomList mList;

    public US108Controller(RoomList list) {
        this.mList = list;
    }

    public String displayOfTheRoomList() {
        return mList.getDisplayRoomList();
    }

    public boolean checkIfListIsEmpty(){
        return mList.checkIfRoomListIsEmpty();
    }

    public int sizeOfTheList(){
        return mList.listSize();
    }

    public String displayOfTheChosenRoom(int position) {
        return mList.getDisplayOfTheChosenRoom(position);
    }

    public void changeNameOfTheRoom(int chosenRoom, String change) {
        mList.setRoomName(chosenRoom, change);
    }

    public void changeHouseFloorOfTheRoom(int chosenRoom, int change) {
        mList.setRoomFloor(chosenRoom, change);
    }

    public void changeDimensionsOfTheRoom(int chosenRoom, int chosenFeature, double change) {
        mList.setRoomDimensions(chosenRoom, chosenFeature, change);
    }
}
