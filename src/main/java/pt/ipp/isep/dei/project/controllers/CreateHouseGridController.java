package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.house.House;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;

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
     * Method that creates a new housegrid grid.
     *
     * @param gridDTO HouseGridDTO.
     * @return New object of the class housegrid.
     */
    public boolean createANewHouseGrid(HouseGridDTO gridDTO) {
        HouseGrid newGrid = HouseGridMapper.mapToEntity(gridDTO);
        return house.createHouseGrid(newGrid);
    }

}