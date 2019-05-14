package pt.ipp.isep.dei.project.controllers;


import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.services.HouseGridService;

import java.util.List;


public class CreateHouseGridController {

    private HouseGridService houseGridService;

    public CreateHouseGridController(HouseGridService houseGridService) {
        // this.house = house;
        this.houseGridService = houseGridService;
    }

    /**
     * Gets List HouseGridDto.
     *
     * @return HouseGridDTOList.
     */
    public List<HouseGridDTO> getHouseGridList() {
        return houseGridService.getAllHouseGridDTO();
    }

    /**
     * Method that creates a new housegrid grid.
     *
     * @param gridDTO HouseGridDTO.
     * @return New object of the class housegrid.
     */
    public boolean createANewHouseGrid(HouseGridDTO gridDTO) {
        HouseGrid newGrid = HouseGridMapper.mapToEntity(gridDTO);
        return houseGridService.newHouseGrid(newGrid);
    }

}