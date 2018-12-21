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
        return mHouseGridList.displayOfTheContentOfTheHouseGridsInTheList();
    }

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouseGridList.getHouseGridFromASpecificPositionInTheList(position);
    }

    public boolean createAndAddPowerSourceToHouseGrid(String name, boolean isRechargeable) {
        mPowerSource = this.mSelectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.mSelectedPowerSourceType, isRechargeable);
        return this.mSelectedHouseGrid.addPowerSourceToHouseGrid(mPowerSource);
    }

    public String displayPowerSourceTypeList() {
        return mPowerSourceTypeList.displayPowerSourceTypeList();
    }

    public void getPowerSourceTypeFromListByPosition(int position) {
        this.mSelectedPowerSourceType = this.mPowerSourceTypeList.getPowerSourceTypeFromASpecificPositionInTheList(position);
    }

    public String chooseRechargeableOption() {

        return mSelectedHouseGrid.getPowerSourceList().chooseRechargeableOption();
    }

    public boolean isRechargeable(int opcao) {
        if (opcao == 1) {
            return true;
        }
        return false;
    }

}
