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

    public boolean checkIfHouseGridListIsEmpty (){
        return mHouseGridList.checkIfHouseGridListIsEmpty();
    }

    public String getHouseGridListContent() {
        return mHouseGridList.displayOfTheContentOfTheHouseGridsInTheList();
    }

    public HouseGrid getHouseGridFromListByPosition(int position){
        return this.mHouseGridList.getHouseGridFromASpecificPositionInTheList(position);
    }

    public boolean addPowerSourceToHouseGrid(HouseGrid selectedHouseGrid) {
        return selectedHouseGrid.addPowerSourceToHouseGrid(mPowerSource);
    }
}
