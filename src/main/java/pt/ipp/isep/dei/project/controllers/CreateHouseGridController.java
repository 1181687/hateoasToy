package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.HouseGrid;
import pt.ipp.isep.dei.project.model.HouseGridList;


public class CreateHouseGridController {

    private HouseGridList mHouseGridList;

    public CreateHouseGridController(HouseGridList houseGridList) {
        this.mHouseGridList = houseGridList;
    }

    /**
     * Get method.
     *
     * @return mHouseGridList.
     */
    public HouseGridList getmHouseGridList() {
        return mHouseGridList;
    }

    /**
     * Method that asks the class HouseGridList to create a new house grid.
     *
     * @param nameOfHG Name of the grid.
     * @return New object of the class HouseGrid.
     */
    public HouseGrid createANewHouseGrid (String nameOfHG){
        return mHouseGridList.createAHouseGrid(nameOfHG);
    }

    /**
     * Method that aks the list of grids do add a new grid to it.
     *
     * @param grid Specified grid.
     */
    public void addHouseGridToTheListOfHouseGrids(HouseGrid grid) {
        mHouseGridList.addHouseGridToTheList(grid);
    }
}