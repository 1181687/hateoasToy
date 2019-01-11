package pt.ipp.isep.dei.project.io.ui;

import pt.ipp.isep.dei.project.controllers.GetRoomListController;
import pt.ipp.isep.dei.project.model.RoomList;

/** US108 As an Administrator, I want to have a list of existing rooms, so that I can choose
one to edit it. */

public class GetRoomList {

    private GetRoomListController ctrl;

    public GetRoomList(RoomList list) {
        this.ctrl = new GetRoomListController(list);
    }

    public void run() {
        if (ctrl.checkIfListIsEmpty()) {
            System.out.println("The list is empty. Please add a room.");
            return;
        }
        System.out.println("This is the list of existing rooms.");
        System.out.println(ctrl.getRoomListContent());
    }
}