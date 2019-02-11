package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.HouseGrid;

import java.util.List;


public class CreateHouseGridController {

    private House mHouse;

    public CreateHouseGridController(House house) {
        this.mHouse = house;
    }

    /**
     * Get method.
     *
     * @return mHouseGridList.
     */
    public List<HouseGrid> getHouseGridList() {
        return mHouse.getHouseGridList();
    }

    /**
     * Method that asks the class HouseGridList to create a new house grid.
     *
     * @param nameOfHG Name of the grid.
     * @return New object of the class HouseGrid.
     */
    public HouseGrid createANewHouseGrid (String nameOfHG){
        return mHouse.newHouseGrid(nameOfHG);
    }

    /**
     * Method that aks the list of grids do add a new grid to it.
     *
     * @param grid Specified grid.
     */
    public void addHouseGridToTheListOfHouseGrids(HouseGrid grid) {
        mHouse.addHouseGrid(grid);
    }
}