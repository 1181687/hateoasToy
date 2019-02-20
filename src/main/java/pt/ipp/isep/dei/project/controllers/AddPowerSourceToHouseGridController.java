package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddPowerSourceToHouseGridController {

    private House mHouse;
    private PowerSourceTypeList mPowerSourceTypeList;
    private HouseGrid mSelectedHouseGrid;
    private PowerSourceType mSelectedPowerSourceType;

    /**
     * this is the construtor of the controller, with the house and the PowerSourceTypeList
     * @param house
     * @param powerSourceTypeList
     */
    public AddPowerSourceToHouseGridController(House house, PowerSourceTypeList powerSourceTypeList) {
        this.mHouse = house;
        this.mPowerSourceTypeList = powerSourceTypeList;
    }

    /**
     * Method that checks if the List of House Grids is empty.
     *
     * @return boolean
     */
    public boolean isHouseGridListEmpty() {
        return mHouse.isHouseGridListEmpty();
    }


    /**
     * Method that lists the House Grids in the list of Housegrids.
     *
     * @return String
     */
    public String getHouseGridListToString() {
        return mHouse.getHouseGridListToString();
    }

    /**
     * Method that calculates the size of the HouseGrid list.
     * @return int
     */

    public int getHouseGridListSize(){
        return mHouse.getHouseGridListSize();
    }

    /**
     * Method that gets a HouseGrid from a list of Housegrids by position.
     * @param position
     */

    public void getHouseGridFromListByPosition(int position) {
        this.mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
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
    public String getPowerSourceTypeListToString() {
        return mPowerSourceTypeList.getPowerSourceTypeListToString();
    }

    /**
     * Returns the number of elements contained in the PowerSourceTypeList.
     *
     * @return
     */
    public int getPowerSourceTypeListSize() {
        return mPowerSourceTypeList.getSize();
    }

    /**
     * Method that, given a position, retrieves a PowerSourceType from a PowerSourceTypeList.
     *
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
        return mSelectedHouseGrid.getHouseGridName();
    }

    /**
     * Returns a list of PowerSources that have been added to the HouseGrid's PowerSourceList.
     * @return
     */
    public String listPowerSourcesConnectedToGrid(){
        return mSelectedHouseGrid.getPowerSourceListContent();
    }
}
