package pt.ipp.isep.dei.project.controllers;

import pt.ipp.isep.dei.project.model.house.housegrid.HouseGrid;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridDTO;
import pt.ipp.isep.dei.project.model.house.housegrid.HouseGridMapper;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceType;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeDTO;
import pt.ipp.isep.dei.project.model.house.powersource.PowerSourceTypeMapper;
import pt.ipp.isep.dei.project.services.HouseService;

import java.util.ArrayList;
import java.util.List;

public class AddPowerSourceToHouseGridController {

    private HouseService houseService;


    public AddPowerSourceToHouseGridController(HouseService houseService) {

        this.houseService = houseService;
    }

    /**
     * Method that checks if the List of House Grids is empty.
     *
     * @return boolean
     */
    public boolean isHouseGridRepositoryEmpty() {
        return this.houseService.isGridRepositoryEmpty();
    }


    public List<HouseGridDTO> getGridList() {
        List<HouseGrid> grids = this.houseService.getAllGrids();
        List<HouseGridDTO> gridDTOS = new ArrayList<>();
        for (HouseGrid grid : grids) {
            HouseGridDTO gridDTO = HouseGridMapper.mapToDTO(grid);
            gridDTOS.add(gridDTO);
        }
        return gridDTOS;
    }

    public List<PowerSourceTypeDTO> getPowerSourceTypeList() {
        List<PowerSourceType> types = this.houseService.getAllPowerSourceTypes();
        List<PowerSourceTypeDTO> typesDTOs = new ArrayList<>();
        for (PowerSourceType type : types) {
            PowerSourceTypeDTO typeDTO = PowerSourceTypeMapper.mapToDTO(type);
            typesDTOs.add(typeDTO);
        }
        return typesDTOs;
    }

    public boolean newPowerSource(String powerSourceId, String typeId, String gridId) {
        return this.houseService.newPowerSource(powerSourceId, typeId, gridId);
    }
}
