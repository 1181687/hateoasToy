package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

import java.time.LocalDateTime;

public class GetEnergyConsumptionOfAGridController {

    private House house;
    private HouseGrid houseGrid;

    public GetEnergyConsumptionOfAGridController(House house) {
        this.house = house;
    }

    public boolean isHouseGridListEmpty() {
        return house.isHouseGridListEmpty();
    }

    public int getHouseGridListSize() {
        return house.getHouseGridListSize();
    }

    public String getHouseGridListToString() {
        return this.house.getHouseGridListToString();
    }

    public void getHouseGridByPosition(int position) {
        this.houseGrid = house.getHouseGridByPosition(position);
    }

    public double getEnergyConsumptionInAnInterval(LocalDateTime startDate, LocalDateTime endDate) {
        return this.houseGrid.getEnergyConsumptionInAnInterval(startDate, endDate);
    }

    public String getHouseGridName(){
        return this.houseGrid.getName();
    }


}
