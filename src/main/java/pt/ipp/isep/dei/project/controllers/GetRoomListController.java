package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;

public class GetRoomListController {
    private RoomList roomList;

    public GetRoomListController(RoomList list) {
        this.roomList = list;
    }

    public String getRoomListContent() {
        return roomList.getRoomListContent();
    }

    public boolean isEmpty(){
        return roomList.isEmpty();
    }
}
