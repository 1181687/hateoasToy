package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US135Controller {

    private HouseGridList mHouseGridList;
    private PowerSource mPowerSource;
    private PowerSourceTypeList mPowerSourceTypeList;
    private HouseGrid mSelectedHouseGrid;
    private PowerSourceType mSelectedPowerSourceType;


    public US135Controller(HouseGridList houseGridList, PowerSourceTypeList powerSourceTypeList) {
        this.mHouseGridList = houseGridList;
        this.mPowerSourceTypeList = powerSourceTypeList;
    }

    public boolean checkIfHouseGridListIsEmpty() {
        return mHouseGridList.checkIfHouseGridListIsEmpty();
    }

    public String getHouseGridListContent() {
        return mHouseGridList.getContentOfTheHouseGridsInTheList();
    }

    public int houseGridListLength(){
        return mHouseGridList.getmHouseGridsList().size();
    }

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouseGridList.getHouseGridFromASpecificPositionInTheList(position);
    }

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        mPowerSource = this.mSelectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.mSelectedPowerSourceType);
        return this.mSelectedHouseGrid.addPowerSource(mPowerSource);
    }

    public String getPowerSourceTypeListContent() {
        return mPowerSourceTypeList.displayPowerSourceTypeList();
    }

    public int powerSourceTypeListLength(){
        return mPowerSourceTypeList.powerSourceTypeListLength();
    }

    public void getPowerSourceTypeFromListByPosition(int position) {
        this.mSelectedPowerSourceType = this.mPowerSourceTypeList.getPowerSourceTypeFromASpecificPosition(position);
    }

}
