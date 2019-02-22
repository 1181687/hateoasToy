package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

import java.util.List;


public class CreateHouseGridController {

    private House house;

    public CreateHouseGridController(House house) {
        this.house = house;
    }

    /**
     * Get method.
     *
     * @return mHouseGridList.
     */
    public List<HouseGrid> getHouseGridList() {
        return house.getHouseGridList();
    }

    /**
     * Method that creates a new house grid.
     *
     * @param nameOfHG Name of the grid.
     * @return New object of the class HouseGrid.
     */
    public HouseGrid createANewHouseGrid (String nameOfHG){
        return house.newHouseGrid(nameOfHG);
    }

    /**
     * Method that adds a new grid to the list of grids.
     *
     * @param grid Specified grid.
     */
    public void addHouseGridToTheListOfHouseGrids(HouseGrid grid) {
        house.addGrid(grid);
    }
}