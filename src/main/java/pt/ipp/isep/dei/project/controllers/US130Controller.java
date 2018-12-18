package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.RoomList;

public class US130Controller {

    private HouseGrid mHouseGrid;

    public void defineHouseGridName (String houseGridName){
        mHouseGrid.setmHouseGridName(houseGridName);
    }

}
