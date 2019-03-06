package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.*;

public class AddPowerSourceToHouseGridController {

    private House house;
    private PowerSourceTypeList powerSourceTypeList;
    private HouseGrid selectedHouseGrid;
    private PowerSourceType selectedPowerSourceType;

    /**
     * this is the construtor of the controller, with the house and the PowerSourceTypeList
     * @param house
     * @param powerSourceTypeList
     */
    public AddPowerSourceToHouseGridController(House house, PowerSourceTypeList powerSourceTypeList) {
        this.house = house;
        this.powerSourceTypeList = powerSourceTypeList;
    }

    /**
     * Method that checks if the List of House Grids is empty.
     *
     * @return boolean
     */
    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }


    /**
     * Method that lists the House Grids in the list of Housegrids.
     *
     * @return String
     */
    public String getHouseGridListToString() {
        return house.getHouseGridListToString();
    }

    /**
     * Method that calculates the size of the housegrid list.
     * @return int
     */

    public int getHouseGridListSize(){
        return house.getHouseGridListSize();
    }

    /**
     * Method that gets a housegrid from a list of Housegrids by position.
     * @param position
     */

    public void getHouseGridFromListByPosition(int position) {
        this.selectedHouseGrid = this.house.getHouseGridByPosition(position);
    }

    /**
     * Method that creates a powersource and adds it to the PowerSourceList in the selected housegrid.
     * @param name of the Power Source.
     * @return true if the Power Source is created and added with success to the housegrid or false if the powersource is not added
     */

    public boolean createAndAddPowerSourceToHouseGrid(String name) {
        PowerSource newPowerSource = this.selectedHouseGrid.getPowerSourceList().createNewPowerSource(name, this.selectedPowerSourceType);
        return this.selectedHouseGrid.addPowerSource(newPowerSource);
    }

    /**
     * Returns a list of Power Source Types.
     * @return String
     */
    public String getPowerSourceTypeListToString() {
        return powerSourceTypeList.getPowerSourceTypeListToString();
    }

    /**
     * Returns the number of elements contained in the PowerSourceTypeList.
     *
     * @return
     */
    public int getPowerSourceTypeListSize() {
        return powerSourceTypeList.getSize();
    }

    /**
     * Method that, given a position, retrieves a PowerSourceType from a PowerSourceTypeList.
     *
     * @param position
     */
    public void getPowerSourceTypeByPosition(int position) {
        this.selectedPowerSourceType = this.powerSourceTypeList.getPowerSourceTypeFromASpecificPosition(position);
    }

    /**
     * Returns the selected housegrid name.
     * @return String
     */
    public String getHouseGridName(){
        return selectedHouseGrid.getName();
    }

    /**
     * Returns a list of PowerSources that have been added to the housegrid's PowerSourceList.
     * @return
     */
    public String listPowerSourcesConnectedToGrid(){
        return selectedHouseGrid.getPowerSourceListContent();
    }
}
