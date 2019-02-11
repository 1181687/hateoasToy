package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

public class GetNominalPowerOfAGridController {
    private House mHouse;
    private HouseGrid mSelectedHouseGrid;

    public GetNominalPowerOfAGridController(House house) {
        this.mHouse=house;
    }

    /**
     * Method that checks if there are no HouseGrids in the House.
     *
     * @return true in case there are no HouseGrids or false if there is at least one HouseGrid in the House.
     */
    public boolean isGridListEmpty() {
        return this.mHouse.isHouseGridListEmpty();
    }


    /**
     * Returns a list of HouseGrids that are in the House's list of HouseGrids.
     * @return String
     */

    public String listHouseGrids(){
        return this.mHouse.getHouseGridListToString();
    }

    /**
     * Returns the number of HouseGrids contained in the House's list of HouseGrids.
     *
     * @return integer
     */

    public int getHouseGridListSize() {
        return mHouse.getHouseGridListSize();
    }

    /**
     * Gets a HouseGrid from the list of house grids, by position, and saves it in the Controller.
     * @param position
     */

    public void getHouseGridByPosition(int position){
        mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
    }

    /**
     * Calculates the nominal power of the selected HouseGrid.
     * @return double
     */
    public double getHouseGridTotalNominalPower(){
        return mSelectedHouseGrid.getNominalPower();
    }
}
