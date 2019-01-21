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
     * @return true in case there are no HouseGrids or false if there is at least one HouseGrid in the House.
     */

    public boolean houseGridListIsEmpty(){
        return this.mHouse.checkIfHouseGridListIsEmpty();
    }

    /**
     * Returns a list of HouseGrids that are in the House's HouseGridList.
     * @return String
     */

    public String listHouseGrids(){
        return this.mHouse.getHouseGridListContent();
    }

    /**
     * Returns the number of HouseGrids contained in the House's HouseGridList.
     * @return integer
     */

    public int getHouseGridListSize(){
        return mHouse.getHouseGridListLength();
    }

    /**
     * Gets a HouseGrid from the HouseGridList, by position, and saves it in the Controller.
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
