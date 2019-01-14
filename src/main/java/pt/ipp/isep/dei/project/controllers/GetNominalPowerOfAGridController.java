package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

public class GetNominalPowerOfAGridController {
    private House mHouse;
    private HouseGrid mSelectedHouseGrid;

    public GetNominalPowerOfAGridController(House house) {
        this.mHouse=house;
    }

    public boolean checkIfGridListIsEmpty(){
        return this.mHouse.checkIfHouseGridListIsEmpty();
    }

    public String listHouseGrids(){
        return this.mHouse.getHouseGridListContent();
    }

    public int getHouseGridListLength(){
        return mHouse.getHouseGridListLength();
    }

    public void getHouseGridbyPosition(int position){
        mSelectedHouseGrid = this.mHouse.getHouseGridByPosition(position);
    }

    public double getHouseGridTotalNominalPower(){
        return mSelectedHouseGrid.getNominalPower();
    }
}
