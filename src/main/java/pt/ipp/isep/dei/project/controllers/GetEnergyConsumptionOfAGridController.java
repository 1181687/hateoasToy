package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfAGridController {

    private House mHouse;
    private HouseGrid mHouseGrid;

    public GetEnergyConsumptionOfAGridController(House house) {
        this.mHouse = house;
    }

    public boolean isHouseGridListEmpty() {
        return mHouse.isHouseGridListEmpty();
    }

    public int getHouseGridListSize() {
        return mHouse.getHouseGridListSize();
    }

    public String getHouseGridListToString() {
        return this.mHouse.getHouseGridListToString();
    }

    public void getHouseGridByPosition(int position) {
        this.mHouseGrid = mHouse.getHouseGridByPosition(position);
    }

    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return this.mHouseGrid.getEnergyConsumptionInAnInterval(startDate, endDate);
    }

    public String getHouseGridName(){
        return this.mHouseGrid.getHouseGridName();
    }


}
