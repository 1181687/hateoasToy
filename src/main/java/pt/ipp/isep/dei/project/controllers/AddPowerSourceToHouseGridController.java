package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddPowerSourceToHouseGridController {

    private HouseGridList mHouseGridList;
    private PowerSource mPowerSource;
    private PowerSourceTypeList mPowerSourceTypeList;
    private HouseGrid mSelectedHouseGrid;
    private PowerSourceType mSelectedPowerSourceType;


    public AddPowerSourceToHouseGridController(HouseGridList houseGridList, PowerSourceTypeList powerSourceTypeList) {
        this.mHouseGridList = houseGridList;
        this.mPowerSourceTypeList = powerSourceTypeList;
    }

    public boolean checkIfHouseGridListIsEmpty() {
        return mHouseGridList.checkIfHouseGridListIsEmpty();
    }

    public String getHouseGridListContent() {
        return mHouseGridList.getHouseGridListToString();
    }

    public int houseGridListLength(){
        return mHouseGridList.getmHouseGridsList().size();
    }

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouseGridList.getHouseGridByPosition(position);
    }

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        mPowerSource = this.mSelectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.mSelectedPowerSourceType);
        return this.mSelectedHouseGrid.addPowerSource(mPowerSource);
    }

    public String getPowerSourceTypeListContent() {
        return mPowerSourceTypeList.getPowerSourceTypeListContent();
    }

    public int powerSourceTypeListLength(){
        return mPowerSourceTypeList.powerSourceTypeListLength();
    }

    public void getPowerSourceTypeFromListByPosition(int position) {
        this.mSelectedPowerSourceType = this.mPowerSourceTypeList.getPowerSourceTypeFromASpecificPosition(position);
    }

    public String getHouseGridName(){
        return mSelectedHouseGrid.getmHouseGridName();
    }

    public String listPowerSourcesConnectedToHouseGrid(){
        return mSelectedHouseGrid.listPowerSources();
    }
}
