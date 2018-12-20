package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class US135Controller {

    private HouseGridList mHouseGridList;
    private PowerSource mPowerSource;
    private PowerSourceTypeList mPowerSourceTypeList;


    public US135Controller(HouseGridList houseGridList) {
        this.mHouseGridList = houseGridList;
    }

    public boolean checkIfHouseGridListIsEmpty() {
        return mHouseGridList.checkIfHouseGridListIsEmpty();
    }

    public String getHouseGridListContent() {
        return mHouseGridList.getContentOfHouseGrid();
    }

    public HouseGrid getHouseGridFromListByPosition(int position) {
        return this.mHouseGridList.getHouseGridFromASpecificPositionInTheList(position);
    }

    public boolean addPowerSourceToHouseGrid(HouseGrid selectedHouseGrid, String name, PowerSourceType type, boolean isRechargeable) {
        mPowerSource = selectedHouseGrid.getPowerSourceList().createNewPowerSource(name, type, isRechargeable);
        return selectedHouseGrid.addPowerSourceToHouseGrid(mPowerSource);
    }

    public String displayPowerSourceTypeList() {
        return mPowerSourceTypeList.displayPowerSourceTypeList();
    }

    public PowerSourceType getPowerSourceTypeFromListByPosition(int position) {
        return this.mPowerSourceTypeList.getPowerSourceTypeFromASpecificPositionInTheList(position);
    }

    public String chooseRechargeableOption() {

        StringBuilder content = new StringBuilder();
        content.append("1 - Yes");
        content.append("2 - No");
        content.append("\n");

        return content.toString();
    }

    public boolean isRechargeable(int opcao) {
        if (opcao == 1) {
            return true;
        }
        return false;
    }
}
