package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.RoomList;

public class US108Controller {
    private RoomList mLista;

    public US108Controller(RoomList list) {
        this.mLista = list;
    }

    public String displayOfTheRoomList (){
        return mLista.getDisplayRoomList();
    }

    public String displayOfTheChosenRoom(int position){
        return mLista.getDisplayOfTheChosenRoom(position);
    }
}
