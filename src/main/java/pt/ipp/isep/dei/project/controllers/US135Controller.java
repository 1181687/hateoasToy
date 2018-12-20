package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;
import pt.ipp.isep.dei.project.model.PowerSource;

public class US135Controller {

    private HouseGridList mHouseGridList;
    private PowerSource mPowerSource;


    public US135Controller(HouseGridList houseGridList, PowerSource newPowerSource) {
        this.mHouseGridList = houseGridList;
        this.mPowerSource = newPowerSource;
    }

    public int getNumberOfHouseGridsInTheHouseGridList (){
        return mHouseGridList.getmList().size();
    }

    public String getHouseGridListContent() {
        return mHouseGridList.getContentOfHouseGrid();
    }

    public HouseGrid getHouseGridFromListByPosition(int position){
        return this.mHouseGridList.getHouseGridFromASpecificPositionInTheList(position);
    }

    public boolean addPowerSourceToHouseGrid(HouseGrid selectedHouseGrid) {
        return selectedHouseGrid.getPowerSourceList().addPowerSourceToList(mPowerSource);
    }
}
