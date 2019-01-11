package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;

public class GetRoomListController {
    private RoomList mList;

    public GetRoomListController(RoomList list) {
        this.mList = list;
    }

    public String displayOfTheRoomList() {
        return mList.getRoomListContent();
    }

    public boolean checkIfListIsEmpty(){
        return mList.checkIfRoomListIsEmpty();
    }
}
