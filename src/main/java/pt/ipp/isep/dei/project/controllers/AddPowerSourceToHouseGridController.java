package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddPowerSourceToHouseGridController {

    private HouseGridList mHouseGridList;
    private PowerSourceTypeList mPowerSourceTypeList;
    private HouseGrid mSelectedHouseGrid;
    private PowerSourceType mSelectedPowerSourceType;


    public AddPowerSourceToHouseGridController(HouseGridList houseGridList, PowerSourceTypeList powerSourceTypeList) {
        this.mHouseGridList = houseGridList;
        this.mPowerSourceTypeList = powerSourceTypeList;
    }

    /**
     * Checks if the HouseGridList is empty.
     * @return boolean
     */

    public boolean houseGridListIsEmpty() {
        return mHouseGridList.checkIfHouseGridListIsEmpty();
    }

    /**
     * Lists the House Grids in the HouseGridList
     * @return String
     */
    public String getHouseGridListToString() {
        return mHouseGridList.getHouseGridListToString();
    }

    /**
     * Calculates the
     * @return
     */

    public int houseGridListLength(){
        return mHouseGridList.getmHouseGridsList().size();
    }

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouseGridList.getHouseGridByPosition(position);
    }

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        PowerSource newPowerSource = this.mSelectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.mSelectedPowerSourceType);
        return this.mSelectedHouseGrid.addPowerSource(newPowerSource);
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
        return mSelectedHouseGrid.getName();
    }

    public String listPowerSourcesConnectedToHouseGrid(){
        return mSelectedHouseGrid.getPowerSourceListContent();
    }
}
