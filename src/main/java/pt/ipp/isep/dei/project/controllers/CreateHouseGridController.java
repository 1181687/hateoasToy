package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.House;
import pt.ipp.isep.dei.project.model.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.housegrid.HouseGridMapper;

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
     * @param gridDTO HouseGridDTO.
     * @return New object of the class housegrid.
     */
    public boolean createANewHouseGrid (HouseGridDTO gridDTO){
        HouseGrid newGrid = HouseGridMapper.mapToEntity(gridDTO);
        return house.createHouseGrid(newGrid);
    }

}