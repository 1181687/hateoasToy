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
}
