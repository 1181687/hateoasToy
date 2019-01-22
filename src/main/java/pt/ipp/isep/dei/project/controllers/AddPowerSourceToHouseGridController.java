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
     * Method that checks if the HouseGridList is empty.
     * @return boolean
     */
    public boolean checkIfHouseGridListIsEmpty() {
        return mHouseGridList.isHouseGridListEmpty();
    }


    /**
     * Method that lists the House Grids in the HouseGridList.
     * @return String
     */
    public String getHouseGridListToString() {
        return mHouseGridList.getHouseGridListToString();
    }

    /**
     * Method that calculates the size of the HouseGrid list.
     * @return int
     */

    public int houseGridListLength(){
        return mHouseGridList.getmHouseGridsList().size();
    }

    /**
     * Method that gets a HouseGrid from the HouseGridList by position.
     * @param position
     */

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouseGridList.getHouseGridByPosition(position);
    }

    /**
     * Method that creates a PowerSource and adds it to the PowerSourceList in the selected HouseGrid.
     * @param name of the Power Source.
     * @return true if the Power Source is created and added with success to the HouseGrid or false if the PowerSource is not added
     */

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        PowerSource newPowerSource = this.mSelectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.mSelectedPowerSourceType);
        return this.mSelectedHouseGrid.addPowerSource(newPowerSource);
    }

    /**
     * Returns a list of Power Source Types.
     * @return String
     */
    public String getPowerSourceTypeListContent() {
        return mPowerSourceTypeList.getPowerSourceTypeListToString();
    }

    /**
     * Returns the number of elements contained in the PowerSourceTypeList.
     * @return
     */

    public int powerSourceTypeListLength() {
        return mPowerSourceTypeList.getLength();
    }

    /**
     * Method that, given a position, retrieves a PowerSourceType from a PowerSourceTypeList.
     * @param position
     */

    public void getPowerSourceTypeByPosition(int position) {
        this.mSelectedPowerSourceType = this.mPowerSourceTypeList.getPowerSourceTypeFromASpecificPosition(position);
    }

    /**
     * Returns the selected HouseGrid name.
     * @return String
     */

    public String getHouseGridName(){
        return mSelectedHouseGrid.getName();
    }

    /**
     * Returns a list of PowerSources that have been added to the HouseGrid's PowerSourceList.
     * @return
     */
    public String listPowerSourcesConnectedToHouseGrid(){
        return mSelectedHouseGrid.getPowerSourceListContent();
    }
}
